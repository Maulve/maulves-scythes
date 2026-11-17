package maulve.scythes.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import maulve.scythes.damage_type.ModDamageTypes;
import maulve.scythes.enchantment.ModEnchantments;
//? fabric
//? neoforge
import maulve.scythes.item.ModItems;
import maulve.scythes.util.MixinHelper;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.AABB;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Debug(export = true)
@Mixin(Player.class)
public abstract class PlayerMixin {
	@Unique
	private Player getSelf() {
		return (Player) (Object) this;
	}

	@ModifyVariable(method = "attack", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/LivingEntity;getHealth()F"), ordinal = 3)
	private boolean overrideDoSweepAttack(boolean doSweepAttack) {
		if (MixinHelper.maulves_scythes$holdingScythe(getSelf())) {
			return true;
		}
		return doSweepAttack;
	}

	//? if >= 1.21.4 {
	private static final String HURT_TARGET = "Lnet/minecraft/world/entity/Entity;hurtOrSimulate(Lnet/minecraft/world/damagesource/DamageSource;F)Z";
	//? } else {
	/*private static final String HURT_TARGET = "Lnet/minecraft/world/entity/Entity;hurt(Lnet/minecraft/world/damagesource/DamageSource;F)Z";
	*///? }


	// NOTE: NOT YET PORTED TO EARLIER VERSIONS, ONLY FOR 1.21.11

	@ModifyArg(method = "attack", at = @At(value = "INVOKE", target = HURT_TARGET), index = 0)
	private DamageSource modifyDamageSource(DamageSource originalSource) {
		Entity causingEntity = originalSource.getEntity();
		if (causingEntity instanceof LivingEntity livingEntity) {
			if (livingEntity.getMainHandItem().is(ModItems.AMETHYST_SCYTHE.get())) {
				return new DamageSource(
						livingEntity.registryAccess().lookupOrThrow(Registries.DAMAGE_TYPE)
								.getOrThrow(ModDamageTypes.CRYSTAL_SWING),
						livingEntity
				);
			}
		}
		return originalSource;
	}

	@ModifyArg(method = "doSweepAttack", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/LivingEntity;hurtServer(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/world/damagesource/DamageSource;F)Z"), index = 1)
	private DamageSource modifyDamageSourceInSweep(DamageSource originalSource) {
		Entity causingEntity = originalSource.getEntity();
		if (causingEntity instanceof LivingEntity livingEntity) {
			if (livingEntity.getMainHandItem().is(ModItems.AMETHYST_SCYTHE.get())) {
				return new DamageSource(
						livingEntity.registryAccess().lookupOrThrow(Registries.DAMAGE_TYPE)
								.getOrThrow(ModDamageTypes.CRYSTAL_SWING),
						livingEntity
				);
			}
		}
		return originalSource;
	}

	//? fabric {
		//? if >= 1.21.11 {
		private static final String SWEEP_TARGET = "doSweepAttack*";
		//? } else {
		/*private static final String SWEEP_TARGET = "attack";
		*///? }
	//? }
	//? neoforge {
		/*//? if >= 1.21.11 {
		/^private static final String SWEEP_TARGET = "doSweepAttack(Lnet/minecraft/world/entity/Entity;FLnet/minecraft/world/damagesource/DamageSource;FLnet/minecraft/world/phys/AABB;)V";
		 ^///? } else {
		private static final String SWEEP_TARGET = "attack";
	//? }
	*///? }

	/*
	fabric and neoforge differ slightly in code for Player#doSweepAttack.
	while fabric calls the AABB.inflate inline, neoforge uses it as a method arg (of doSweepAttack)
	 */
	//? if fabric|| (neoforge && < 1.21.11) {
	@ModifyExpressionValue(method = SWEEP_TARGET, at = @At(value = "INVOKE", target = "Lnet/minecraft/world/phys/AABB;inflate(DDD)Lnet/minecraft/world/phys/AABB;"))
	//? } else {
	/*@ModifyVariable(method = SWEEP_TARGET, at = @At("HEAD"), argsOnly = true)
	*///? }
	private AABB expandSweepBoundingBoxForSweepingArc(AABB originalBox) {
		if (MixinHelper.maulves_scythes$hasEnchantment(getSelf(), ModEnchantments.SWEEPING_ARC)) {
			return originalBox.inflate(10.0, 0.5, 10.0);
		} else {
			return originalBox;
		}
	}
}
