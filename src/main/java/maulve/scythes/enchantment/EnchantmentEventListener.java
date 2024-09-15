package maulve.scythes.enchantment;

import net.fabricmc.fabric.api.entity.event.v1.ServerEntityCombatEvents;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.world.ServerWorld;

public class EnchantmentEventListener {
    public static void register() {
        ServerEntityCombatEvents.AFTER_KILLED_OTHER_ENTITY.register(((world, entity, killedEntity) -> {
            if (entity instanceof PlayerEntity player && killedEntity instanceof LivingEntity && world instanceof ServerWorld serverWorld) {
                ItemStack weapon = player.getMainHandStack();

                DynamicRegistryManager registryManager = serverWorld.getRegistryManager();

                RegistryEntry<Enchantment> lifeDrainEntry = ModEnchantments.getEntry(registryManager, ModEnchantments.LIFE_DRAIN);

                if (EnchantmentHelper.getLevel(lifeDrainEntry, weapon) > 0) {
                    player.heal(EnchantmentHelper.getLevel(lifeDrainEntry, weapon));
                }
            }
        }));
    }

}
