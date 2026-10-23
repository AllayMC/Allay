package org.allaymc.server.entity.component.projectile;

import org.allaymc.api.entity.EntityInitInfo;
import org.allaymc.api.entity.type.EntityTypes;
import org.allaymc.api.world.Dimension;
import org.allaymc.testutils.AllayTestExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

/**
 * {@code EntityArrowInGroundStateTest} verifies arrow embedding survives a complete
 * entity persistence round trip.
 *
 * @author Miroshka000
 */
@ExtendWith(AllayTestExtension.class)
class EntityArrowInGroundStateTest {

    /**
     * Saves an arrow marked as embedded and recreates it from that entity
     * data. The restored arrow must preserve the embedded flag before another
     * physics update changes it.
     */
    @Test
    void shouldPreserveInGroundStateAcrossSaveAndLoad() {
        var dimension = mock(Dimension.class);
        var arrow = EntityTypes.ARROW.createEntity(EntityInitInfo.builder()
                .dimension(dimension)
                .pos(0, 64, 0)
                .build());
        arrow.setInGround(true);

        var restoredArrow = EntityTypes.ARROW.createEntity(EntityInitInfo.builder()
                .dimension(dimension)
                .nbt(arrow.saveNBT())
                .build());

        assertTrue(restoredArrow.isInGround());
    }
}
