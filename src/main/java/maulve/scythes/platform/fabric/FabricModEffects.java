package maulve.scythes.platform.fabric;

import maulve.scythes.MaulvesScythes;
import maulve.scythes.effect.CrystallizedMobEffect;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.effect.MobEffect;

public class FabricModEffects {
	public static final Holder.Reference<MobEffect> CRYSTALLIZED_EFFECT = Registry.registerForHolder(
			BuiltInRegistries.MOB_EFFECT,
			MaulvesScythes.id("crystallized"),
			new CrystallizedMobEffect()
	);

	public static void initialize() {}
}
