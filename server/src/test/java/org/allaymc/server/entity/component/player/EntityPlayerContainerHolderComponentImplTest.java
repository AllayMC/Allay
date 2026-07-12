package org.allaymc.server.entity.component.player;

import org.allaymc.api.entity.interfaces.EntityArrow;
import org.junit.jupiter.api.Test;
import org.joml.Vector3d;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Allay Project 2026/7/12
 *
 * @author Miroshka000
 */
class EntityPlayerContainerHolderComponentImplTest {

    @Test
    void embeddedArrowShouldBeReadyEvenIfItsPreviousMotionWasNonZero() {
        var arrow = mock(EntityArrow.class);
        when(arrow.isInGround()).thenReturn(true);
        when(arrow.getMotion()).thenReturn(new Vector3d(0.1, -0.05, 0.1));

        assertTrue(EntityPlayerContainerHolderComponentImpl.isArrowReadyForPickup(arrow));
    }

    @Test
    void stationaryArrowShouldNotBePickableBeforeHittingBlock() {
        var arrow = mock(EntityArrow.class);
        when(arrow.isInGround()).thenReturn(false);
        when(arrow.getMotion()).thenReturn(new Vector3d());

        assertFalse(EntityPlayerContainerHolderComponentImpl.isArrowReadyForPickup(arrow));
    }
}
