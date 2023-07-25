package com.tjk.electriccraft.network;

import com.tjk.electriccraft.block.entity.CoalGeneratorBlockEntity;
import com.tjk.electriccraft.block.entity.SimpleSolarPanelGeneratorBlockEntity;
import com.tjk.electriccraft.screen.CoalGeneratorMenu;
import com.tjk.electriccraft.screen.SimpleSolarPanelGeneratorMenu;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class EnergySync {
    private final int energy;
    private final BlockPos pos;

    public EnergySync(int energy, BlockPos pos){
        this.energy = energy;
        this.pos = pos;
    }

    public EnergySync(FriendlyByteBuf buf){
        this.energy = buf.readInt();
        this.pos = buf.readBlockPos();
    }

    public void toBytes(FriendlyByteBuf buf){
        buf.writeInt(energy);
        buf.writeBlockPos(pos);
    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier){
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            if (Minecraft.getInstance().level.getBlockEntity(pos) instanceof CoalGeneratorBlockEntity blockEntity){
                blockEntity.setEnergyLevel(energy);

                if (Minecraft.getInstance().player.containerMenu instanceof CoalGeneratorMenu menu && menu.getBlockEntity().getBlockPos().equals(pos)){
                    blockEntity.setEnergyLevel(energy);
                }
            }
            if (Minecraft.getInstance().level.getBlockEntity(pos) instanceof SimpleSolarPanelGeneratorBlockEntity blockEntity){
                blockEntity.setEnergyLevel(energy);

                if (Minecraft.getInstance().player.containerMenu instanceof SimpleSolarPanelGeneratorMenu menu && menu.getBlockEntity().getBlockPos().equals(pos)){
                    blockEntity.setEnergyLevel(energy);
                }
            }
        });
        return true;
    }
}
