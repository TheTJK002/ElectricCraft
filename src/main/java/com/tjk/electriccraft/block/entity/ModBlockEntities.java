package com.tjk.electriccraft.block.entity;

import com.tjk.electriccraft.ElectricCraftMod;
import com.tjk.electriccraft.block.ModBlock;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, ElectricCraftMod.MODID);

    public static final RegistryObject<BlockEntityType> COAL_GENERATOR =
            BLOCK_ENTITIES.register("coal_generator", () ->
                    BlockEntityType.Builder.of(CoalGeneratorBlockEntity::new,
                            ModBlock.COAL_GENERATOR.get()).build(null));
    public static final RegistryObject<BlockEntityType> SIMPLE_SOLAR_PANEL_GENERATOR =
            BLOCK_ENTITIES.register("simple_solar_panel_generator", () ->
                    BlockEntityType.Builder.of(CoalGeneratorBlockEntity::new,
                            ModBlock.SIMPLE_SOLAR_PANEL_GENERATOR.get()).build(null));

    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}
