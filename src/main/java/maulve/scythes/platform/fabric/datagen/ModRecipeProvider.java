package maulve.scythes.platform.fabric.datagen;

//? fabric {

import maulve.scythes.MaulvesScythes;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.recipes.RecipeProvider;
import org.jetbrains.annotations.NotNull;

//? if <= 1.20.4 {
/*import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.world.item.Items;
*///?} else {
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.world.item.Items;
import java.util.concurrent.CompletableFuture;
//?}

public class ModRecipeProvider extends FabricRecipeProvider {

	//? if <= 1.20.4 {
	/*public ModRecipeProvider(FabricPackOutput output) {
		super(output);
	}

	*///? } else {

	private final CompletableFuture<HolderLookup.Provider> registriesFuture;

    public ModRecipeProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
        this.registriesFuture = registriesFuture;
    }

	@Override
	protected RecipeProvider createRecipeProvider(HolderLookup.Provider registryLookup, RecipeOutput exporter) {
		return null;
	}

	//?}

	//? if <=1.21.1 {

	/*@Override
	public void buildRecipes(RecipeOutput recipeOutput) {
		ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, Items.COOKED_CHICKEN)
				.requires(Items.LAVA_BUCKET)
				.requires(Items.CHICKEN)
				.unlockedBy("has_lava_bucket", has(Items.LAVA_BUCKET))
				.unlockedBy("has_chicken", has(Items.CHICKEN))
				.save(recipeOutput, MaulvesScythes.MOD_ID + ":lava_chicken_recipe");
	}

	*///? } else {
	//@Override
    //protected void generateRecipes(RecipeOutput recipeOutput) {
		/*
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, Items.COOKED_CHICKEN)
                .requires(Items.LAVA_BUCKET)
                .requires(Items.CHICKEN)
                .unlockedBy("has_lava_bucket", has(Items.LAVA_BUCKET))
                .unlockedBy("has_chicken", has(Items.CHICKEN))
                .save(recipeOutput, MaulvesScythes.MOD_ID + ":lava_chicken_recipe");

		 */
    //}
    //?}

	@Override
	public @NotNull String getName() {
		return MaulvesScythes.MOD_ID + ":recipe_provider";
	}
}
//?}
