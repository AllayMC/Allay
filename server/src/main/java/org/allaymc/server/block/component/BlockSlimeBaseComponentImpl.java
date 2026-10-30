package org.allaymc.server.block.component;

import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.component.EntityPhysicsComponent;

/**
 * {@code BlockSlimeBaseComponentImpl} reverses vertical movement for entities whose
 * physics is computed on the server and eliminates landing fall damage. Entities
 * controlled through another movement path keep their own bounce handling, avoiding a
 * second server-applied reversal for the same landing.
 *
 * @author daoge_cmd
 */
public class BlockSlimeBaseComponentImpl extends BlockBaseComponentImpl {
    /**
     * Creates the slime block behavior for the supplied block type.
     */
    public BlockSlimeBaseComponentImpl(BlockType<? extends BlockBehavior> blockType) {
        super(blockType);
    }

    /**
     * Reverses the previous vertical motion for entities with server-computed
     * physics. Entities without a physics component or using client-computed movement
     * are skipped so their existing movement handling supplies the bounce.
     */
    @Override
    public void onEntityFallOn(Entity entity, Block block) {
        if (!(entity instanceof EntityPhysicsComponent physicsComponent)) {
            return;
        }
        // Client-computed movement already supplies its own bounce. Skip the server
        // reversal so this landing does not receive two independent motion changes.
        if (!physicsComponent.computeMovementServerSide()) {
            return;
        }

        var lastMotion = physicsComponent.getLastMotion();
        physicsComponent.setMotion(lastMotion.x(), -lastMotion.y(), lastMotion.z());
    }

    /**
     * Returns a full reduction factor that eliminates landing fall damage.
     */
    @Override
    public float getFallDamageReductionFactor() {
        return 1f;
    }
}
