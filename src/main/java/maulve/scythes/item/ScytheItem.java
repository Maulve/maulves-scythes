package maulve.scythes.item;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.VegetationBlock;
import net.minecraft.world.level.block.WebBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.jspecify.annotations.NonNull;

// >= 1.21.11 changes how weapons are defined, so they now extend Item and are defined in datagen

public class ScytheItem extends
		//? < 1.21.11
		//SwordItem
	    //? >= 1.21.11
	    Item
{
	//? if < 1.21.11 {
	/*public ScytheItem(ToolMaterial toolMaterial, Item.Settings settings) {
		super(toolMaterial, settings);
	}
	*///? } else {
	public ScytheItem(Properties properties) {
		super(properties);
	}
	//?}

	@Override
	public boolean canDestroyBlock(@NonNull ItemStack stack, BlockState state, @NonNull Level level, @NonNull BlockPos pos, @NonNull LivingEntity entity) {
		return allowedToMine(state.getBlock());
	}

	@Override
	public float getDestroySpeed(@NonNull ItemStack stack, BlockState state) {
		if (allowedToMine(state.getBlock())) {
			return super.getDestroySpeed(stack, state);
		} else {
			return 0.0F;
		}
	}

	@Override
	public boolean mineBlock(@NonNull ItemStack stack, @NonNull Level level, BlockState state, @NonNull BlockPos pos, @NonNull LivingEntity miningEntity) {
		if (allowedToSweepMine(state.getBlock())) {
			Player player = null;

			if (miningEntity instanceof Player) {
				player = (Player) miningEntity;
			}

			assert player != null;

			mine2x3(level, pos, player);
		}

		if (state.getDestroySpeed(level, pos) != 0.0F) {
			stack.hurtAndBreak(2, miningEntity, EquipmentSlot.MAINHAND);
		}
		return true;
	}

	private void mine2x3(Level level, BlockPos originPos, Player miner) {
		BlockPos[] gridPositions;

		if (miner.getMotionDirection() == Direction.SOUTH) {
			gridPositions = new BlockPos[]{
					new BlockPos(originPos.getX() + 1, originPos.getY(), originPos.getZ()),
					new BlockPos(originPos.getX() - 1, originPos.getY(), originPos.getZ()),
					new BlockPos(originPos.getX(), originPos.getY(), originPos.getZ()),
					new BlockPos(originPos.getX() + 1, originPos.getY(), originPos.getZ() + 1),
					new BlockPos(originPos.getX() - 1, originPos.getY(), originPos.getZ() + 1),
					new BlockPos(originPos.getX(), originPos.getY(), originPos.getZ() + 1),
			};
		} else if (miner.getMotionDirection() == Direction.NORTH) {
			gridPositions = new BlockPos[]{
					new BlockPos(originPos.getX() + 1, originPos.getY(), originPos.getZ()),
					new BlockPos(originPos.getX() - 1, originPos.getY(), originPos.getZ()),
					new BlockPos(originPos.getX(), originPos.getY(), originPos.getZ()),
					new BlockPos(originPos.getX() + 1, originPos.getY(), originPos.getZ() - 1),
					new BlockPos(originPos.getX() - 1, originPos.getY(), originPos.getZ() - 1),
					new BlockPos(originPos.getX(), originPos.getY(), originPos.getZ() - 1),
			};
		} else if (miner.getMotionDirection() == Direction.EAST) {
			gridPositions = new BlockPos[]{
					new BlockPos(originPos.getX(), originPos.getY(), originPos.getZ() + 1),
					new BlockPos(originPos.getX(), originPos.getY(), originPos.getZ() - 1),
					new BlockPos(originPos.getX(), originPos.getY(), originPos.getZ()),
					new BlockPos(originPos.getX() + 1, originPos.getY(), originPos.getZ() + 1),
					new BlockPos(originPos.getX() + 1, originPos.getY(), originPos.getZ() - 1),
					new BlockPos(originPos.getX() + 1, originPos.getY(), originPos.getZ()),
			};
		} else if (miner.getMotionDirection() == Direction.WEST) {
			gridPositions = new BlockPos[]{
					new BlockPos(originPos.getX(), originPos.getY(), originPos.getZ() + 1),
					new BlockPos(originPos.getX(), originPos.getY(), originPos.getZ() - 1),
					new BlockPos(originPos.getX(), originPos.getY(), originPos.getZ()),
					new BlockPos(originPos.getX() - 1, originPos.getY(), originPos.getZ() + 1),
					new BlockPos(originPos.getX() - 1, originPos.getY(), originPos.getZ() - 1),
					new BlockPos(originPos.getX() - 1, originPos.getY(), originPos.getZ()),
			};
		} else {
			gridPositions = new BlockPos[]{};
		}

		for (BlockPos pos : gridPositions) {
			Block block = level.getBlockState(pos).getBlock();
			if (allowedToSweepMine(block)) {
				level.destroyBlock(pos, !miner.isCreative(), miner, 0);
			}
		}
	}


	private boolean allowedToMine(Block block) {
		return block instanceof VegetationBlock || block instanceof WebBlock || block instanceof LeavesBlock;
	}
	private boolean allowedToSweepMine(Block block) {
		return block instanceof VegetationBlock;
	}

}
