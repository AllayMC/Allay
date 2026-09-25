package org.allaymc.server.block.dispenser;

import org.allaymc.api.block.data.BlockFace;
import org.allaymc.api.block.dispenser.DispenseBehavior;
import org.allaymc.api.block.dispenser.DispenseResult;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.interfaces.BlockLiquidBehavior;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.entity.EntityInitInfo;
import org.allaymc.api.entity.type.EntityType;
import org.allaymc.api.entity.type.EntityTypes;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.math.MathUtils;
import org.joml.Vector3i;
import org.joml.Vector3ic;

public class BoatDispenseBehavior implements DispenseBehavior {

    @Override
    public DispenseResult dispense(Block block, BlockFace face, ItemStack item) {
        var dimension = block.getDimension();
        var targetPos = face.offsetPos(block.getPosition());
        var targetBlock = dimension.getBlockState(targetPos);

        Vector3ic spawnPos;
        if (isWater(targetBlock)) {
            spawnPos = targetPos;
        } else {
            var abovePos = new Vector3i(targetPos.x(), targetPos.y() + 1, targetPos.z());
            if (dimension.getBlockState(abovePos).getBlockType() != BlockTypes.AIR) {
                return DispenseResult.fail();
            }
            spawnPos = abovePos;
        }

        var entity = getEntityType().createEntity(
                EntityInitInfo.builder()
                        .dimension(dimension)
                        .pos(MathUtils.center(spawnPos))
                        .build()
        );
        if (entity != null) {
            dimension.getEntityManager().addEntity(entity);
        }
        return DispenseResult.success();
    }

    protected EntityType<?> getEntityType() {
        return EntityTypes.BOAT;
    }

    private boolean isWater(BlockState blockState) {
        if (!(blockState.getBehavior() instanceof BlockLiquidBehavior)) {
            return false;
        }
        var blockType = blockState.getBlockType();
        return blockType == BlockTypes.WATER || blockType == BlockTypes.FLOWING_WATER;
    }
}