package maulve.scythes.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;

public class LifeDrainEnchantment extends ScytheEnchantment {
    private static final float amount = 0.5f;

    @Override
    public void onTargetDamaged(LivingEntity user, Entity target, int level) {
        if (target instanceof LivingEntity && ((LivingEntity) target).isDead()) {
            user.heal(amount * level);
        }
    }

    @Override
    public int getMaxLevel() { return 3;}

    @Override
    public boolean canAccept(Enchantment other) {
        return other != Enchantments.SWEEPING && other != Enchantments.LOOTING && super.canAccept(other);
    }

}