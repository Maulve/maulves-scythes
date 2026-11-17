package maulve.scythes.item;

import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

public class AmethystMaterialDefinition {
	//? >= 1.21.1
	public static final TagKey<Block> INCORRECT = BlockTags.INCORRECT_FOR_WOODEN_TOOL;
	//? <= 1.20.4
	//public static final int MINING_LEVEL = 0;

	public static final int DURABILITY = 2521;
	public static final float MINING_SPEED = 9.0F;
	public static final float ATTACK_DAMAGE = 5.0F;
	public static final int ENCHANTABILITY = 26;
}
