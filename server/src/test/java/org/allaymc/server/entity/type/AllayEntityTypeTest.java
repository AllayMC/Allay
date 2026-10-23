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
 * {@code AllayEntityTypeTest} checks location initialization through a
 * registered built-in entity factory.
 *
 * @author daoge_cmd
 */
@ExtendWith(AllayTestExtension.class)
class AllayEntityTypeTest {
    /**
     * Shared sheep instance constructed for the initialization assertions.
     */
    static EntitySheep sheep;
    /**
     * {@code mockDimension} supplies a retained dimension identity for the
     * constructed sheep. The test compares that same reference through the
     * resulting location rather than simulating world behavior.
     */
    static Dimension mockDimension = Mockito.mock(Dimension.class);

    /**
     * Creates a sheep using {@code float}-encoded position data and the mocked
     * dimension.
     */
    @BeforeAll
    static void init() {
        sheep = EntityTypes.SHEEP.createEntity(EntityInitInfo.builder().pos(0, 1, 2).dimension(mockDimension).build());
    }

    /**
     * Constructs a sheep through the registered type with explicit position
     * and dimension. The assertion protects propagation of those
     * initialization values into the entity's location, including its default
     * zero rotation.
     */
    @Test
    void testCommon() {
        assertEquals(new Location3d(0f, 1f, 2f, 0, 0, mockDimension), sheep.getLocation());
    }
}