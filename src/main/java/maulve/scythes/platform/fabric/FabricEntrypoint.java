package maulve.scythes.platform.fabric;

//? fabric {

import maulve.scythes.MaulvesScythes;
import net.fabricmc.api.ModInitializer;

public class FabricEntrypoint implements ModInitializer {

	@Override
	public void onInitialize() {
		MaulvesScythes.onInitialize();
		FabricEventSubscriber.registerEvents();
	}
}
//?}
