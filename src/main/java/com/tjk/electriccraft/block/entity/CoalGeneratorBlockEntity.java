package com.tjk.electriccraft.block.entity;

import com.tjk.electriccraft.network.ECNetworkMessages;
import com.tjk.electriccraft.network.EnergySync;
import com.tjk.electriccraft.screen.CoalGeneratorMenu;
import com.tjk.electriccraft.utils.ECEnergyStorage;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class CoalGeneratorBlockEntity extends BlockEntity implements MenuProvider {

    protected final ContainerData data;
    private final ItemStackHandler itemHandler = new ItemStackHandler(1) {
        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
        }
    };

    private final ECEnergyStorage energyHandler = new ECEnergyStorage(100000) {
        @Override
        public void onEnergyChanged() {
            setChanged();
            ECNetworkMessages.sendToClients(new EnergySync(this.energy, getBlockPos()));
        }

        @Override
        public boolean canExtract() {
            return true;
        }
    };

    private LazyOptional<IItemHandler> lazyItemHandler = LazyOptional.empty();
    private LazyOptional<IEnergyStorage> lazyEnergyHandler = LazyOptional.empty();
    private int progress = 0;
    private int maxProgress = 100;

    public CoalGeneratorBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.COAL_GENERATOR.get(), pos, state);
        this.data = new ContainerData() {
            @Override
            public int get(int index) {
                return switch (index) {
                    case 0 -> CoalGeneratorBlockEntity.this.progress;
                    case 1 -> CoalGeneratorBlockEntity.this.maxProgress;
                    default -> 0;
                };
            }

            @Override
            public void set(int index, int value) {
                switch (index) {
                    case 0 -> CoalGeneratorBlockEntity.this.progress = value;
                    case 1 -> CoalGeneratorBlockEntity.this.maxProgress = value;
                }
            }

            @Override
            public int getCount() {
                return 0;
            }
        };
    }

    public static void tick(Level level, BlockPos pos, BlockState state, CoalGeneratorBlockEntity entity) {
        if (level.isClientSide()) {
            return;
        }
        if(hasCoal(entity)) {
            entity.progress++;
        if(entity.progress >= entity.maxProgress) {
            createEnergy(entity);
        }
        } else {
            entity.resetProgress();
        }
        setChanged(level, pos, state);
    }

    private static boolean hasCoal(CoalGeneratorBlockEntity entity) {
        return entity.itemHandler.getStackInSlot(0).getItem() == Items.COAL;
    }

    private static void createEnergy(CoalGeneratorBlockEntity entity){
        entity.itemHandler.extractItem(0, 1, false);
        entity.resetProgress();
        entity.energyHandler.receiveEnergy(350, false);
    }


    @Override
    public Component getDisplayName() {
        return Component.literal("Coal Generator");
    }

    public IEnergyStorage getEnergyStorage() { return energyHandler;}
    public void setEnergyLevel(int energy) {this.energyHandler.setEnergy(energy);}

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int id, Inventory inv, Player player) {
        ECNetworkMessages.sendToClients(new EnergySync(this.energyHandler.getEnergyStored(), getBlockPos()));
        return new CoalGeneratorMenu(id, inv, this, this.data);
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if(cap == ForgeCapabilities.ITEM_HANDLER) {
            return lazyItemHandler.cast();
        }
        if(cap == ForgeCapabilities.ENERGY) {
            return lazyEnergyHandler.cast();
        }
        return super.getCapability(cap, side);
    }

    @Override
    public void onLoad() {
        super.onLoad();
        lazyItemHandler = LazyOptional.of(() -> itemHandler);
        lazyEnergyHandler = LazyOptional.of(() -> energyHandler);
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        lazyItemHandler.invalidate();
        lazyEnergyHandler.invalidate();
    }

    @Override
    protected void saveAdditional(CompoundTag nbt) {
        nbt.put("inventory", itemHandler.serializeNBT());
        nbt.putInt("coal_generator.progress", progress);
        nbt.putInt("coal_generator.energy", energyHandler.getEnergyStored());
        super.saveAdditional(nbt);
    }

    @Override
    public void load(CompoundTag nbt) {
        super.load(nbt);
        itemHandler.deserializeNBT(nbt.getCompound("inventory"));
        progress = nbt.getInt("coal_generator.progress");
        energyHandler.setEnergy(nbt.getInt("coal_generator.energy"));
    }

    public void drops()
    {
        SimpleContainer inventory = new SimpleContainer(itemHandler.getSlots());
        for (int i = 0; i < itemHandler.getSlots(); i++)
            inventory.setItem(i, itemHandler.getStackInSlot(i));
        assert this.level != null;
        Containers.dropContents(this.level, this.worldPosition, inventory);
    }

    private void resetProgress() {
        this.progress = 0;
    }
}
