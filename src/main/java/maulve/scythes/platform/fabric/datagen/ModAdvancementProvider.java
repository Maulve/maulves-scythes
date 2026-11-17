package maulve.scythes.platform.fabric.datagen;

//? fabric {

import maulve.scythes.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricAdvancementProvider;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.advancements.AdvancementType;
import net.minecraft.advancements.criterion.InventoryChangeTrigger;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.advancements.AdvancementSubProvider;
import net.minecraft.network.chat.Component;
import org.jspecify.annotations.NonNull;

import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class ModAdvancementProvider extends FabricAdvancementProvider {
	//? if >= 1.21.1 {
	protected ModAdvancementProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registryLookup) {
		super(output, registryLookup);
	}
	//? } else {
	/*protected ModAdvancementProvider(FabricDataOutput output) {
		super(output);
	}
	*///? }

	//? if < 1.21.1 {
	/*@Override
	public void generateAdvancement(Consumer<AdvancementHolder> consumer) {
		customGenerateAdvancement(consumer);
	}
	*///? } else {
	@Override
	public void generateAdvancement(HolderLookup.@NonNull Provider registryLookup, @NonNull Consumer<AdvancementHolder> consumer) {
		customGenerateAdvancement(consumer);
	}
	//? }

	private static void customGenerateAdvancement(@NonNull Consumer<AdvancementHolder> consumer) {
		Advancement.Builder.advancement()
				.parent(AdvancementSubProvider.createPlaceholder("minecraft:end/find_end_city"))
				.display(
						ModItems.AMETHYST_SCYTHE.get(),
						Component.translatable("advancements.maulves_scythes.amaranthine_death.title"),
						Component.translatable("advancements.maulves_scythes.amaranthine_death.description"),
						null,
						AdvancementType.CHALLENGE,
						true, // show toast popup
						true, // announce in chat
						false) // hidden until unlocked
				.addCriterion("has_amethyst_scythe",
						InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.AMETHYST_SCYTHE.get()))
				.save(consumer, "amaranthine_death");
	}
}

//? }
