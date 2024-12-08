package maulve.scythes.datagen;

import maulve.scythes.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricAdvancementProvider;
import net.minecraft.advancement.Advancement;
import net.minecraft.advancement.AdvancementFrame;
import net.minecraft.advancement.criterion.InventoryChangedCriterion;
import net.minecraft.text.Text;

import java.util.function.Consumer;

public class ModAdvancementProvider extends FabricAdvancementProvider {
    public ModAdvancementProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateAdvancement(Consumer<Advancement> consumer) {
        Advancement.Builder.create()
                .display(ModItems.AMETHYST_SCYTHE,
                        Text.translatable("advancements.maulves-scythes.glimmering_reaper.title"),
                        Text.translatable("advancements.maulves-scythes.glimmering_reaper.description"),
                        null,
                        AdvancementFrame.TASK,
                        true,
                        true,
                        false)
                .criterion("has_item", InventoryChangedCriterion.Conditions.items(ModItems.AMETHYST_SCYTHE))
                .build(consumer, "maulves-scythes:glimmering_reaper");
    }
}
