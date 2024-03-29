package com.tjk.electriccraft.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.tjk.electriccraft.ElectricCraftMod;
import com.tjk.electriccraft.screen.render.EnergyInfoAreaCoalGeneratorBlock;
import com.tjk.electriccraft.screen.render.EnergyInfoAreaCulinaryGeneratorBlock;
import com.tjk.electriccraft.utils.MouseUtil;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

import java.util.Optional;

public class CulinaryGeneratorScreen extends AbstractContainerScreen<CulinaryGeneratorMenu> {
    private static final ResourceLocation TEXTURE =
            new ResourceLocation(ElectricCraftMod.MODID, "textures/gui/fluid_generator_gui.png");

    private EnergyInfoAreaCulinaryGeneratorBlock energyInfoArea;
    public CulinaryGeneratorScreen(CulinaryGeneratorMenu menu, Inventory inventory, Component component) {
        super(menu, inventory, component);
    }

    @Override
    protected void init() {
        super.init();
        assignEnergyInfoArea();
    }

    @Override
    protected void renderLabels(PoseStack stack, int mouseX, int mouseY) {
        super.renderLabels(stack, mouseX, mouseY);
        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;

        renderEnergyAreaTooltips(stack, mouseX, mouseY, x, y);
    }

    @Override
    protected void renderBg(PoseStack stack, float partialTick, int mouseX, int mouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F,1.0F,1.0F,1.0F);
        RenderSystem.setShaderTexture(0, TEXTURE);
        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;

        this.blit(stack, x, y, 0,0, imageWidth, imageHeight);
        if(menu.isCulinaryGenerator()) {
            int progress = menu.getScaleProgress();
            blit(stack, x + 81, y + 41 + 14 - progress, 197, 14 - progress, 14, progress);
        }
        energyInfoArea.draw(stack);
    }

    private void renderEnergyAreaTooltips(PoseStack pPoseStack, int pMouseX, int pMouseY, int x, int y) {
        if (isMouseAboveArea(pMouseX, pMouseY, x, y, 10, 13, 19, 45))
            renderTooltip(pPoseStack, energyInfoArea.getTooltips(), Optional.empty(), pMouseX - x, pMouseY - y);
    }

    private void assignEnergyInfoArea() {
        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;
        energyInfoArea = new EnergyInfoAreaCulinaryGeneratorBlock(x + 10, y + 13, menu.blockEntity.getEnergyStorage());
    }

    @Override
    public void render(PoseStack stack, int mouseX, int mouseY, float delta) {
        renderBackground(stack);
        super.render(stack, mouseX, mouseY, delta);
        renderTooltip(stack, mouseX, mouseY);
    }

    private boolean isMouseAboveArea(int mouseX, int mouseY, int x, int y, int offsetX, int offsetY, int width, int height){
        return MouseUtil.isMouseOver(mouseX, mouseY, x + offsetX, y + offsetY, width, height);
    }
}
