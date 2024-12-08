package maulve.scythes.datagen;

import maulve.scythes.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;

import java.util.function.Consumer;

public class ModRecipeProvider extends FabricRecipeProvider {
    public ModRecipeProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generate(Consumer<RecipeJsonProvider> exporter) {
        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.SCYTHED_AMETHYST)
                .input(Items.AMETHYST_SHARD)
                .input(Items.GOLD_INGOT)
                .input(Items.CALCITE)
                .criterion(hasItem(Items.AMETHYST_SHARD), conditionsFromItem(Items.AMETHYST_SHARD))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.REINFORCED_STICK)
                .pattern("O")
                .pattern("X")
                .input('X', Items.END_ROD)
                .input('O', Items.BLACKSTONE)
                .criterion(hasItem(Items.END_ROD), conditionsFromItem(Items.END_ROD))
                .offerTo(exporter);

        offerScythe(exporter, ModItems.AMETHYST_SCYTHE, ModItems.SCYTHED_AMETHYST, true);
        offerScythe(exporter, ModItems.DIAMOND_SCYTHE, Items.DIAMOND, false);
        offerScythe(exporter, ModItems.IRON_SCYTHE, Items.IRON_INGOT, false);

        offerNetheriteUpgradeRecipe(exporter, ModItems.DIAMOND_SCYTHE, RecipeCategory.COMBAT, ModItems.NETHERITE_SCYTHE);
    }

    private void offerScythe(Consumer<RecipeJsonProvider> exporter, Item scythe, Item material, boolean useReinforcedStick) {
        ShapedRecipeJsonBuilder builder = ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, scythe)
                .pattern(" ##")
                .pattern("# I")
                .pattern("  I")
                .input('#', material)
                .criterion(hasItem(material), conditionsFromItem(material));

        if (useReinforcedStick) {
            builder.input('I', ModItems.REINFORCED_STICK);
        } else {
            builder.input('I', Items.STICK);
        }
        builder.offerTo(exporter);
    }


}