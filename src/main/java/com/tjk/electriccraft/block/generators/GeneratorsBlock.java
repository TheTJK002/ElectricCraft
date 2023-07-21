package com.tjk.electriccraft.block.generators;

import com.tjk.electriccraft.block.ModBlock;
import com.tjk.electriccraft.block.custom.CoalGeneratorBlock;
import com.tjk.electriccraft.block.custom.LavaGeneratorBlock;
import net.minecraft.network.protocol.PacketUtils;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.RegistryObject;

import static com.tjk.electriccraft.block.ModBlock.registerBlock;

public class GeneratorsBlock {
    public static final RegistryObject<Block> COAL_GENERATOR = registerBlock("coal_generator",
            () -> new CoalGeneratorBlock(BlockBehaviour.Properties.of(Material.STONE)
                    .requiresCorrectToolForDrops().sound(SoundType.METAL)
                    .strength(0.5f)), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> LAVA_GENERATOR = registerBlock("lava_generator",
            () -> new LavaGeneratorBlock(BlockBehaviour.Properties.of(Material.STONE)
                    .requiresCorrectToolForDrops().sound(SoundType.METAL)
                    .strength(0.5f)), CreativeModeTab.TAB_BUILDING_BLOCKS);

    public static void register(IEventBus eventBus) {};
}
