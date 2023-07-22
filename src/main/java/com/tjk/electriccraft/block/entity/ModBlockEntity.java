package com.tjk.electriccraft.block.entity;

import com.tjk.electriccraft.ElectricCraftMod;
import com.tjk.electriccraft.block.ModBlock;
import com.tjk.electriccraft.block.generators.GeneratorsBlock;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockEntity {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, ElectricCraftMod.MODID);

    public static final RegistryObject<BlockEntityType<CoalGeneratorBlockEntity>> COAL_GENERATOR =
            BLOCK_ENTITIES.register("coal_generator", () ->
                    BlockEntityType.Builder.of(CoalGeneratorBlockEntity::new,
                            GeneratorsBlock.COAL_GENERATOR.get()).build(null));

    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}