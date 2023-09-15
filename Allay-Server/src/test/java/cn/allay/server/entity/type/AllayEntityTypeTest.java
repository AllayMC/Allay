package cn.allay.server.entity.type;

import cn.allay.api.entity.init.EntityInitInfo;
import cn.allay.api.entity.init.SimpleEntityInitInfo;
import cn.allay.api.entity.interfaces.EntitySheep;
import cn.allay.api.math.location.Location3f;
import cn.allay.api.world.World;
import cn.allay.testutils.AllayTestExtension;
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