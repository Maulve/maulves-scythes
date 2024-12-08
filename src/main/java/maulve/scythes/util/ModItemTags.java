package maulve.scythes.util;

import maulve.scythes.MaulvesScythes;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class ModItemTags {
    public static final TagKey<Item> SCYTHES = createTag("scythes");

    private static TagKey<Item> createTag(String name) {
        return TagKey.of(RegistryKeys.ITEM, Identifier.of(MaulvesScythes.MOD_ID, name));
    }
}