package org.allaymc.server.entity.type;

import org.allaymc.api.entity.EntityInitInfo;
import org.allaymc.api.entity.interfaces.EntitySheep;
import org.allaymc.api.entity.type.EntityTypes;
import org.allaymc.api.math.location.Location3d;
import org.allaymc.api.world.Dimension;
import org.allaymc.testutils.AllayTestExtension;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author daoge_cmd
 */
@ExtendWith(AllayTestExtension.class)
class AllayEntityTypeTest {
    static EntitySheep sheep;
    static Dimension mockDimension = Mockito.mock(Dimension.class);

    @BeforeAll
    static void init() {
        sheep = EntityTypes.SHEEP.createEntity(EntityInitInfo.builder().pos(0, 1, 2).dimension(mockDimension).build());
    }

    @Test
    void testCommon() {
        assertEquals(new Location3d(0f, 1f, 2f, 0, 0, mockDimension), sheep.getLocation());
    }
}