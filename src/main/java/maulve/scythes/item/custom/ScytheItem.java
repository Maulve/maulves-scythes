package maulve.scythes.item.custom;

import net.minecraft.block.*;
import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.component.type.AttributeModifiersComponent;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

public class ScytheItem extends SwordItem {
    public ScytheItem(ToolMaterial toolMaterial, Item.Settings settings) {
        super(toolMaterial, settings);
    }

    public static AttributeModifiersComponent createAttributeModifiers(ToolMaterial material, int attackDamage, float attackSpeed) {
        return AttributeModifiersComponent.builder()
                .add(
                        EntityAttributes.GENERIC_ATTACK_DAMAGE,
                        new EntityAttributeModifier(
                                BASE_ATTACK_DAMAGE_MODIFIER_ID, (float) attackDamage + material.getAttackDamage(), EntityAttributeModifier.Operation.ADD_VALUE),
                        AttributeModifierSlot.MAINHAND
                ).add(
                        EntityAttributes.GENERIC_ATTACK_SPEED,
                        new EntityAttributeModifier(
                                BASE_ATTACK_SPEED_MODIFIER_ID, attackSpeed, EntityAttributeModifier.Operation.ADD_VALUE),
                        AttributeModifierSlot.MAINHAND
                ).build();
    }

    @Override
    public boolean canMine(BlockState state, World world, BlockPos pos, PlayerEntity miner) {
        return allowedToMine(state.getBlock());
    }

    @Override
    public float getMiningSpeed(ItemStack stack, BlockState state) {
        if (allowedToMine(state.getBlock())) {
            return super.getMiningSpeed(stack, state);
        }
        return 0.0f;
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
            stack.damage(2, miner, EquipmentSlot.MAINHAND);
        }
        return true;
    }

    private boolean allowedToMine(Block block) {
        return block instanceof PlantBlock || block instanceof CobwebBlock || block instanceof LeavesBlock;
    }
    private boolean allowedToSweepMine(Block block) {
        return block instanceof PlantBlock;
    }
}
