package maulve.scythes.platform.fabric.datagen;

//? fabric {

import maulve.scythes.item.ModItems;
//? if >= 1.21.4 {
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
//? } else {
/*import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
*///? }
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.model.ModelTemplates;
import org.jspecify.annotations.NonNull;

public class ModModelProvider extends FabricModelProvider {
	public ModModelProvider(FabricDataOutput output) {
		super(output);
	}

	@Override
	public void generateBlockStateModels(@NonNull BlockModelGenerators blockStateModelGenerator) {

	}

	@Override
	public void generateItemModels(@NonNull ItemModelGenerators itemModelGenerator) {
    	itemModelGenerator.generateFlatItem(ModItems.SCYTHED_AMETHYST.get(), ModelTemplates.FLAT_ITEM);
		itemModelGenerator.generateFlatItem(ModItems.REINFORCED_STICK.get(), ModelTemplates.FLAT_HANDHELD_ITEM);
	}
}
//? }
