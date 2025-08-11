package org.allaymc.server.block.component;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.component.EntityPhysicsComponent;

/**
 * @author daoge_cmd
 */
public class BlockSlimeBaseComponentImpl extends BlockBaseComponentImpl {
    public BlockSlimeBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    @Override
    public void onEntityFallOn(Entity entity, Block block) {
        if (!(entity instanceof EntityPhysicsComponent physicsComponent)) {
            return;
        }

        // Client will handle the bounce
        if (!physicsComponent.computeMovementServerSide()) {
            return;
        }

        var lastMotion = physicsComponent.getLastMotion();
        physicsComponent.setMotion(lastMotion.x(), -lastMotion.y(), lastMotion.z());
    }

    @Override
    public float getFallDamageReductionFactor() {
        return 1f;
    }
}
