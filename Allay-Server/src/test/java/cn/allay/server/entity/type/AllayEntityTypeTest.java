package cn.allay.server.entity.type;

import cn.allay.api.entity.impl.EntitySheep;
import cn.allay.api.entity.type.EntityInitInfo;
import cn.allay.api.data.VanillaEntityTypes;
import cn.allay.api.math.location.Loc;
import cn.allay.testutils.AllayTestExtension;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Allay Project 2023/5/26
 *
 * @author daoge_cmd
 */
@ExtendWith(AllayTestExtension.class)
class AllayEntityTypeTest {
    static EntitySheep sheep;

    @BeforeAll
    static void init() {
        sheep = VanillaEntityTypes.SHEEP_TYPE.createEntity(new EntityInitInfo.Simple(Loc.of(0f, 1f, 2f, null)));
    }

    @Test
    void testCommon() {
        assertEquals(Loc.of(0f, 1f, 2f, null), sheep.getLocation());
    }
}