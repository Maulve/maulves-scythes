package maulve.scythes.item;

//? < 1.21.11 {
/*import net.minecraft.world.item.Item;
import net.minecraft.world.item.ToolMaterial;
*///? }

import maulve.scythes.platform.fabric.FabricModEffects;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jspecify.annotations.NonNull;

public class AmethystScytheItem extends ScytheItem {
	//? if < 1.21.11 {
	/*public AmethystScytheItem(ToolMaterial toolMaterial, Item.Settings settings) {
		super(toolMaterial, settings);
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
	public void hurtEnemy(@NonNull ItemStack stack, @NonNull LivingEntity target, @NonNull LivingEntity attacker) {
		if (target.isAlive()) {
			//target.addEffect(new MobEffectInstance(FabricModEffects.CRYSTALLIZED_EFFECT, 200, 0));
		}
		Level level = attacker.level();
		if (!level.isClientSide()) {
			float pitch = getRandom(2, 4) + 0.7F;
			level.playSound(null, target.getBlockPosBelowThatAffectsMyMovement(), SoundEvents.AMETHYST_BLOCK_HIT, SoundSource.PLAYERS, 1F, pitch);

			//IMPROVEMENT: maybe cool particles?
		}
		stack.hurtAndBreak(1, attacker, EquipmentSlot.MAINHAND);
	}

	@Override
	public @NonNull Component getName(@NonNull ItemStack stack) {
		return Component.translatable(this.getDescriptionId()).withStyle(ChatFormatting.LIGHT_PURPLE);
	}
}
