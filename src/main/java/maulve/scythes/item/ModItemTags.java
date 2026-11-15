package maulve.scythes.item;

import maulve.scythes.MaulvesScythes;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class ModItemTags {
	public static final TagKey<Item> SCYTHES = createTag("scythes");
	public static final TagKey<Item> AMETHYST_REPAIR = createTag("amethyst_repair");

	private static TagKey<Item> createTag(String name) {
		return TagKey.create(Registries.ITEM, MaulvesScythes.id(name));
	}
}
