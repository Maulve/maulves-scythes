package maulve.scythes.util;

import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;

public class ModStatusEffects {
    public static StatusEffectInstance getBleedingEffect() {
        return new StatusEffectInstance(StatusEffects.WITHER, 60, 1);
    }
}
