package maulve.scythes.platform.fabric;

//? fabric {

import maulve.scythes.enchantment.ModEnchantments;
import net.fabricmc.fabric.api.entity.event.v1.ServerEntityCombatEvents;
import net.minecraft.core.Holder;
import net.minecraft.core.RegistryAccess;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;

public class FabricEventSubscriber {

	public static void registerEvents() {
		registerLifeDrainEvent();
	}

	private static void registerLifeDrainEvent() {
		ServerEntityCombatEvents.AFTER_KILLED_OTHER_ENTITY.register((level, entity, killedEntity, damageSource) -> {
			if (entity instanceof Player player && killedEntity instanceof LivingEntity && level instanceof ServerLevel serverLevel) {

				RegistryAccess registryAccess = serverLevel.getLevel().registryAccess();

				Holder.Reference<Enchantment> lifeDrainEntry = ModEnchantments.getEntry(registryAccess, ModEnchantments.LIFE_DRAIN);

				if (EnchantmentHelper.getEnchantmentLevel(lifeDrainEntry, player) > 0) {
					player.heal(EnchantmentHelper.getEnchantmentLevel(lifeDrainEntry, player));
				}
			}
		});
	}
}
//?}
