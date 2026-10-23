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
 * {@code EntityArrowPhysicsComponentImplTest} verifies immediate arrow
 * embedding and support-sensitive motion updates.
 *
 * @author Miroshka000
 */
class EntityArrowPhysicsComponentImplTest {

    /**
     * Applies a block hit to an arrow with existing motion. The hit must mark
     * the arrow embedded and zero stored motion immediately, before another
     * engine motion update runs.
     */
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

    /**
     * Updates an embedded arrow after its block collision disappears. The
     * arrow must clear its embedded flag and receive the normal
     * gravity-and-air-drag vertical motion in that update.
     */
    @Test
    void removedBlockShouldReleaseEmbeddedArrowBackToPhysics() {
        var arrow = mock(EntityArrowBaseComponent.class);
        when(arrow.isInGround()).thenReturn(true);
        when(arrow.checkBlockCollision()).thenReturn(false);

        var physics = new TestArrowPhysicsComponent(mock(Entity.class), arrow);
        var motion = physics.updateMotion(EntityPhysicsComponent.LiquidState.NONE);

        verify(arrow).setInGround(false);
        // Permit floating-point rounding while checking gravity followed by one
        // percent air drag.
        assertEquals(-0.0495, motion.y(), 1.0e-10);
    }

    /**
     * Updates an embedded arrow that still collides with its supporting block
     * while in water. Embedded-state handling must return zero motion before
     * the liquid branch can accelerate the arrow.
     */
    @Test
    void embeddedArrowShouldRemainStoppedInLiquid() {
        var arrow = mock(EntityArrowBaseComponent.class);
        when(arrow.isInGround()).thenReturn(true);
        when(arrow.checkBlockCollision()).thenReturn(true);

        var physics = new TestArrowPhysicsComponent(mock(Entity.class), arrow);

        assertEquals(new Vector3d(), physics.updateMotion(new EntityPhysicsComponent.LiquidState(true, false)));
    }

    /**
     * {@code TestArrowPhysicsComponent} exposes block hits and raw motion for
     * isolated arrow physics tests.
     */
    private static final class TestArrowPhysicsComponent extends EntityArrowPhysicsComponentImpl {
        /**
         * Creates test physics retaining the supplied entity and arrow
         * component mocks. The fixture injects only those references, so each
         * test must restrict execution to paths whose dependencies it has
         * arranged.
         */
        private TestArrowPhysicsComponent(Entity entity, EntityArrowBaseComponent arrow) {
            this.thisEntity = entity;
            this.arrowBaseComponent = arrow;
        }

        /**
         * Invokes the protected arrow block-impact callback for the supplied
         * test hit.
         */
        private void hitBlock(Block block, Vector3dc hitPosition) {
            onHitBlock(block, hitPosition);
        }

        /**
         * Replaces stored motion directly for arranging the test starting
         * velocity. The helper bypasses motion events and validation so the
         * fixture can exercise the block-hit transition without unrelated
         * component setup.
         */
        private void setRawMotion(double x, double y, double z) {
            this.motion.set(x, y, z);
        }
    }
}
