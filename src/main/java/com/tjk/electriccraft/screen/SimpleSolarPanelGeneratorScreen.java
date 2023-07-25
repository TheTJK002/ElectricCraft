package com.tjk.electriccraft.screen;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.tjk.electriccraft.ElectricCraftMod;
import com.tjk.electriccraft.screen.render.EnergyInfoAreaSolarPanelGeneratorBlock;
import com.tjk.electriccraft.utils.MouseUtil;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

import java.util.Optional;

public class SimpleSolarPanelGeneratorScreen extends AbstractContainerScreen<SimpleSolarPanelGeneratorMenu> {
    private static final ResourceLocation TEXTURE =
            new ResourceLocation(ElectricCraftMod.MODID, "textures/gui/solar_panel_generator_gui.png");
    private EnergyInfoAreaSolarPanelGeneratorBlock energyInfoArea;
    public SimpleSolarPanelGeneratorScreen(SimpleSolarPanelGeneratorMenu menu, Inventory inventory, Component component) {
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
        energyInfoArea.draw(stack);
    }

    private void renderEnergyAreaTooltips(PoseStack stack, int mouseX, int mouseY, int x, int y) {
        if (isMouseAboveArea(mouseX, mouseY, x, y, 79, 19, 18, 45))
            renderTooltip(stack, energyInfoArea.getTooltips(), Optional.empty(), mouseX - x, mouseY - y);
    }

    private void assignEnergyInfoArea() {
        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;
        energyInfoArea = new EnergyInfoAreaSolarPanelGeneratorBlock(x + 79, y + 19, menu.blockEntity.getEnergyStorage());
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
