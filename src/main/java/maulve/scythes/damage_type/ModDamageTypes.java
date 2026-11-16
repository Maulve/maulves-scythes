package maulve.scythes.damage_type;

import maulve.scythes.MaulvesScythes;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.damagesource.DamageType;

public class ModDamageTypes {
	public static final ResourceKey<DamageType> CRYSTALLIZE_DAMAGE = ResourceKey.create(Registries.DAMAGE_TYPE, MaulvesScythes.id("crystallize"));
	public static final ResourceKey<DamageType> CRYSTAL_SWING = ResourceKey.create(Registries.DAMAGE_TYPE, MaulvesScythes.id("crystal_swing"));
}
