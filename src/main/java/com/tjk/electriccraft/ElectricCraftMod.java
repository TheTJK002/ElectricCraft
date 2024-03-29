package com.tjk.electriccraft;

import com.mojang.logging.LogUtils;
import com.tjk.electriccraft.block.ModBlock;
import com.tjk.electriccraft.block.entity.ModBlockEntities;
import com.tjk.electriccraft.item.ModItems;
import com.tjk.electriccraft.network.ECNetworkMessages;
import com.tjk.electriccraft.recipe.ModRecipes;
import com.tjk.electriccraft.screen.*;
import com.tjk.electriccraft.world.feature.ModConfiguredFeatures;
import com.tjk.electriccraft.world.feature.ModPlacedFeatures;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod(ElectricCraftMod.MODID)
public class ElectricCraftMod {
    public static final String MODID = "electriccraft";
    private static final Logger LOGGER = LogUtils.getLogger();

    public ElectricCraftMod() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModItems.register(modEventBus);
        ModBlock.register(modEventBus);
        ModBlockEntities.register(modEventBus);
        ModMenuTypes.register(modEventBus);
        ModRecipes.register(modEventBus);

        //World Gen
        ModConfiguredFeatures.register(modEventBus);
        ModPlacedFeatures.register(modEventBus);

        modEventBus.addListener(this::commonSetup);
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        ECNetworkMessages.register();
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            MenuScreens.register(ModMenuTypes.COAL_GENERATOR_MENU.get(), CoalGeneratorScreen::new);
            MenuScreens.register(ModMenuTypes.CULINARY_GENERATOR_MENU.get(), CulinaryGeneratorScreen::new);
            MenuScreens.register(ModMenuTypes.BLOCK_PLACER_MENU.get(), BlockPlacerScreen::new);
            MenuScreens.register(ModMenuTypes.BLOCK_BREAKER_MENU.get(), BlockBreakerScreen::new);
        }
    }
}
