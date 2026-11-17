package maulve.scythes.platform.fabric;

//? fabric {

import maulve.scythes.enchantment.ModEnchantments;
import maulve.scythes.util.ScythesEnchantmentHelper;
import net.fabricmc.fabric.api.entity.event.v1.ServerEntityCombatEvents;
import net.minecraft.core.Holder;
import net.minecraft.core.RegistryAccess;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;

public class FabricEventSubscriber {

	public static void registerEvents() {
		registerLifeDrainEvent();
	}

	private static void registerLifeDrainEvent() {
		//? if >= 1.21.11 {
		ServerEntityCombatEvents.AFTER_KILLED_OTHER_ENTITY.register((level, entity, killedEntity, damageSource) -> {
		//? } else {
		/*ServerEntityCombatEvents.AFTER_KILLED_OTHER_ENTITY.register((level, entity, killedEntity) -> {
			*///? }
			//? if >= 1.21.1 {
			if (entity instanceof Player player && killedEntity instanceof LivingEntity && level instanceof ServerLevel) {
				//? } else {
			/*if (entity instanceof Player player && killedEntity instanceof LivingEntity && level != null) {
				*///? }
				ItemStack weaponStack = player.getMainHandItem();
				int enchantmentLevel = ScythesEnchantmentHelper.getLevel(ModEnchantments.LIFE_DRAIN, weaponStack);
				if (enchantmentLevel > 0) {
					player.heal(enchantmentLevel);
				}
			}
		});
	}
}
//?}
