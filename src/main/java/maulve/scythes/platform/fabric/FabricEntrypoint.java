package maulve.scythes.platform.fabric;

//? fabric {

import maulve.scythes.MaulvesScythes;
import maulve.scythes.enchantment.ModEnchantments;
import net.fabricmc.api.ModInitializer;

public class FabricEntrypoint implements ModInitializer {

	@Override
	public void onInitialize() {
		MaulvesScythes.onInitialize();
		FabricEventSubscriber.registerEvents();
		FabricModItemRegistry.initialize();
		FabricModCreativeTab.initialize();
		FabricModEffects.initialize();
		//? <= 1.20.4
		//ModEnchantments.initialize();
	}
}
//?}
