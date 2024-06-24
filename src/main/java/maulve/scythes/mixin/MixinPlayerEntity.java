package maulve.scythes.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Slice;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

// TODO: finish sweep mixin

/*
goal: have sweep attack always happen when scythe is in hand
in method "attack" in PlayerEntity, "bl4" boolean needs to be true
 */

@Mixin(PlayerEntity.class)
public abstract class MixinPlayerEntity {

    /*
    @Inject(method = "attack", slice = @Slice(
            from = @At(value = "INVOKE", target = "bl4")
    ))
    public void attack(Entity target, CallbackInfo ci) {

    }

    */
}