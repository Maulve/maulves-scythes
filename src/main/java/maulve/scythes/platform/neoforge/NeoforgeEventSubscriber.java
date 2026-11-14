package maulve.scythes.platform.neoforge;

//? neoforge {

/*import maulve.scythes.MaulvesScythes;
import maulve.scythes.event.ExampleEventHandler; // sample_content
import net.minecraft.server.level.ServerPlayer; // sample_content
import net.neoforged.bus.api.SubscribeEvent;
//? >1.20.4 {
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
//?} <= 1.20.4 {
/^import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.entity.living.LivingHurtEvent;
^///?}

//? >1.20.4 {
@EventBusSubscriber
//?} <= 1.20.4 {
/^@Mod.EventBusSubscriber(modid = MaulvesScythes.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
^///?}
public class NeoforgeEventSubscriber {

	@SubscribeEvent

			//? if <= 1.20.4 {
	/^public static void onPlayerDamage(LivingHurtEvent event) {
	 ^///? } else {
	public static void onPlayerDamage(LivingDamageEvent.Post event) {
		//? }
		if (event.getEntity() instanceof ServerPlayer player && event.getNewDamage() > 0) {
			ExampleEventHandler.onPlayerHurt(player);
		}
	}
}
*///?}
