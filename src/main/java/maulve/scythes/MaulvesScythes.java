package maulve.scythes;

import maulve.scythes.platform.Platform;

import net.minecraft.resources.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//? fabric {
import maulve.scythes.platform.fabric.FabricPlatform;
//?} neoforge {
/*import maulve.scythes.platform.neoforge.NeoforgePlatform;
*///? }

@SuppressWarnings("LoggingSimilarMessage")
public class MaulvesScythes {

	public static final String MOD_ID = /*$ mod_id*/"maulves_scythes";
	public static final String MOD_VERSION = /*$ mod_version*/"2.0.0";
	public static final String MOD_FRIENDLY_NAME = /*$ mod_name*/"Maulve's Scythes";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	private static final Platform PLATFORM = createPlatformInstance();

	public static void onInitialize() {
		LOGGER.info("Initializing {} on {}", MOD_ID, MaulvesScythes.xplat().loader());
		LOGGER.debug("{}: { version: {}; friendly_name: {} }", MOD_ID, MOD_VERSION, MOD_FRIENDLY_NAME);
	}

	public static void onInitializeClient() {
		LOGGER.info("Initializing {} Client on {}", MOD_ID, MaulvesScythes.xplat().loader());
		LOGGER.debug("{}: { version: {}; friendly_name: {} }", MOD_ID, MOD_VERSION, MOD_FRIENDLY_NAME);
	}

	public static Platform xplat() {
		return PLATFORM;
	}

	private static Platform createPlatformInstance() {
		//? fabric {
		return new FabricPlatform();
		//?} neoforge {
		/*return new NeoforgePlatform();
		 *///?}
	}

	public static Identifier id(String path) {
		//? >= 1.21 {
		return Identifier.fromNamespaceAndPath(MOD_ID, path);
		 //?} < 1.21 {
		/*return new Identifier(MOD_ID, path);
		*///?}
	}

	public static Identifier id(String namespace, String path) {
		//? >= 1.21 {
		return Identifier.fromNamespaceAndPath(namespace, path);
		 //?} < 1.21  {
		/*return new Identifier(namespace, path);
		*///?}
	}
}
