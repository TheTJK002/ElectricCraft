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
    public static final RegistryObject<Item> STEEL_PICKAXE = ITEMS.register("steel_pickaxe",
            () -> new PickaxeItem(ModTiers.STEEL, 2, -2.8f,
                    new Item.Properties().tab(CreativeModeTab.TAB_MISC)));
    public static final RegistryObject<Item> STEEL_SWORD = ITEMS.register("steel_sword",
            () -> new SwordItem(ModTiers.STEEL, 4, -2.4f,
                    new Item.Properties().tab(CreativeModeTab.TAB_MISC)));
    public static final RegistryObject<Item> STEEL_AXE = ITEMS.register("steel_axe",
            () -> new AxeItem(ModTiers.STEEL, 7, -3.1f,
                    new Item.Properties().tab(CreativeModeTab.TAB_MISC)));
    public static final RegistryObject<Item> STEEL_SHOVEL = ITEMS.register("steel_shovel",
            () -> new ShovelItem(ModTiers.STEEL, 2.5f, -3.1f,
                    new Item.Properties().tab(CreativeModeTab.TAB_MISC)));
    public static final RegistryObject<Item> STEEL_HOE = ITEMS.register("steel_hoe",
            () -> new HoeItem(ModTiers.STEEL, -1, -3.0f,
                    new Item.Properties().tab(CreativeModeTab.TAB_MISC)));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
