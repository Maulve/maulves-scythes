package maulve.scythes.mixin;

import maulve.scythes.damage_type.ModDamageTypes;
//? fabric
import maulve.scythes.platform.fabric.FabricModEffects;
//? neoforge
//import maulve.scythes.platform.neoforge.NeoForgeMobEffects;
//? >= 1.21.4
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public class LivingEntityMixin {
	//? if >= 1.21.4 {
	@Inject(method = "hurtServer", at = @At("HEAD"))
	private void onHurtServer(ServerLevel level, DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
		//? } else {
	/*@Inject(method = "hurt", at = @At("HEAD"))
	private void onHurtServer(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
		*///? }
		if (source.is(ModDamageTypes.CRYSTAL_SWING)) {
			LivingEntity target = (LivingEntity) (Object) this;
			//? fabric
			target.addEffect(new MobEffectInstance(FabricModEffects.CRYSTALLIZED_EFFECT, 200, 0));
			//? neoforge
			//target.addEffect(new MobEffectInstance(NeoForgeMobEffects.CRYSTALLIZED_EFFECT.value(), 200, 0));
		}
	}

}
