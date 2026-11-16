package maulve.scythes.item;

//? < 1.21.11 {
/*import net.minecraft.world.item.Item;
import net.minecraft.world.item.SwordItem;
//? if < 1.21.4 {
/^import net.minecraft.world.item.Tier;
^///? } else {
import net.minecraft.world.item.ToolMaterial;
//? }
*///? }

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jspecify.annotations.NonNull;

public class AmethystScytheItem extends ScytheItem {
	//? if < 1.21.4 {
	/*public AmethystScytheItem(Tier tier, float attackDamage, float attackSpeed, Properties properties) {
		super(tier, attackDamage, attackSpeed, properties);
	}
	*///? } else if < 1.21.11 {
	/*public AmethystScytheItem(ToolMaterial toolMaterial, float attackDamage, float attackSpeed, Properties properties) {
		super(toolMaterial, attackDamage, attackSpeed, properties);
	}
	*///? } else {
	public AmethystScytheItem(Properties properties) {
		super(properties);
	}
	//? }

	private float getRandom(float min, float max) {
		return (float) ((Math.random() * (max - min)) + min) / 10;
	}

	@Override
	//? if >= 1.21.11 {
	public void hurtEnemy(@NonNull ItemStack stack, @NonNull LivingEntity target, @NonNull LivingEntity attacker) {
	//? } else {
	/*public boolean hurtEnemy(@NonNull ItemStack stack, @NonNull LivingEntity target, @NonNull LivingEntity attacker) {
		*///? }
		Level level = attacker.level();
		if (!level.isClientSide()) {
			float pitch = getRandom(2, 4) + 0.7F;
			level.playSound(null, target.getBlockPosBelowThatAffectsMyMovement(), SoundEvents.AMETHYST_BLOCK_HIT, SoundSource.PLAYERS, 1F, pitch);

			//IMPROVEMENT: maybe cool particles?
		}
		stack.hurtAndBreak(1, attacker, EquipmentSlot.MAINHAND);
		//? <= 1.21.4
		//return true;
	}

	@Override
	public @NonNull Component getName(@NonNull ItemStack stack) {
		return Component.translatable(this.getDescriptionId()).withStyle(ChatFormatting.LIGHT_PURPLE);
	}
}
