package maulve.scythes.item;

//? <= 1.21.1 {

/*import com.google.common.base.Suppliers;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;
import org.jspecify.annotations.NonNull;

import java.util.function.Supplier;

public enum ModTiers implements Tier {
	AMETHYST(
			//? >= 1.21.1
			AmethystMaterialDefinition.INCORRECT,
			//? <= 1.20.4
			//AmethystMaterialDefinition.MINING_LEVEL,
			AmethystMaterialDefinition.DURABILITY,
			AmethystMaterialDefinition.MINING_SPEED,
			AmethystMaterialDefinition.ATTACK_DAMAGE,
			AmethystMaterialDefinition.ENCHANTABILITY,
			() -> Ingredient.of(ModItems.SCYTHED_AMETHYST.get())
	);

	//? >= 1.21.1
	private final TagKey<Block> incorrectBlocksForDrops;
	//? <= 1.20.4
	//private final int miningLevel;
	private final int uses;
	private final float speed;
	private final float damage;
	private final int enchantmentValue;
	private final Supplier<Ingredient> repairIngredient;

	//? >= 1.21.1
	ModTiers(TagKey<Block> incorrectBlocks, int uses, float speed, float damage, int enchantmentValue, Supplier<Ingredient> repairIngredient) {
	//? <= 1.20.4
	//ModTiers(int miningLevel, int uses, float speed, float damage, int enchantmentValue, Supplier<Ingredient> repairIngredient) {
		//? >= 1.21.1
		this.incorrectBlocksForDrops = incorrectBlocks;
		//? <= 1.20.4
		//this.miningLevel = miningLevel;
		this.uses = uses;
		this.speed = speed;
		this.damage = damage;
		this.enchantmentValue = enchantmentValue;
		this.repairIngredient = Suppliers.memoize(repairIngredient::get);
	}

	@Override public int getUses() { return this.uses; }
	@Override public float getSpeed() { return this.speed; }
	@Override public float getAttackDamageBonus() { return this.damage; }
	//? >= 1.21.1
	@Override public @NonNull TagKey<Block> getIncorrectBlocksForDrops() { return this.incorrectBlocksForDrops; }
	//? <= 1.20.4
	//@Override public int getLevel() { return this.miningLevel; }
	@Override public int getEnchantmentValue() { return this.enchantmentValue; }
	@Override public @NonNull Ingredient getRepairIngredient() { return this.repairIngredient.get(); }
}

*///? }
