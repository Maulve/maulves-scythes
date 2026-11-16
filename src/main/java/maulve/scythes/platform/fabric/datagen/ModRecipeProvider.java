package maulve.scythes.platform.fabric.datagen;

//? fabric {

import maulve.scythes.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.SmithingTransformRecipeBuilder;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import org.jspecify.annotations.NonNull;

import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends FabricRecipeProvider {

	public ModRecipeProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
		super(output, registriesFuture);
	}

	@Override
	protected @NonNull RecipeProvider createRecipeProvider(HolderLookup.@NonNull Provider registryLookup, @NonNull RecipeOutput output) {
		return new RecipeProvider(registryLookup, output) {
			@Override
			public void buildRecipes() {
				offerScythe(output, ModItems.IRON_SCYTHE.get(), Items.IRON_INGOT, Items.STICK);
				offerScythe(output, ModItems.DIAMOND_SCYTHE.get(), Items.DIAMOND, Items.STICK);
				// TODO
				offerScythe(output, ModItems.AMETHYST_SCYTHE.get(), ModItems.SCYTHED_AMETHYST.get(), ModItems.REINFORCED_STICK.get());

				SmithingTransformRecipeBuilder.smithing(
						Ingredient.of(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE),
						Ingredient.of(ModItems.DIAMOND_SCYTHE.get()),
						Ingredient.of(Items.NETHERITE_INGOT),
						RecipeCategory.COMBAT,
						ModItems.NETHERITE_SCYTHE.get()
				).unlocks("has_netherite_ingot", this.has(Items.NETHERITE_INGOT))
						.save(output, getItemName(ModItems.NETHERITE_SCYTHE.get()) + "_smithing");
			}

			private void offerScythe(RecipeOutput output, Item scythe, Item bladeMaterial, Item stickMaterial) {
				shaped(RecipeCategory.COMBAT, scythe)
						.pattern(" ##")
						.pattern("# I")
						.pattern("  I")
						.define('#', bladeMaterial)
						.define('I', stickMaterial)
						.unlockedBy(getHasName(bladeMaterial), has(bladeMaterial))
						.save(output);
			}
		};
	}

	@Override
	public @NonNull String getName() {
		return "MaulvesScythesRecipeProvider";
	}
}

//?}
