package com.tjk.electriccraft.block.entity;

import com.tjk.electriccraft.screen.BlockBreakerMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
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

import java.util.List;

import static com.tjk.electriccraft.block.custom.BlockBreakerBlock.FACING;

public class BlockBreakerBlockEntity extends BlockEntity implements MenuProvider {
    private final ItemStackHandler itemHandler = new ItemStackHandler(3) {
        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
        }
    };

    private LazyOptional<IItemHandler> lazyItemHandler = LazyOptional.empty();
    public final ContainerData data;
    private int progress = 0;
    private int maxProgress = 80;

    public BlockBreakerBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.BLOCK_BREAKER.get(), pos, state);
        this.data = new ContainerData() {
            @Override
            public int get(int index) {
                return switch (index) {
                    case 0 -> BlockBreakerBlockEntity.this.progress;
                    case 1 -> BlockBreakerBlockEntity.this.maxProgress;
                    default -> 0;
                };
            }

            @Override
            public void set(int index, int value) {
                switch (index) {
                    case 0 -> BlockBreakerBlockEntity.this.progress = value;
                    case 1 -> BlockBreakerBlockEntity.this.maxProgress = value;
                }
            }

            @Override
            public int getCount() {
                return 2;
            }
        };
    }

    @Override
    public Component getDisplayName() {
        return Component.literal("Block Breaker");
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int id, Inventory inventory, Player player) {
        return new BlockBreakerMenu(id, inventory, this, this.data);
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap) {
        if(cap == ForgeCapabilities.ITEM_HANDLER) {
            return lazyItemHandler.cast();
        }
        return super.getCapability(cap);
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if(cap == ForgeCapabilities.ITEM_HANDLER) {
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
    protected void saveAdditional(CompoundTag nbt) {
        nbt.put("inventory", itemHandler.serializeNBT());
        nbt.putInt("block_breaker.progress", progress);
        super.saveAdditional(nbt);
    }

    @Override
    public void load(CompoundTag nbt) {
        super.load(nbt);
        itemHandler.deserializeNBT(nbt.getCompound("inventory"));
        progress = nbt.getInt("block_breaker.progress");
    }

    public void drops() {
        SimpleContainer inventory = new SimpleContainer(itemHandler.getSlots());
        for (int i = 0; i < itemHandler.getSlots(); i++)
            inventory.setItem(i, itemHandler.getStackInSlot(i));
        assert this.level != null;
        Containers.dropContents(this.level, this.worldPosition, inventory);
    }

    private static void spawnBlockAsEntity(Level level, BlockPos pos, ItemStack stack) {
        if (level.isClientSide || stack.isEmpty() || (!level.getGameRules().getBoolean(GameRules.RULE_DOBLOCKDROPS)) || level.restoringBlockSnapshots)
            ;
        ItemEntity itemAsEntity = new ItemEntity(level,
                ((level.random.nextFloat() * 0.1) + 0.0) + pos.getX(),
                ((level.random.nextFloat() * 0.1) + 0.0) + pos.getY(),
                ((level.random.nextFloat() * 0.1) + 0.0) + pos.getZ(),
                stack
        );
        itemAsEntity.setDefaultPickUpDelay();
        level.addFreshEntity(itemAsEntity);
    }

    public void tick() {

        Level pLevel = this.level;
        BlockPos pPos = this.worldPosition;
        assert pLevel != null;
        Direction direction = pLevel.getBlockState(pPos).getValue(FACING);
        BlockPos placeHere = pPos.relative(direction);

        if (!pLevel.getBlockState(placeHere).isAir() && level instanceof ServerLevel) {

            List<ItemStack> blockDrops;
            final Block block = level.getBlockState(placeHere).getBlock();
            ItemStack tool = itemHandler.getStackInSlot(0);
            int damageValue = this.itemHandler.getStackInSlot(0).getDamageValue();

            if (tool.getItem().isCorrectToolForDrops(block.defaultBlockState()) && this.itemHandler.getStackInSlot(1).isEmpty() && this.itemHandler.getStackInSlot(2).isEmpty()) {

                blockDrops = Block.getDrops(block.defaultBlockState(), (ServerLevel) level, placeHere, this.level.getBlockEntity(pPos), null, tool);
                SoundType blockSounds = level.getBlockState(placeHere).getBlock().getSoundType(level.getBlockState(placeHere).getBlock().defaultBlockState(), level, pPos, null);

                level.setBlockAndUpdate(placeHere, Blocks.AIR.defaultBlockState());

                for (ItemStack drop : blockDrops) {
                    spawnBlockAsEntity(level, placeHere, drop);

                }

                if (tool.isDamageableItem()) {
                    this.itemHandler.getStackInSlot(0).hurt(1, RandomSource.create(), null);
                    pLevel.playSound(null, pPos, blockSounds.getBreakSound(), SoundSource.BLOCKS, (float) 1, 1);


                }
                if (damageValue + 1 == tool.getMaxDamage()) {
                    this.itemHandler.extractItem(0, 1, false);
                    pLevel.playSound(null, pPos, SoundEvents.ITEM_BREAK, SoundSource.BLOCKS, 1, 1);
                }

            }

            else if (tool.getItem().isCorrectToolForDrops(block.defaultBlockState()) && this.itemHandler.getStackInSlot(1).is(Item.byBlock(block)) && this.itemHandler.getStackInSlot(2).isEmpty()) {
                blockDrops = Block.getDrops(block.defaultBlockState(), (ServerLevel) level, placeHere, this.level.getBlockEntity(pPos), null, tool);
                SoundType blockSounds = level.getBlockState(placeHere).getBlock().getSoundType(level.getBlockState(placeHere).getBlock().defaultBlockState(), level, pPos, null);

                level.setBlockAndUpdate(placeHere, Blocks.AIR.defaultBlockState());

                for (ItemStack drop : blockDrops) {
                    spawnBlockAsEntity(level, placeHere, drop);

                }

                if (tool.isDamageableItem()) {
                    this.itemHandler.getStackInSlot(0).hurt(1, RandomSource.create(), null);
                    pLevel.playSound(null, pPos, blockSounds.getBreakSound(), SoundSource.BLOCKS, (float) 1, 1);


                }
                if (damageValue + 1 == tool.getMaxDamage()) {
                    this.itemHandler.extractItem(0, 1, false);
                    pLevel.playSound(null, pPos, SoundEvents.ITEM_BREAK, SoundSource.BLOCKS, 1, 1);
                }
            }

            else if (tool.getItem().isCorrectToolForDrops(block.defaultBlockState()) && !this.itemHandler.getStackInSlot(2).is(Item.byBlock(block)) && this.itemHandler.getStackInSlot(1).isEmpty()) {
                blockDrops = Block.getDrops(block.defaultBlockState(), (ServerLevel) level, placeHere, this.level.getBlockEntity(pPos), null, tool);
                SoundType blockSounds = level.getBlockState(placeHere).getBlock().getSoundType(level.getBlockState(placeHere).getBlock().defaultBlockState(), level, pPos, null);

                level.setBlockAndUpdate(placeHere, Blocks.AIR.defaultBlockState());

                for (ItemStack drop : blockDrops) {
                    spawnBlockAsEntity(level, placeHere, drop);

                }

                if (tool.isDamageableItem()) {
                    this.itemHandler.getStackInSlot(0).hurt(1, RandomSource.create(), null);
                    pLevel.playSound(null, pPos, blockSounds.getBreakSound(), SoundSource.BLOCKS, (float) 1, 1);


                }
                if (damageValue + 1 == tool.getMaxDamage()) {
                    this.itemHandler.extractItem(0, 1, false);
                    pLevel.playSound(null, pPos, SoundEvents.ITEM_BREAK, SoundSource.BLOCKS, 1, 1);
                }
            }
        }
    }
}
