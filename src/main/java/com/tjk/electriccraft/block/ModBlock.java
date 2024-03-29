package com.tjk.electriccraft.block;

import com.tjk.electriccraft.ElectricCraftMod;
import com.tjk.electriccraft.block.custom.BlockBreakerBlock;
import com.tjk.electriccraft.block.custom.BlockPlacerBlock;
import com.tjk.electriccraft.item.ModCreativeTabs;
import com.tjk.electriccraft.block.custom.CoalGeneratorBlock;
import com.tjk.electriccraft.block.custom.CulinaryGeneratorBlock;
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
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, ElectricCraftMod.MODID);

    public static final RegistryObject<Block> COAL_GENERATOR = registerBlock("coal_generator", () -> new CoalGeneratorBlock(BlockBehaviour.Properties.of(Material.STONE).requiresCorrectToolForDrops().sound(SoundType.METAL).strength(1.5f)), ModCreativeTabs.ELECTRICCRAFT);
    public static final RegistryObject<Block> LAVA_GENERATOR = registerBlock("lava_generator", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).requiresCorrectToolForDrops().sound(SoundType.METAL).strength(1.5f)), ModCreativeTabs.ELECTRICCRAFT);
    public static final RegistryObject<Block> CULINARY_GENERATOR = registerBlock("culinary_generator", () -> new CulinaryGeneratorBlock(BlockBehaviour.Properties.of(Material.STONE).requiresCorrectToolForDrops().sound(SoundType.METAL).strength(1.5f)), ModCreativeTabs.ELECTRICCRAFT);
    public static final RegistryObject<Block> MOB_DROP_GENERATOR = registerBlock("mob_drop_generator", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).requiresCorrectToolForDrops().sound(SoundType.METAL).strength(1.5f)), ModCreativeTabs.ELECTRICCRAFT);
    public static final RegistryObject<Block> ELECTRIC_CRUSHER = registerBlock("electric_crusher", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).requiresCorrectToolForDrops().sound(SoundType.METAL).strength(1.5f)), ModCreativeTabs.ELECTRICCRAFT);
    public static final RegistryObject<Block> ELECTRIC_FURNACE = registerBlock("electric_furnace", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).requiresCorrectToolForDrops().sound(SoundType.METAL).strength(1.5f)), ModCreativeTabs.ELECTRICCRAFT);
    public static final RegistryObject<Block> ELECTRIC_SAWMILL = registerBlock("electric_sawmill", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).requiresCorrectToolForDrops().sound(SoundType.METAL).strength(1.5f)), ModCreativeTabs.ELECTRICCRAFT);
    public static final RegistryObject<Block> SIMPLE_SOLAR_PANEL_GENERATOR = registerBlock("simple_solar_panel_generator", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).requiresCorrectToolForDrops().sound(SoundType.METAL).strength(1.5f)), ModCreativeTabs.ELECTRICCRAFT);
    public static final RegistryObject<Block> BASIC_SOLAR_PANEL_GENERATOR = registerBlock("basic_solar_panel_generator", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).requiresCorrectToolForDrops().sound(SoundType.METAL).strength(1.5f)), ModCreativeTabs.ELECTRICCRAFT);
    public static final RegistryObject<Block> ADVANCED_SOLAR_PANEL_GENERATOR = registerBlock("advanced_solar_panel_generator", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).requiresCorrectToolForDrops().sound(SoundType.METAL).strength(1.5f)), ModCreativeTabs.ELECTRICCRAFT);
    public static final RegistryObject<Block> ELITE_SOLAR_PANEL_GENERATOR = registerBlock("elite_solar_panel_generator", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).requiresCorrectToolForDrops().sound(SoundType.METAL).strength(1.5f)), ModCreativeTabs.ELECTRICCRAFT);
    public static final RegistryObject<Block> ULTIMATE_SOLAR_PANEL_GENERATOR = registerBlock("ultimate_solar_panel_generator", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).requiresCorrectToolForDrops().sound(SoundType.METAL).strength(1.5f)), ModCreativeTabs.ELECTRICCRAFT);
    public static final RegistryObject<Block> CREATIVE_SOLAR_PANEL_GENERATOR = registerBlock("creative_solar_panel_generator", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).requiresCorrectToolForDrops().sound(SoundType.METAL).strength(1.5f)), ModCreativeTabs.ELECTRICCRAFT);
    public static final RegistryObject<Block> SIMPLE_LUNAR_PANEL_GENERATOR = registerBlock("simple_lunar_panel_generator", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).requiresCorrectToolForDrops().sound(SoundType.METAL).strength(1.5f)), ModCreativeTabs.ELECTRICCRAFT);
    public static final RegistryObject<Block> BASIC_LUNAR_PANEL_GENERATOR = registerBlock("basic_lunar_panel_generator", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).requiresCorrectToolForDrops().sound(SoundType.METAL).strength(1.5f)), ModCreativeTabs.ELECTRICCRAFT);
    public static final RegistryObject<Block> ADVANCED_LUNAR_PANEL_GENERATOR = registerBlock("advanced_lunar_panel_generator", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).requiresCorrectToolForDrops().sound(SoundType.METAL).strength(1.5f)), ModCreativeTabs.ELECTRICCRAFT);
    public static final RegistryObject<Block> ELITE_LUNAR_PANEL_GENERATOR = registerBlock("elite_lunar_panel_generator", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).requiresCorrectToolForDrops().sound(SoundType.METAL).strength(1.5f)), ModCreativeTabs.ELECTRICCRAFT);
    public static final RegistryObject<Block> ULTIMATE_LUNAR_PANEL_GENERATOR = registerBlock("ultimate_lunar_panel_generator", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).requiresCorrectToolForDrops().sound(SoundType.METAL).strength(1.5f)), ModCreativeTabs.ELECTRICCRAFT);
    public static final RegistryObject<Block> CREATIVE_LUNAR_PANEL_GENERATOR = registerBlock("creative_lunar_panel_generator", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).requiresCorrectToolForDrops().sound(SoundType.METAL).strength(1.5f)), ModCreativeTabs.ELECTRICCRAFT);
    public static final RegistryObject<Block> STEEL_BLOCK = registerBlock("steel_block", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).requiresCorrectToolForDrops().sound(SoundType.METAL).strength(1.5f)), ModCreativeTabs.ELECTRICCRAFT);
    public static final RegistryObject<Block> SILICON_ORE = registerBlock("silicon_ore", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(1.5f)), ModCreativeTabs.ELECTRICCRAFT);
    public static final RegistryObject<Block> DEEPSLATE_SILICON_ORE = registerBlock("deepslate_silicon_ore", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(1.5f)), ModCreativeTabs.ELECTRICCRAFT);
    public static final RegistryObject<Block> BLOCK_PLACER = registerBlock("block_placer", () -> new BlockPlacerBlock(BlockBehaviour.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(1.5f)), ModCreativeTabs.ELECTRICCRAFT);
    public static final RegistryObject<Block> BLOCK_BREAKER = registerBlock("block_breaker", () -> new BlockBreakerBlock(BlockBehaviour.Properties.of(Material.STONE).requiresCorrectToolForDrops().strength(1.5f)), ModCreativeTabs.ELECTRICCRAFT);

    public static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block, CreativeModeTab tab) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn, tab);
        return toReturn;
    }

    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, Supplier<T> block, CreativeModeTab tab) {
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties().tab(tab)));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
