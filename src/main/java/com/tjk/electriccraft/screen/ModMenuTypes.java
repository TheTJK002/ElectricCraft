package com.tjk.electriccraft.screen;

import com.tjk.electriccraft.ElectricCraftMod;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.network.IContainerFactory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModMenuTypes {
    public static final DeferredRegister<MenuType<?>> MENUS =
            DeferredRegister.create(ForgeRegistries.MENU_TYPES, ElectricCraftMod.MODID);

    public static final RegistryObject<MenuType<CoalGeneratorMenu>> COAL_GENERATOR_MENU = registerMenuType(CoalGeneratorMenu::new, "coal_generator_menu");
    public static final RegistryObject<MenuType<CulinaryGeneratorMenu>> CULINARY_GENERATOR_MENU = registerMenuType(CulinaryGeneratorMenu::new, "culinary_generator_menu");
    public static final RegistryObject<MenuType<BlockPlacerMenu>> BLOCK_PLACER_MENU = registerMenuType(BlockPlacerMenu::new, "block_placer_menu");
    public static final RegistryObject<MenuType<BlockBreakerMenu>> BLOCK_BREAKER_MENU = registerMenuType(BlockBreakerMenu::new, "block_breaker_menu");

    private static <T extends AbstractContainerMenu> RegistryObject<MenuType<T>> registerMenuType(IContainerFactory<T> factory, String name) {
        return MENUS.register(name, () -> IForgeMenuType.create(factory));
    };

    public static void register(IEventBus eventBus) {
        MENUS.register(eventBus);
    }
}
