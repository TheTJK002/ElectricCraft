package com.tjk.electriccraft.screen.render;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.Rect2i;
import net.minecraft.network.chat.Component;
import net.minecraftforge.energy.IEnergyStorage;


import java.util.List;

public class EnergyInfoAreaCoalGeneratorBlock extends InfoArea {
    private final IEnergyStorage energy;

    public EnergyInfoAreaCoalGeneratorBlock(int xMin, int yMin)  {
        this(xMin, yMin, null,8,64);
    }

    public EnergyInfoAreaCoalGeneratorBlock(int xMin, int yMin, IEnergyStorage energy)  {
        this(xMin, yMin, energy,19,45);
    }

    public EnergyInfoAreaCoalGeneratorBlock(int xMin, int yMin, IEnergyStorage energy, int width, int height)  {
        super(new Rect2i(xMin, yMin, width, height));
        this.energy = energy;
    }

    public List<Component> getTooltips() {
        return List.of(Component.literal(energy.getEnergyStored()+"/"+energy.getMaxEnergyStored()+" FE"));
    }

    @Override

    public void draw(PoseStack transform) {
        final int height = area.getHeight();
        int stored = (int)(height*(energy.getEnergyStored()/(float)energy.getMaxEnergyStored()));
        fillGradient(
                transform,
                area.getX(), area.getY()+(height-stored),
                area.getX() + area.getWidth(), area.getY() +area.getHeight(),
                0xffb51500, 0xff600b00
        );
    }

}
