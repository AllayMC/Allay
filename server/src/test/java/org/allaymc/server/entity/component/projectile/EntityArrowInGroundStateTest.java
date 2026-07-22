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
 * Allay Project 2026/7/12
 *
 * @author Miroshka000
 */
@ExtendWith(AllayTestExtension.class)
class EntityArrowInGroundStateTest {

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
