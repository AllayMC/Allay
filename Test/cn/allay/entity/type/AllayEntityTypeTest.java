package cn.allay.entity.type;

import cn.allay.entity.impl.EntitySheep;
import cn.allay.math.location.Loc;
import cn.allay.testutils.AllayTestExtension;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Author: daoge_cmd <br>
 * Date: 2023/5/26 <br>
 * Allay Project <br>
 */
@ExtendWith(AllayTestExtension.class)
class AllayEntityTypeTest {
    static EntitySheep sheep;

    @BeforeAll
    static void init() {
        sheep = EntitySheep.TYPE.createEntity(new EntityInitInfo.Simple(Loc.of(0f, 1f, 2f, null)));
    }

    @Test
    void testCommon() {
        assertEquals(Loc.of(0f, 1f, 2f, null), sheep.getLocation());
    }
}