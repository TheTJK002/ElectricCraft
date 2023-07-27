package com.tjk.electriccraft.item;

import ca.weblite.objc.Proxy;
import com.tjk.electriccraft.ElectricCraftMod;
import net.minecraft.world.item.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, ElectricCraftMod.MODID);

    public static final RegistryObject<Item> EMPTY_UPGRADE = ITEMS.register("empty_upgrade",
            () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MISC).stacksTo(8)));
    public static final RegistryObject<Item> SPEED_UPGRADE_TIER_1 = ITEMS.register("speed_upgrade_tier_1",
            () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MISC).stacksTo(8)));
    public static final RegistryObject<Item> SPEED_UPGRADE_TIER_2 = ITEMS.register("speed_upgrade_tier_2",
            () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MISC).stacksTo(8)));
    public static final RegistryObject<Item> ENERGY_UPGRADE_TIER_1 = ITEMS.register("energy_upgrade_tier_1",
            () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MISC).stacksTo(8)));
    public static final RegistryObject<Item> ENERGY_UPGRADE_TIER_2 = ITEMS.register("energy_upgrade_tier_2",
            () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MISC).stacksTo(8)));
    public static final RegistryObject<Item> STONE_HAMMER = ITEMS.register("stone_hammer",
            () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MISC).stacksTo(1).durability(131)));
    public static final RegistryObject<Item> SAW_BLADE = ITEMS.register("saw_blade",
            () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MISC)));
    public static final RegistryObject<Item> IRON_PLATE = ITEMS.register("iron_plate",
            () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MISC)));
    public static final RegistryObject<Item> GOLD_PLATE = ITEMS.register("gold_plate",
            () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MISC)));
    public static final RegistryObject<Item> DIAMOND_PLATE = ITEMS.register("diamond_plate",
            () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MISC)));
    public static final RegistryObject<Item> SIMPLE_SOLAR_PANEL = ITEMS.register("simple_solar_panel",
            () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MISC)));
    public static final RegistryObject<Item> STEEL_INGOT = ITEMS.register("steel_ingot",
            () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MISC)));
    public static final RegistryObject<Item> SILICON = ITEMS.register("silicon",
            () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MISC)));
    public static final RegistryObject<Item> BASIC_CIRCUIT = ITEMS.register("basic_circuit",
            () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MISC)));
    public static final RegistryObject<Item> ADVANCED_CIRCUIT = ITEMS.register("advanced_circuit",
            () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MISC)));
    public static final RegistryObject<Item> ELITE_CIRCUIT = ITEMS.register("elite_circuit",
            () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MISC)));
    public static final RegistryObject<Item> ULTIMATE_CIRCUIT = ITEMS.register("ultimate_circuit",
            () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MISC)));


    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
