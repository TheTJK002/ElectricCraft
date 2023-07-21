package com.tjk.electriccraft.block;

import com.tjk.electriccraft.ElectricCraftMod;
import com.tjk.electriccraft.block.custom.*;
import com.tjk.electriccraft.block.generators.GeneratorsBlock;
import com.tjk.electriccraft.item.ModItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModBlock {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, ElectricCraftMod.MODID);

    public static final RegistryObject<Block> ELECTRIC_CRUSHER = registerBlock("electric_crusher",
            () -> new ElectricCrusherBlock(BlockBehaviour.Properties.of(Material.STONE)
                    .requiresCorrectToolForDrops().sound(SoundType.METAL)
                    .strength(0.5f)), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> ELECTRIC_FURNACE = registerBlock("electric_furnace",
            () -> new ElectricFurnaceBlock(BlockBehaviour.Properties.of(Material.STONE)
                    .requiresCorrectToolForDrops().sound(SoundType.METAL)
                    .strength(0.5f)), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> ELECTRIC_SAWMILL = registerBlock("electric_sawmill",
            () -> new ElectricSawmillBlock(BlockBehaviour.Properties.of(Material.STONE)
                    .requiresCorrectToolForDrops().sound(SoundType.METAL)
                    .strength(0.5f)), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> ELECTRIC_PRESS = registerBlock("electric_press",
            () -> new ElectricPressBlock(BlockBehaviour.Properties.of(Material.STONE)
                    .requiresCorrectToolForDrops().sound(SoundType.METAL)
                    .strength(0.5f)), CreativeModeTab.TAB_BUILDING_BLOCKS);

    public static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block, CreativeModeTab tab) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn, tab);
        return toReturn;
    }

    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, Supplier<T> block,
                                                                            CreativeModeTab tab) {
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties().tab(tab)));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
        GeneratorsBlock.register(eventBus);
    }
}
