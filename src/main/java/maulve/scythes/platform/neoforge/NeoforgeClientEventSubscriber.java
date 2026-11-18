package maulve.scythes.platform.neoforge;

//? neoforge {

/*import maulve.scythes.MaulvesScythes;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
//? > 1.20.4 {
/^import net.neoforged.fml.common.EventBusSubscriber;
^///?} <= 1.20.4 {
import net.neoforged.fml.common.Mod;
//?}
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;

//? > 1.20.4 {
/^@EventBusSubscriber(modid = MaulvesScythes.MOD_ID, value = Dist.CLIENT)
^///?} <= 1.20.4 {
@Mod.EventBusSubscriber(modid = MaulvesScythes.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
//?}
public class NeoforgeClientEventSubscriber {
	@SubscribeEvent
	public static void onClientSetup(final FMLClientSetupEvent event) {
		MaulvesScythes.onInitializeClient();
	}
}
*///?}
