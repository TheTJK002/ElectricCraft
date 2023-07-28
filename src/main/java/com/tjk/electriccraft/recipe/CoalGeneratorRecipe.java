package com.tjk.electriccraft.recipe;

import com.google.gson.JsonObject;
import com.tjk.electriccraft.ElectricCraftMod;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

public class CoalGeneratorRecipe implements Recipe<SimpleContainer> {
    private final ResourceLocation id;
    public final int output;
    public final Ingredient input;

    public CoalGeneratorRecipe(ResourceLocation id, int output, Ingredient input) {
        this.id = id;
        this.output = output;
        this.input = input;
    }

    @Override
    public boolean matches(SimpleContainer simpleContainer, Level level) {
        if (level.isClientSide()) {
            return false;
        }
        return input.test(simpleContainer.getItem(0));
    }

    @Override
    public ItemStack assemble(SimpleContainer simpleContainer) {
        return ItemStack.EMPTY;
    }

    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return true;
    }

    @Override
    public ItemStack getResultItem() {
        return ItemStack.EMPTY.copy();
    }

    @Override
    public ResourceLocation getId() {
        return id;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return Serializer.INSTANCE;
    }

    @Override
    public RecipeType<?> getType() {
        return Type.INSTANCE;
    }

    public static class Type implements RecipeType<CoalGeneratorRecipe> {
        public static final Type INSTANCE = new Type();
        public static final String ID = "coal_generator";
        private Type() {
        }
    }

    public static class Serializer implements RecipeSerializer<CoalGeneratorRecipe> {
        public static final Serializer INSTANCE = new Serializer();
        public static final ResourceLocation ID = new ResourceLocation(ElectricCraftMod.MODID, "coal_generator");

        @Override
        public CoalGeneratorRecipe fromJson(ResourceLocation id, JsonObject json) {
            Ingredient input = Ingredient.fromJson(json.getAsJsonObject("ingredient"));
            int output = json.get("output").getAsInt();
            return new CoalGeneratorRecipe(id, output, input);
        }

        @Override
        public @Nullable CoalGeneratorRecipe fromNetwork(ResourceLocation id, FriendlyByteBuf buf) {
            int output = buf.readInt();
            Ingredient input = Ingredient.fromNetwork(buf);
            return new CoalGeneratorRecipe(id, output, input);
        }

        @Override
        public void toNetwork(FriendlyByteBuf buf, CoalGeneratorRecipe recipe) {
            buf.writeInt(recipe.output);
            recipe.input.toNetwork(buf);
        }
    }
}
