package maulve.scythes.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.sugar.Local;
import maulve.scythes.MaulvesScythes;
import maulve.scythes.damage_type.ModDamageTypes;
import maulve.scythes.enchantment.ModEnchantments;
import maulve.scythes.item.AmethystScytheItem;
import maulve.scythes.item.ScytheItem;
//? fabric
import maulve.scythes.platform.fabric.FabricModEffects;
//? neoforge
//import maulve.scythes.platform.neoforge.NeoForgeMobEffects;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.phys.AABB;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Player.class)
public abstract class PlayerEntityMixin {

	@Unique
	protected ItemStack maulves_scythes$getHeldItemStack() {
		Player player = (Player) (Object) this;
		assert player != null;
		return player.getMainHandItem();
	}

	@Unique
	private boolean maulves_scythes$holdingScythe() {
		return maulves_scythes$getHeldItemStack().getItem() instanceof ScytheItem;
	}

	@Unique
	private boolean maulves_scythes$holdingAmethystScythe() {
		return maulves_scythes$getHeldItemStack().getItem() instanceof AmethystScytheItem;
	}

	@Unique boolean maulves_scythes$hasEnchantment(ResourceKey<Enchantment> key) {
		Player player = (Player) (Object) this;

		Holder<Enchantment> sweepingArcHolder = player.registryAccess()
				.lookupOrThrow(Registries.ENCHANTMENT)
				.getOrThrow(key);

		return EnchantmentHelper.getEnchantmentLevel(sweepingArcHolder, player) > 0;
	}

	@ModifyVariable(method = "attack", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/LivingEntity;getHealth()F"), ordinal = 3)
	private boolean overrideDoSweepAttack(boolean doSweepAttack) {
		if (maulves_scythes$holdingScythe()) {
			return true;
		}
		return doSweepAttack;
	}

	@ModifyArg(method = "attack", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/Entity;hurtOrSimulate(Lnet/minecraft/world/damagesource/DamageSource;F)Z"), index = 0)
	private DamageSource modifyDamageSource(DamageSource originalSource) {
		Player player = (Player) (Object) this;
		if (maulves_scythes$holdingAmethystScythe()) {
			return new DamageSource(
					player.level().registryAccess().lookupOrThrow(Registries.DAMAGE_TYPE)
							.getOrThrow(ModDamageTypes.CRYSTAL_SWING),
					player
			);
		}

		return originalSource;
	}

	//? if >= 1.21.11 {
	private static final String SWEEP_TARGET = "doSweepAttack*";
	//? } else {
	/*private static final String SWEEP_TARGET = "attack";
	*///? }

	@Inject(method = SWEEP_TARGET, at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/LivingEntity;knockback(DDD)V", ordinal = 0))
	private void onHurt(CallbackInfo ci, @Local LivingEntity livingEntity) {
		if (maulves_scythes$holdingAmethystScythe()) {
			//? fabric
			livingEntity.addEffect(new MobEffectInstance(FabricModEffects.CRYSTALLIZED_EFFECT, 200, 0));
			//? neoforge
			//livingEntity.addEffect(new MobEffectInstance(NeoForgeMobEffects.CRYSTALLIZED_EFFECT, 200, 0));
		}
	}

	/*
	fabric and neoforge differ slightly in code for Player#doSweepAttack.
	while fabric calls the AABB.inflate inline, neoforge uses it as a method arg (of doSweepAttack)
	 */
	//? fabric
	@ModifyExpressionValue(method = SWEEP_TARGET, at = @At(value = "INVOKE", target = "Lnet/minecraft/world/phys/AABB;inflate(DDD)Lnet/minecraft/world/phys/AABB;"))
	//? neoforge
	//@ModifyVariable(method = "doSweepAttack(Lnet/minecraft/world/entity/Entity;FLnet/minecraft/world/damagesource/DamageSource;FLnet/minecraft/world/phys/AABB;)V", at = @At("HEAD"), argsOnly = true)
	private AABB expandSweepBoundingBoxForSweepingArc(AABB originalBox) {
		if (maulves_scythes$hasEnchantment(ModEnchantments.SWEEPING_ARC)) {
			return originalBox.inflate(10.0, 0.5, 10.0);
		} else {
			return originalBox;
		}
	}
}
