package com.tjk.electriccraft.block.entity;

import com.tjk.electriccraft.screen.BlockPlacerMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.annotation.Nonnull;

import static com.tjk.electriccraft.block.custom.BlockPlacerBlock.FACING;

public class BlockPlacerBlockEntity extends BlockEntity implements MenuProvider {
    private final ItemStackHandler itemHandler = new ItemStackHandler(1) {
        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
        }
    };

    private int counter = 0;
    private LazyOptional<IItemHandler> lazyItemHandler = LazyOptional.empty();


    public final ContainerData data;
    private int progress = 0;
    private int maxProgress = 80;

    public ItemStackHandler getItemStackHandler() {
        return this.itemHandler;
    }

    public BlockPlacerBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(ModBlockEntities.BLOCK_PLACER.get(), blockPos, blockState);
        this.data = new ContainerData() {
            public int get(int index) {
                return switch (index) {
                    case 0 -> BlockPlacerBlockEntity.this.progress;
                    case 1 -> BlockPlacerBlockEntity.this.maxProgress;
                    default -> 0;
                };
            }

            public void set(int index, int value) {
                switch (index) {
                    case 0 -> BlockPlacerBlockEntity.this.progress = value;
                    case 1 -> BlockPlacerBlockEntity.this.maxProgress = value;
                }
            }

            public int getCount() {
                return 2;
            }
        };
    }

    @Override
    public Component getDisplayName() {
        return Component.literal("Block Placer");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int id, Inventory inventory, Player player) {
        return new BlockPlacerMenu(id, inventory, this, this.data);
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap) {
        if (cap == ForgeCapabilities.ITEM_HANDLER) {
            return lazyItemHandler.cast();
        }
        return super.getCapability(cap);
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @javax.annotation.Nullable Direction side) {
        if (cap == ForgeCapabilities.ITEM_HANDLER) {
            return lazyItemHandler.cast();
        }

        return super.getCapability(cap, side);
    }

    @Override
    public void onLoad() {
        super.onLoad();
        lazyItemHandler = LazyOptional.of(() -> itemHandler);
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        lazyItemHandler.invalidate();
    }

    @Override
    protected void saveAdditional(@NotNull CompoundTag tag) {
        tag.put("inventory", itemHandler.serializeNBT());
        tag.putInt("block_placer.progress", progress);
        super.saveAdditional(tag);
    }

    @Override
    public void load(CompoundTag nbt) {
        super.load(nbt);
        itemHandler.deserializeNBT(nbt.getCompound("inventory"));
        progress = nbt.getInt("block_placer.progress");
    }

    public void drops() {
        SimpleContainer inventory = new SimpleContainer(itemHandler.getSlots());
        for (int i = 0; i < itemHandler.getSlots(); i++) {
            inventory.setItem(i, itemHandler.getStackInSlot(i));
        }

        Containers.dropContents(this.level, this.worldPosition, inventory);
    }

    public void tick() {

        Level pLevel = this.level;
        BlockPos pPos = this.worldPosition;
        BlockPlacerBlockEntity pBlockEntity = this;
        ItemStack itemStackInSlot = pBlockEntity.getItemStackHandler().getStackInSlot(0);
        Direction direction = pLevel.getBlockState(pPos).getValue(FACING);
        BlockPos placeHere = pPos.relative(direction);
        this.counter++;

        if (!itemStackInSlot.isEmpty() && this.counter % 1 == 0) {
            Item item = itemStackInSlot.getItem();
            if (item instanceof BlockItem && level.getBlockState(placeHere).isAir()) {
                BlockState itemToBlockState = ((BlockItem) itemStackInSlot.getItem().asItem()).getBlock().defaultBlockState();
                SoundType blockSounds = itemToBlockState.getBlock().getSoundType(itemToBlockState.getBlock().defaultBlockState(), level, pPos, null);

                if (!itemToBlockState.isAir()) {
                    this.itemHandler.getStackInSlot(0).shrink(1);
                    pLevel.playSound(null, pPos, blockSounds.getPlaceSound(), SoundSource.BLOCKS, 1, 1);
                    level.setBlockAndUpdate(placeHere, itemToBlockState);
                }
            }
        }
    }
}
