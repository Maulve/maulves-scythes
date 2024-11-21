package maulve.scythes.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import maulve.scythes.item.AmethystScytheItem;
import maulve.scythes.item.ScytheItem;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = PlayerEntity.class, priority = 800)
public abstract class ServerPlayerEntityMixin {
    @Unique
    private boolean holdingScythe() {
        PlayerEntity player = (PlayerEntity)(Object) this;
        Item heldItem = player.getMainHandStack().getItem();

        return heldItem instanceof ScytheItem;
    }

    @Unique
    private boolean holdingAmethystScythe() {
        PlayerEntity player = (PlayerEntity)(Object) this;
        Item heldItem = player.getMainHandStack().getItem();

        return heldItem instanceof AmethystScytheItem;
    }

    // modifies doSweepAttack (bl4) if player is holding a Scythe
    @ModifyVariable(method = "attack", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;getHealth()F"), ordinal = 3)
    private boolean overrideBL4(boolean bl4) {
        if (holdingScythe()) {
            return true;
        }
        return bl4;
    }

    // adds Bleeding to sweeping attack targets
    @Inject(method = "attack", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;damage(Lnet/minecraft/entity/damage/DamageSource;F)Z"))
    private void addBleeding(Entity target, CallbackInfo ci, @Local(ordinal = 0) @NotNull LivingEntity livingEntity) {
        if (holdingAmethystScythe()) {
            StatusEffectInstance effectWither = new StatusEffectInstance(StatusEffects.WITHER, 80, 1);
            StatusEffectInstance effectSlowness = new StatusEffectInstance(StatusEffects.SLOWNESS, 20, 1);
            livingEntity.addStatusEffect(effectWither);
            livingEntity.addStatusEffect(effectSlowness);
        }
    }
}