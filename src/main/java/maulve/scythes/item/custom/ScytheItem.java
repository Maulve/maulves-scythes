package maulve.scythes.item.custom;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import net.minecraft.block.*;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

public class ScytheItem extends SwordItem implements Vanishable {
    private final float attackDamage;
    private final Multimap<EntityAttribute, EntityAttributeModifier> attributeModifiers;

    public ScytheItem(ToolMaterial toolMaterial, int attackDamage, float attackSpeed, Item.Settings settings) {
        super(toolMaterial, attackDamage, attackSpeed, settings);
        this.attackDamage = (float)attackDamage + toolMaterial.getAttackDamage();
        ImmutableMultimap.Builder<EntityAttribute, EntityAttributeModifier> builder = ImmutableMultimap.builder();
        builder.put(EntityAttributes.GENERIC_ATTACK_DAMAGE, new EntityAttributeModifier(ATTACK_DAMAGE_MODIFIER_ID, "Weapon modifier", this.attackDamage, EntityAttributeModifier.Operation.ADDITION));
        builder.put(EntityAttributes.GENERIC_ATTACK_SPEED, new EntityAttributeModifier(ATTACK_SPEED_MODIFIER_ID, "Weapon modifier", attackSpeed, EntityAttributeModifier.Operation.ADDITION));
        this.attributeModifiers = builder.build();
    }

    @Override
    public boolean canMine(BlockState state, World world, BlockPos pos, PlayerEntity miner) {
        return allowedToMine(state.getBlock());
    }

    @Override
    public float getMiningSpeedMultiplier(ItemStack stack, BlockState state) {
        if (allowedToMine(state.getBlock())) {
            return super.getMiningSpeedMultiplier(stack, state);
        }
        return 0.0f;
    }
    
    public float getAttackDamage() {
        return this.attackDamage;
    }

    public Multimap<EntityAttribute, EntityAttributeModifier> getAttributeModifiers(EquipmentSlot slot) {
        return slot == EquipmentSlot.MAINHAND ? this.attributeModifiers : super.getAttributeModifiers(slot);
    }

    @Override
    public boolean postMine(ItemStack stack, World world, BlockState state, BlockPos pos, LivingEntity miner) {
        if (allowedToSweepMine(state.getBlock())) {
            BlockPos[] gridPositions;

            PlayerEntity player = null;

            if (miner instanceof PlayerEntity) {
                player = (PlayerEntity) miner;
            }

            assert player != null;

            if (player.getMovementDirection() == Direction.SOUTH) {
                gridPositions = new BlockPos[]{
                        new BlockPos(pos.getX() + 1, pos.getY(), pos.getZ()),
                        new BlockPos(pos.getX() - 1, pos.getY(), pos.getZ()),
                        new BlockPos(pos.getX(), pos.getY(), pos.getZ()),
                        new BlockPos(pos.getX() + 1, pos.getY(), pos.getZ() + 1),
                        new BlockPos(pos.getX() - 1, pos.getY(), pos.getZ() + 1),
                        new BlockPos(pos.getX(), pos.getY(), pos.getZ() + 1),
                };
            } else if (player.getMovementDirection() == Direction.NORTH) {
                gridPositions = new BlockPos[]{
                        new BlockPos(pos.getX() + 1, pos.getY(), pos.getZ()),
                        new BlockPos(pos.getX() - 1, pos.getY(), pos.getZ()),
                        new BlockPos(pos.getX(), pos.getY(), pos.getZ()),
                        new BlockPos(pos.getX() + 1, pos.getY(), pos.getZ() - 1),
                        new BlockPos(pos.getX() - 1, pos.getY(), pos.getZ() - 1),
                        new BlockPos(pos.getX(), pos.getY(), pos.getZ() - 1),
                };
            } else if (player.getMovementDirection() == Direction.EAST) {
                gridPositions = new BlockPos[]{
                        new BlockPos(pos.getX(), pos.getY(), pos.getZ() + 1),
                        new BlockPos(pos.getX(), pos.getY(), pos.getZ() - 1),
                        new BlockPos(pos.getX(), pos.getY(), pos.getZ()),
                        new BlockPos(pos.getX() + 1, pos.getY(), pos.getZ() + 1),
                        new BlockPos(pos.getX() + 1, pos.getY(), pos.getZ() - 1),
                        new BlockPos(pos.getX() + 1, pos.getY(), pos.getZ()),
                };
            } else if (player.getMovementDirection() == Direction.WEST) {
                gridPositions = new BlockPos[]{
                        new BlockPos(pos.getX(), pos.getY(), pos.getZ() + 1),
                        new BlockPos(pos.getX(), pos.getY(), pos.getZ() - 1),
                        new BlockPos(pos.getX(), pos.getY(), pos.getZ()),
                        new BlockPos(pos.getX() - 1, pos.getY(), pos.getZ() + 1),
                        new BlockPos(pos.getX() - 1, pos.getY(), pos.getZ() - 1),
                        new BlockPos(pos.getX() - 1, pos.getY(), pos.getZ()),
                };
            } else {
                gridPositions = new BlockPos[]{};
            }

            for (BlockPos _pos : gridPositions) {
                Block block = world.getBlockState(_pos).getBlock();
                if (allowedToSweepMine(block)) {
                    world.breakBlock(_pos, !player.isCreative(), miner);
                }
            }
        }

        if (state.getHardness(world, pos) != 0.0F) {
            stack.damage(2, miner, (e) -> e.sendEquipmentBreakStatus(EquipmentSlot.MAINHAND));
        }
        return true;
    }

    private boolean allowedToMine(Block block) {
        return block instanceof PlantBlock || block instanceof CobwebBlock;
    }
    private boolean allowedToSweepMine(Block block) {
        return block instanceof PlantBlock;
    }
}
