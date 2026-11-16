package maulve.scythes.platform.fabric.datagen;

//? fabric {

import maulve.scythes.item.ModItemTags;
import maulve.scythes.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.tags.ItemTags;
import org.jspecify.annotations.NonNull;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends FabricTagProvider.ItemTagProvider {
	public ModItemTagProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
		super(output, registriesFuture);
	}

	@Override
	protected void addTags(HolderLookup.@NonNull Provider wrapperLookup) {
    	valueLookupBuilder(ModItemTags.SCYTHES)
				.add(ModItems.IRON_SCYTHE.get())
				.add(ModItems.DIAMOND_SCYTHE.get())
				.add(ModItems.NETHERITE_SCYTHE.get())
				.add(ModItems.AMETHYST_SCYTHE.get());

		valueLookupBuilder(ModItemTags.AMETHYST_REPAIR)
				.add(ModItems.SCYTHED_AMETHYST.get());

		valueLookupBuilder(ItemTags.SHARP_WEAPON_ENCHANTABLE)
				.addTag(ModItemTags.SCYTHES);

		valueLookupBuilder(ItemTags.MELEE_WEAPON_ENCHANTABLE)
				.addTag(ModItemTags.SCYTHES);
	}
}

//? }
