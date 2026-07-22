package org.allaymc.server.entity.component.projectile;

import org.allaymc.api.block.dto.Block;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.component.EntityArrowBaseComponent;
import org.allaymc.api.entity.component.EntityPhysicsComponent;
import org.allaymc.api.world.Dimension;
import org.junit.jupiter.api.Test;
import org.joml.Vector3d;
import org.joml.Vector3dc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Allay Project 2026/7/12
 *
 * @author Miroshka000
 */
class EntityArrowPhysicsComponentImplTest {

    @Test
    void blockHitShouldMarkArrowInGroundAndStopItImmediately() {
        var entity = mock(Entity.class);
        var arrow = mock(EntityArrowBaseComponent.class);
        when(arrow.getDimension()).thenReturn(mock(Dimension.class));

        var physics = new TestArrowPhysicsComponent(entity, arrow);
        physics.setRawMotion(0.5, -0.1, 0.25);
        physics.hitBlock(mock(Block.class), new Vector3d(1, 2, 3));

        assertEquals(new Vector3d(), physics.getMotion());
        verify(arrow).setInGround(true);
    }

    @Test
    void removedBlockShouldReleaseEmbeddedArrowBackToPhysics() {
        var arrow = mock(EntityArrowBaseComponent.class);
        when(arrow.isInGround()).thenReturn(true);
        when(arrow.checkBlockCollision()).thenReturn(false);

        var physics = new TestArrowPhysicsComponent(mock(Entity.class), arrow);
        var motion = physics.updateMotion(EntityPhysicsComponent.LiquidState.NONE);

        verify(arrow).setInGround(false);
        assertEquals(-0.0495, motion.y(), 1.0e-10);
    }

    @Test
    void embeddedArrowShouldRemainStoppedInLiquid() {
        var arrow = mock(EntityArrowBaseComponent.class);
        when(arrow.isInGround()).thenReturn(true);
        when(arrow.checkBlockCollision()).thenReturn(true);

        var physics = new TestArrowPhysicsComponent(mock(Entity.class), arrow);

        assertEquals(new Vector3d(), physics.updateMotion(new EntityPhysicsComponent.LiquidState(true, false)));
    }

    private static final class TestArrowPhysicsComponent extends EntityArrowPhysicsComponentImpl {
        private TestArrowPhysicsComponent(Entity entity, EntityArrowBaseComponent arrow) {
            this.thisEntity = entity;
            this.arrowBaseComponent = arrow;
        }

        private void hitBlock(Block block, Vector3dc hitPosition) {
            onHitBlock(block, hitPosition);
        }

        private void setRawMotion(double x, double y, double z) {
            this.motion.set(x, y, z);
        }
    }
}
