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
			AmethystMaterialDefinition.INCORRECT,
			AmethystMaterialDefinition.DURABILITY,
			AmethystMaterialDefinition.MINING_SPEED,
			AmethystMaterialDefinition.ATTACK_DAMAGE,
			AmethystMaterialDefinition.ENCHANTABILITY,
			() -> Ingredient.of(ModItems.SCYTHED_AMETHYST.get())
	);

	private final TagKey<Block> incorrectBlocksForDrops;
	private final int uses;
	private final float speed;
	private final float damage;
	private final int enchantmentValue;
	private final Supplier<Ingredient> repairIngredient;

	ModTiers(TagKey<Block> incorrectBlocks, int uses, float speed, float damage, int enchantmentValue, Supplier<Ingredient> repairIngredient) {
		this.incorrectBlocksForDrops = incorrectBlocks;
		this.uses = uses;
		this.speed = speed;
		this.damage = damage;
		this.enchantmentValue = enchantmentValue;
		this.repairIngredient = Suppliers.memoize(repairIngredient::get);
	}

	@Override public int getUses() { return this.uses; }
	@Override public float getSpeed() { return this.speed; }
	@Override public float getAttackDamageBonus() { return this.damage; }
	@Override public @NonNull TagKey<Block> getIncorrectBlocksForDrops() { return this.incorrectBlocksForDrops; }
	@Override public int getEnchantmentValue() { return this.enchantmentValue; }
	@Override public @NonNull Ingredient getRepairIngredient() { return this.repairIngredient.get(); }
}

*///? }
