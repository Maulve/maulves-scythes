package maulve.scythes.effect;

import maulve.scythes.MaulvesScythes;
import maulve.scythes.damage_type.ModDamageTypes;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import org.jspecify.annotations.NonNull;

// TODO: more effects, like heart color and screen effect
public class CrystallizedMobEffect extends MobEffect {
	public static final Component TOOLTIP = Component.translatable("tooltip.maulves_scythes.crystallize");

	public CrystallizedMobEffect() {
		super(MobEffectCategory.HARMFUL, 0x000001);

		this.addAttributeModifier(
				Attributes.MOVEMENT_SPEED,
				//? >= 1.21.1
				MaulvesScythes.id("minecraft", "effect.slowness"),
				//? <= 1.20.4
				//"7107DE5E-7CE8-4030-940E-514C1F160890",
				-0.25D,
				AttributeModifier.Operation.ADD_MULTIPLIED_BASE
		);
	}

	@Override
	//? if >= 1.21.4 {
	public boolean applyEffectTick(@NonNull ServerLevel level, LivingEntity entity, int amplifier) {
		//? } else if 1.21.1 {
	/*public boolean applyEffectTick(LivingEntity entity, int amplifier) {
		*///? } else {
	/*public void applyEffectTick(LivingEntity entity, int amplifier) {
		*///? }
		//? if >= 1.21.4 {
		DamageSource crystallizeDamageSource = new DamageSource(
				entity.registryAccess()
						.lookupOrThrow(Registries.DAMAGE_TYPE)
						.get(ModDamageTypes.CRYSTALLIZE_DAMAGE.identifier()).get()
		);

		entity.hurtServer(level, crystallizeDamageSource, 1.0F);
		//? } else {
		/*DamageSource crystallizeDamageSource = entity.level().damageSources().source(ModDamageTypes.CRYSTALLIZE_DAMAGE);
		entity.hurt(crystallizeDamageSource, 1.0F);
		*///? }
		//? >= 1.21.1
		return true;
	}

	// i = duration
	// j = amplifier
	@Override
	public boolean shouldApplyEffectTickThisTick(int i, int j) {
    	int interval = 40 >> j; // halve interval every time amplifier is increased by 1, starts at 2 seconds
		return interval == 0 || i % interval == 0;
	}

}
