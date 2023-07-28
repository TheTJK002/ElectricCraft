package com.tjk.electriccraft.item;

import com.tjk.electriccraft.item.ModItems;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class ModCreativeTabs {
    public static final CreativeModeTab ELECTRICCRAFT = new CreativeModeTab("electriccraft") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModItems.BASIC_CIRCUIT.get());
        }
    };
}
