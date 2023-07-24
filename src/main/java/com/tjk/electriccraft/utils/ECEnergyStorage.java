package com.tjk.electriccraft.utils;

import net.minecraftforge.energy.EnergyStorage;

public abstract class ECEnergyStorage extends EnergyStorage {
    public ECEnergyStorage(int capacity) {
        super(capacity);
    }

    @Override
    public int extractEnergy(int maxExtract, boolean simulate) {
        int outputEnergy = super.extractEnergy(maxExtract, simulate);
        if(outputEnergy != 0) {
            onEnergyChanged();
        }
        return outputEnergy;
    }

    @Override
    public int receiveEnergy(int maxReceive, boolean simulate) {
        int inputEnergy = super.receiveEnergy(maxReceive, simulate);
        if(inputEnergy != 0) {
            onEnergyChanged();
        }
        return inputEnergy;
    }

    public int setEnergy(int energy) {
        this.energy = energy;
        return energy;
    }

    public abstract void onEnergyChanged();

}
