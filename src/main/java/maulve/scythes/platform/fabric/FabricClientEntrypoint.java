package maulve.scythes.platform.fabric;

//? fabric {

import maulve.scythes.MaulvesScythes;
import net.fabricmc.api.ClientModInitializer;

public class FabricClientEntrypoint implements ClientModInitializer {

	@Override
	public void onInitializeClient() {
		MaulvesScythes.onInitializeClient();
	}

}
//?}
