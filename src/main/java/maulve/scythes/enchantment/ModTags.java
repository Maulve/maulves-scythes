package maulve.scythes.enchantment;

import maulve.scythes.MaulvesScythes;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class ModTags {
    public static final TagKey<Item> SCYTHE_ENCHANTABLE = TagKey.of(Registries.ITEM.getKey(), new Identifier(MaulvesScythes.MOD_ID, "scythes"));
}
