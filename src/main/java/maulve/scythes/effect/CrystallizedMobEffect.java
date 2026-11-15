package maulve.scythes.effect;

import maulve.scythes.platform.fabric.FabricModItems;
import net.minecraft.core.registries.Registries;
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
	public CrystallizedMobEffect() {
		super(MobEffectCategory.HARMFUL, 0x000000);

		this.addAttributeModifier(
				Attributes.MOVEMENT_SPEED,
				Identifier.withDefaultNamespace("effect.slowness"),
				-0.25D,
				AttributeModifier.Operation.ADD_MULTIPLIED_BASE
		);
	}

	@Override
	public boolean applyEffectTick(@NonNull ServerLevel level, LivingEntity entity, int amplifier) {
		DamageSource crystallizeDamageSource = new DamageSource(
				level.registryAccess()
						.lookupOrThrow(Registries.DAMAGE_TYPE)
						.get(FabricModItems.CRYSTALLIZE_DAMAGE.identifier()).get()
		);
		entity.hurtServer(level, crystallizeDamageSource, 1.0F);
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
