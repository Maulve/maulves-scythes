package maulve.scythes.item.custom;

import maulve.scythes.util.ModStatusEffects;
import net.minecraft.client.item.TooltipType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolMaterial;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;

import java.util.List;

public class AmethystScytheItem extends ScytheItem {
    public AmethystScytheItem(ToolMaterial toolMaterial, Item.Settings settings) {
        super(toolMaterial, settings);
    }

    private float getRandom() {
        int max = 4;
        int min = 2;
        return (float) ((Math.random() * (max - min)) + min) / 10;
    }

    // workaround so that §d formatting doesn't appear in anvil
    @Override
    public Text getName(ItemStack stack) {
        return Text.translatable(this.getTranslationKey(stack)).formatted(Formatting.LIGHT_PURPLE);
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (target.isAlive()) {
            target.addStatusEffect(ModStatusEffects.getBleedingEffect());
        }
        World world = attacker.getWorld();
        if (!world.isClient) {
            float pitch = getRandom() + 0.7f;
            world.playSound(null, target.getBlockPos(), SoundEvents.BLOCK_AMETHYST_BLOCK_HIT, SoundCategory.PLAYERS, 1f, pitch);
        }
        stack.damage(1, attacker, EquipmentSlot.MAINHAND);
        return true;
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        tooltip.add(Text.translatable("item.maulves-scythes.amethyst_scythe.tooltip-bleeding"));
    }
}
