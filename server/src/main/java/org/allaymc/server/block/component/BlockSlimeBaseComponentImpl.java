package org.allaymc.server.block.component;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.entity.Entity;

/**
 * @author daoge_cmd
 */
public class BlockSlimeBaseComponentImpl extends BlockBaseComponentImpl {
    public BlockSlimeBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    @Override
    public void onEntityFallOn(Entity entity, Block block) {
        // Client will handle the bounce
        if (!entity.computeMovementServerSide()) {
            return;
        }

        var lastMotion = entity.getLastMotion();
        entity.setMotion(lastMotion.x(), -lastMotion.y(), lastMotion.z());
    }

    @Override
    public float getFallDamageReductionFactor() {
        return 1f;
    }
}
