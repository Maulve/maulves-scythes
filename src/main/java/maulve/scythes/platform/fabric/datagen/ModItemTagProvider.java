package maulve.scythes.platform.fabric.datagen;

import maulve.scythes.item.ModItemTags;
import maulve.scythes.platform.fabric.FabricModItems;
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
				.add(FabricModItems.IRON_SCYTHE)
				.add(FabricModItems.DIAMOND_SCYTHE)
				.add(FabricModItems.NETHERITE_SCYTHE)
				.add(FabricModItems.AMETHYST_SCYTHE);

		valueLookupBuilder(ModItemTags.AMETHYST_REPAIR)
				.add(FabricModItems.SCYTHED_AMETHYST);

		valueLookupBuilder(ItemTags.SHARP_WEAPON_ENCHANTABLE)
				.addTag(ModItemTags.SCYTHES);

		valueLookupBuilder(ItemTags.MELEE_WEAPON_ENCHANTABLE)
				.addTag(ModItemTags.SCYTHES);
	}
}
