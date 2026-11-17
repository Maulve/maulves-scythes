package maulve.scythes.platform.neoforge;

//? neoforge {

/*//? >1.20.4 {
import maulve.scythes.effect.CrystallizedMobEffect;
import maulve.scythes.enchantment.ModEnchantments;
import maulve.scythes.item.ModItems;
import net.minecraft.core.Holder;
import net.minecraft.core.RegistryAccess;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;
import net.neoforged.neoforge.event.entity.player.ItemTooltipEvent;
//?} <= 1.20.4 {
/^import net.neoforged.fml.common.Mod;
^///?}

//? >1.20.4 {
@EventBusSubscriber
//?} <= 1.20.4 {
/^@Mod.EventBusSubscriber(modid = MaulvesScythes.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
^///?}
public class NeoforgeEventSubscriber {

	@SubscribeEvent
	public static void addCrystallizeTooltip(ItemTooltipEvent event) {
		 if (event.getItemStack().is(ModItems.AMETHYST_SCYTHE.get())) {
			 event.getToolTip().add(CrystallizedMobEffect.TOOLTIP);
		 }
	}

	@SubscribeEvent(priority = EventPriority.LOWEST)
	public static void lifeDrainEvent(LivingDeathEvent event) {
		LivingEntity killedEntity = event.getEntity();

		if (!(killedEntity.level() instanceof ServerLevel)) {
			return;
		}

		Entity killer = event.getSource().getEntity();
		if (killer instanceof LivingEntity livingKiller) {
			RegistryAccess registryAccess = livingKiller.registryAccess();
			Holder.Reference<Enchantment> lifeDrainEntry = ModEnchantments.getEntry(registryAccess, ModEnchantments.LIFE_DRAIN);

			if (EnchantmentHelper.getEnchantmentLevel(lifeDrainEntry, livingKiller) > 0) {
				livingKiller.heal(EnchantmentHelper.getEnchantmentLevel(lifeDrainEntry, livingKiller));
			}
		}
	}
}
*///?}
