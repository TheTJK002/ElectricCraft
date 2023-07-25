package com.tjk.electriccraft.block.entity;

import com.tjk.electriccraft.block.custom.SimpleSolarPanelGeneratorBlock;
import com.tjk.electriccraft.network.ECNetworkMessages;
import com.tjk.electriccraft.network.EnergySync;
import com.tjk.electriccraft.screen.SimpleSolarPanelGeneratorMenu;
import com.tjk.electriccraft.utils.ECEnergyStorage;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.IEnergyStorage;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class SimpleSolarPanelGeneratorBlockEntity extends BlockEntity implements MenuProvider {
    protected final ContainerData data;

    private final ECEnergyStorage energyHandler = new ECEnergyStorage(16000) {
        @Override
        public void onEnergyChanged() {
            setChanged();
            ECNetworkMessages.sendToClients(new EnergySync(this.energy, getBlockPos()));
        }
    };

    private LazyOptional<IEnergyStorage> lazyEnergyHandler = LazyOptional.empty();
    public SimpleSolarPanelGeneratorBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.SIMPLE_SOLAR_PANEL_GENERATOR.get(), pos, state);
        this.data = new ContainerData() {
            @Override
            public int get(int p_39284_) {
                return 0;
            }

            @Override
            public void set(int p_39285_, int p_39286_) {

            }

            @Override
            public int getCount() {
                return 0;
            }
        };
    }

    public static void tick(Level level, BlockPos pos, BlockState state, SimpleSolarPanelGeneratorBlockEntity entity) {
        if(level.isClientSide()) {
            return;
        }
        if(entity.isEnergyFull(entity)) {
            //Nope
        } else {
            createEnergy(entity);
        }

        setChanged(level, pos, state);
    }

    private boolean isEnergyFull(SimpleSolarPanelGeneratorBlockEntity entity) {
        return entity.energyHandler.getEnergyStored() == entity.energyHandler.getMaxEnergyStored();
    }

    private static void createEnergy(SimpleSolarPanelGeneratorBlockEntity entity) {
        entity.energyHandler.receiveEnergy(16, false);
    }

    public IEnergyStorage getEnergyStorage() {
        return energyHandler;
    }

    public void setEnergyLevel(int energy) {
        this.energyHandler.setEnergy(energy);
    }

    @Override
    public Component getDisplayName() { return Component.literal("Simple Solar Panel Generator"); }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int id, Inventory inventory, Player player) {
        ECNetworkMessages.sendToClients(new EnergySync(this.energyHandler.getEnergyStored(), getBlockPos()));
        return new SimpleSolarPanelGeneratorMenu(id, inventory, this, this.data);
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if(cap == ForgeCapabilities.ENERGY) {
            return lazyEnergyHandler.cast();
        }
        return super.getCapability(cap, side);
    }

    @Override
    public void onLoad() {
        super.onLoad();
        lazyEnergyHandler = LazyOptional.of(() -> energyHandler);
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        lazyEnergyHandler.invalidate();
    }

    @Override
    protected void saveAdditional(CompoundTag nbt) {
        nbt.putInt("simple_solar_panel_generator.energy", energyHandler.getEnergyStored());
        super.saveAdditional(nbt);
    }

    @Override
    public void load(CompoundTag nbt) {
        super.load(nbt);
        energyHandler.setEnergy(nbt.getInt("simple_solar_panel_generator.energy"));
    }
}
