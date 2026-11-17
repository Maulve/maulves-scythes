package maulve.scythes.platform.neoforge;

//? neoforge {

/*import maulve.scythes.MaulvesScythes;
import maulve.scythes.effect.CrystallizedMobEffect;
import maulve.scythes.enchantment.ModEnchantments;
import maulve.scythes.item.ModItems;
import maulve.scythes.util.ScythesEnchantmentHelper;
import net.minecraft.core.Holder;
import net.minecraft.core.RegistryAccess;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.SubscribeEvent;
//? >= 1.21.1
import net.neoforged.fml.common.EventBusSubscriber;
//? <= 1.20.4
//import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;
import net.neoforged.neoforge.event.entity.player.ItemTooltipEvent;

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
			ItemStack weaponStack = livingKiller.getMainHandItem();
			//? >= 1.21.1
			RegistryAccess registryAccess = livingKiller.level().registryAccess();

			//? >= 1.21.1
			int enchantmentLevel = ScythesEnchantmentHelper.getLevel(registryAccess, ModEnchantments.LIFE_DRAIN, weaponStack);
			//? <= 1.20.4
			//int enchantmentLevel = ScythesEnchantmentHelper.getLevel(ModEnchantments.LIFE_DRAIN, weaponStack);

			if (enchantmentLevel > 0) {
				livingKiller.heal(enchantmentLevel);
			}
		}
	}
}
*///?}
