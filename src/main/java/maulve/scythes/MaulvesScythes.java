package maulve.scythes;

import maulve.scythes.enchantment.ModEnchantments;
import maulve.scythes.item.ModItemGroups;
import maulve.scythes.item.ModItems;
import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MaulvesScythes implements ModInitializer {
	public static final String MOD_ID = "maulves-scythes";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModItemGroups.registerItemGroups();
		ModItems.registerModItems();
	}
}