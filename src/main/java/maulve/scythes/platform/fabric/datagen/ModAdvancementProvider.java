package maulve.scythes.platform.fabric.datagen;

import maulve.scythes.platform.fabric.FabricModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricAdvancementProvider;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.advancements.AdvancementType;
import net.minecraft.advancements.criterion.InventoryChangeTrigger;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.advancements.AdvancementSubProvider;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import org.jspecify.annotations.NonNull;

import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class ModAdvancementProvider extends FabricAdvancementProvider {
	protected ModAdvancementProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registryLookup) {
		super(output, registryLookup);
	}

	@Override
	public void generateAdvancement(HolderLookup.@NonNull Provider registryLookup, @NonNull Consumer<AdvancementHolder> consumer) {
		Advancement.Builder.advancement()
				.parent(AdvancementSubProvider.createPlaceholder("minecraft:end/find_end_city"))
				.display(
						FabricModItems.AMETHYST_SCYTHE,
						Component.translatable("advancements.maulves_scythes.amaranthine_death.title"),
						Component.translatable("advancements.maulves_scythes.amaranthine_death.description"),
						null,
						AdvancementType.CHALLENGE,
						true, // show toast popup
						true, // announce in chat
						false) // hidden until unlocked
				.addCriterion("has_amethyst_scythe",
						InventoryChangeTrigger.TriggerInstance.hasItems(FabricModItems.AMETHYST_SCYTHE))
				.save(consumer, "amaranthine_death");
	}
}
