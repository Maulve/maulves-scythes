package maulve.scythes.enchantment;

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
}