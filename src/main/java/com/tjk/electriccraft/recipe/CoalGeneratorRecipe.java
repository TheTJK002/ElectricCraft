package com.tjk.electriccraft.recipe;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.tjk.electriccraft.ElectricCraftMod;
import net.minecraft.core.NonNullList;
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
    private final ItemStack output;
    private final NonNullList<Ingredient> input;

    public CoalGeneratorRecipe(ResourceLocation id, ItemStack output, NonNullList<Ingredient> input) {
        this.id = id;
        this.output = output;
        this.input = input;
    }
    @Override
    public boolean matches(SimpleContainer simpleContainer, Level level) {
        if(level.isClientSide()) {
            return false;
        }
        return input.get(0).test(simpleContainer.getItem(1));
    }

    @Override
    public ItemStack assemble(SimpleContainer simpleContainer) {
        return output;
    }

    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return true;
    }

    @Override
    public ItemStack getResultItem() {
        return output.copy();
    }

    @Override
    public ResourceLocation getId() {
        return id;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return Serializer.ISTANCE;
    }

    @Override
    public RecipeType<?> getType() {
        return Type.ISTANCE;
    }

    public static class Type implements RecipeType<CoalGeneratorRecipe> {
        private Type () {}
        public static final Type ISTANCE = new Type();
        public static final String ID = "coal_generator";
    }

    public static class Serializer implements RecipeSerializer<CoalGeneratorRecipe> {
        public static final Serializer ISTANCE = new Serializer();
        public static final ResourceLocation ID =
                new ResourceLocation(ElectricCraftMod.MODID, "coal_generator");

        @Override
        public CoalGeneratorRecipe fromJson(ResourceLocation id, JsonObject json) {
            ItemStack output = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(json, "output"));
            JsonArray ingredients = GsonHelper.getAsJsonArray(json, "ingredients");
            NonNullList<Ingredient> inputs = NonNullList.withSize(1, Ingredient.EMPTY);

            for (int i = 0; i < inputs.size(); i++) {
                inputs.set(i, Ingredient.fromJson(ingredients.get(i)));
            }

            return new CoalGeneratorRecipe(id, output, inputs);
        }

        @Override
        public @Nullable CoalGeneratorRecipe fromNetwork(ResourceLocation id, FriendlyByteBuf buf) {
            NonNullList<Ingredient> inputs = NonNullList.withSize(buf.readInt(), Ingredient.EMPTY);

            for (int i = 0; i < inputs.size(); i++) {
                inputs.set(i, Ingredient.fromNetwork(buf));
            }

            ItemStack output = buf.readItem();
            return new CoalGeneratorRecipe(id, output, inputs);
        }

        @Override
        public void toNetwork(FriendlyByteBuf buf, CoalGeneratorRecipe recipe) {
            buf.writeInt(recipe.getIngredients().size());

            for (Ingredient ing : recipe.getIngredients()) {
                ing.toNetwork(buf);
            }

            buf.writeItemStack(recipe.getResultItem(), false);
        }
    }
}
