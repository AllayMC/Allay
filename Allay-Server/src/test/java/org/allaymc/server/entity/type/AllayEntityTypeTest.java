package org.allaymc.server.entity.type;

import org.allaymc.api.entity.init.SimpleEntityInitInfo;
import org.allaymc.api.entity.interfaces.sheep.EntitySheep;
import org.allaymc.api.math.location.Location3f;
import org.allaymc.api.world.World;
import org.allaymc.testutils.AllayTestExtension;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Allay Project 2023/5/26
 *
 * @author daoge_cmd
 */
@ExtendWith(AllayTestExtension.class)
class AllayEntityTypeTest {
    static EntitySheep sheep;
    static World mockWorld = Mockito.mock(World.class);

    @BeforeAll
    static void init() {
        sheep = EntitySheep.SHEEP_TYPE.createEntity(SimpleEntityInitInfo.builder().pos(0, 1, 2).world(mockWorld).build());
    }

    @Test
    void testCommon() {
        assertEquals(new Location3f(0f, 1f, 2f, 0, 0, 0, mockWorld), sheep.getLocation());
    }
}