package com.tjk.electriccraft.world.feature;

import com.google.common.base.Suppliers;
import com.tjk.electriccraft.ElectricCraftMod;
import com.tjk.electriccraft.block.ModBlock;
import net.minecraft.core.Registry;
import net.minecraft.data.worldgen.features.OreFeatures;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;
import java.util.function.Supplier;

public class ModConfiguredFeatures {
    public static final DeferredRegister<ConfiguredFeature<?, ?>> CONFIGURED_FEATURES =
            DeferredRegister.create(Registry.CONFIGURED_FEATURE_REGISTRY, ElectricCraftMod.MODID);

    public static final Supplier<List<OreConfiguration.TargetBlockState>> OVERWORLD_SILICON_ORE = Suppliers.memoize(() -> List.of(
            OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES, ModBlock.SILICON_ORE.get().defaultBlockState()),
            OreConfiguration.target(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, ModBlock.DEEPSLATE_SILICON_ORE.get().defaultBlockState())));

    public static final RegistryObject<ConfiguredFeature<?,?>> SILICON_ORE = CONFIGURED_FEATURES.register("silicon_ore",
            () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(OVERWORLD_SILICON_ORE.get(), 11)));

    public static void register(IEventBus eventBus) { CONFIGURED_FEATURES.register(eventBus); }
}
