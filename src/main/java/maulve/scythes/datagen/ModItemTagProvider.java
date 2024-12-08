package maulve.scythes.datagen;

import maulve.scythes.item.ModItems;
import maulve.scythes.util.ModItemTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends FabricTagProvider.ItemTagProvider {
    public ModItemTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
        super(output, completableFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        getOrCreateTagBuilder(ModItemTags.SCYTHES)
                .add(ModItems.AMETHYST_SCYTHE)
                .add(ModItems.NETHERITE_SCYTHE)
                .add(ModItems.DIAMOND_SCYTHE)
                .add(ModItems.IRON_SCYTHE);

        getOrCreateTagBuilder(ItemTags.SWORD_ENCHANTABLE)
                .addTag(ModItemTags.SCYTHES);

        getOrCreateTagBuilder(ItemTags.SHARP_WEAPON_ENCHANTABLE)
                .addTag(ModItemTags.SCYTHES);
    }
}
