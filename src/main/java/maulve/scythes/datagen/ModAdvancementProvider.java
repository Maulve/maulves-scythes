package maulve.scythes.datagen;

import maulve.scythes.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricAdvancementProvider;
import net.minecraft.advancement.Advancement;
import net.minecraft.advancement.AdvancementEntry;
import net.minecraft.advancement.AdvancementFrame;
import net.minecraft.advancement.criterion.InventoryChangedCriterion;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class ModAdvancementProvider extends FabricAdvancementProvider {
    public ModAdvancementProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(output, registryLookup);
    }

    @Override
    public void generateAdvancement(RegistryWrapper.WrapperLookup registryLookup, Consumer<AdvancementEntry> consumer) {
        Advancement.Builder.create()
                .parent(new AdvancementEntry(Identifier.of("minecraft", "story/root"), null))
                .display(ModItems.AMETHYST_SCYTHE,
                        Text.translatable("advancements.maulves-scythes.glimmering_reaper.title"),
                        Text.translatable("advancements.maulves-scythes.glimmering_reaper.description"),
                        null,
                        AdvancementFrame.TASK,
                        true,
                        true,
                        false)
                .criterion("got_item",
                        InventoryChangedCriterion.Conditions.items(ModItems.AMETHYST_SCYTHE)).build(consumer, "glimmering_reaper");
    }
}
