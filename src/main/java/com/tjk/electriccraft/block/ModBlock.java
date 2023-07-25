package com.tjk.electriccraft.block;

import com.tjk.electriccraft.ElectricCraftMod;
import com.tjk.electriccraft.block.custom.CoalGeneratorBlock;
import com.tjk.electriccraft.block.custom.SimpleSolarPanelGeneratorBlock;
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

    public static final RegistryObject<Block> COAL_GENERATOR = registerBlock("coal_generator",
            () -> new CoalGeneratorBlock(BlockBehaviour.Properties.of(Material.STONE)
                    .requiresCorrectToolForDrops().sound(SoundType.METAL)
                    .strength(1.5f)), CreativeModeTab.TAB_BUILDING_BLOCKS);

    public static final RegistryObject<Block> LAVA_GENERATOR = registerBlock("lava_generator",
            () -> new Block(BlockBehaviour.Properties.of(Material.STONE)
                    .requiresCorrectToolForDrops().sound(SoundType.METAL)
                    .strength(1.5f)), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> CULINARY_GENERATOR = registerBlock("culinary_generator",
            () -> new Block(BlockBehaviour.Properties.of(Material.STONE)
                    .requiresCorrectToolForDrops().sound(SoundType.METAL)
                    .strength(1.5f)), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> MOB_DROP_GENERATOR = registerBlock("mob_drop_generator",
            () -> new Block(BlockBehaviour.Properties.of(Material.STONE)
                    .requiresCorrectToolForDrops().sound(SoundType.METAL)
                    .strength(1.5f)), CreativeModeTab.TAB_BUILDING_BLOCKS);

    public static final RegistryObject<Block> ELECTRIC_CRUSHER = registerBlock("electric_crusher",
            () -> new Block(BlockBehaviour.Properties.of(Material.STONE)
                    .requiresCorrectToolForDrops().sound(SoundType.METAL)
                    .strength(1.5f)), CreativeModeTab.TAB_BUILDING_BLOCKS);

    public static final RegistryObject<Block> ELECTRIC_FURNACE = registerBlock("electric_furnace",
            () -> new Block(BlockBehaviour.Properties.of(Material.STONE)
                    .requiresCorrectToolForDrops().sound(SoundType.METAL)
                    .strength(1.5f)), CreativeModeTab.TAB_BUILDING_BLOCKS);

    public static final RegistryObject<Block> ELECTRIC_SAWMILL = registerBlock("electric_sawmill",
            () -> new Block(BlockBehaviour.Properties.of(Material.STONE)
                    .requiresCorrectToolForDrops().sound(SoundType.METAL)
                    .strength(1.5f)), CreativeModeTab.TAB_BUILDING_BLOCKS);

    public static final RegistryObject<Block> ELECTRIC_PRESS = registerBlock("electric_press",
            () -> new Block(BlockBehaviour.Properties.of(Material.STONE)
                    .requiresCorrectToolForDrops().sound(SoundType.METAL)
                    .strength(1.5f)), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> SIMPLE_SOLAR_PANEL_GENERATOR = registerBlock("simple_solar_panel_generator",
            () -> new SimpleSolarPanelGeneratorBlock(BlockBehaviour.Properties.of(Material.STONE)
                    .requiresCorrectToolForDrops().sound(SoundType.METAL)
                    .strength(1.5f)), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> BASIC_SOLAR_PANEL_GENERATOR = registerBlock("basic_solar_panel_generator",
            () -> new SimpleSolarPanelGeneratorBlock(BlockBehaviour.Properties.of(Material.STONE)
                    .requiresCorrectToolForDrops().sound(SoundType.METAL)
                    .strength(1.5f)), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> ADVANCED_SOLAR_PANEL_GENERATOR = registerBlock("advanced_solar_panel_generator",
            () -> new SimpleSolarPanelGeneratorBlock(BlockBehaviour.Properties.of(Material.STONE)
                    .requiresCorrectToolForDrops().sound(SoundType.METAL)
                    .strength(1.5f)), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> ELITE_SOLAR_PANEL_GENERATOR = registerBlock("elite_solar_panel_generator",
            () -> new SimpleSolarPanelGeneratorBlock(BlockBehaviour.Properties.of(Material.STONE)
                    .requiresCorrectToolForDrops().sound(SoundType.METAL)
                    .strength(1.5f)), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> ULTIMATE_SOLAR_PANEL_GENERATOR = registerBlock("ultimate_solar_panel_generator",
            () -> new SimpleSolarPanelGeneratorBlock(BlockBehaviour.Properties.of(Material.STONE)
                    .requiresCorrectToolForDrops().sound(SoundType.METAL)
                    .strength(1.5f)), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> CREATIVE_SOLAR_PANEL_GENERATOR = registerBlock("creative_solar_panel_generator",
            () -> new SimpleSolarPanelGeneratorBlock(BlockBehaviour.Properties.of(Material.STONE)
                    .requiresCorrectToolForDrops().sound(SoundType.METAL)
                    .strength(1.5f)), CreativeModeTab.TAB_BUILDING_BLOCKS);
    public static final RegistryObject<Block> STEEL_BLOCK = registerBlock("steel_block",
            () -> new Block(BlockBehaviour.Properties.of(Material.STONE)
                    .requiresCorrectToolForDrops().sound(SoundType.METAL)
                    .strength(1.5f)), CreativeModeTab.TAB_BUILDING_BLOCKS);

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
    }
}
