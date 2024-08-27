package org.allaymc.server.block.component;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.entity.Entity;

/**
 * Allay Project 2024/8/28
 *
 * @author daoge_cmd
 */
public class BlockSlimeBaseComponentImpl extends BlockBaseComponentImpl {
    public BlockSlimeBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    @Override
    public void onEntityFallOn(Entity entity, BlockState blockState) {
        // Client will handle the bounce
        if (!entity.computeMovementServerSide()) return;
        var currentMotion = entity.getMotion();
        entity.setMotion(currentMotion.x(), -currentMotion.y(), currentMotion.z());
    }

    @Override
    public boolean canResetFallDistance() {
        return true;
    }
}
