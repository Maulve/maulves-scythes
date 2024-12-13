package maulve.scythes.enchantment;

import maulve.scythes.util.ModItemTags;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.resource.featuretoggle.FeatureSet;
import net.minecraft.resource.featuretoggle.ToggleableFeature;

import java.util.Optional;

public class LifeDrainEnchantment extends Enchantment implements ToggleableFeature {
    private static final float amount = 0.5f;

    private static Properties getEnchantmentProperties() {
        final TagKey<Item> tagKey = ModItemTags.SCYTHES;
        final int weight = Enchantments.FIRE_ASPECT.getWeight();
        final int maxLevel = 3;
        final Cost minCost = new Cost(10, 20);
        final Cost maxCost = new Cost(60, 20);
        final int anvilCost = 4;
        final EquipmentSlot[] equipmentSlots = new EquipmentSlot[] {EquipmentSlot.MAINHAND};

        return new Properties(tagKey, Optional.of(tagKey), weight, maxLevel, minCost, maxCost, anvilCost, FeatureSet.empty(), equipmentSlots);
    }


    public LifeDrainEnchantment() {
        super(getEnchantmentProperties());
    }

    @Override
    public void onTargetDamaged(LivingEntity user, Entity target, int level) {
        if (target instanceof LivingEntity && ((LivingEntity) target).isDead()) {
            user.heal(amount * level);
        }
    }

    @Override
    public boolean canAccept(Enchantment other) {
        return other != Enchantments.SWEEPING_EDGE && other != Enchantments.LOOTING && super.canAccept(other);
    }
}