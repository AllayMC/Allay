package cn.allay.server.mapping;

import cn.allay.api.data.VanillaBlockTypes;
import cn.allay.api.mapping.JeBlockState;
import cn.allay.api.mapping.Mapping;
import cn.allay.testutils.AllayTestExtension;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(AllayTestExtension.class)
public class MappingTest {
    @Test
    void testGetJeBlockState() {
        JeBlockState jeBlockState = Mapping.getJeBlockState(VanillaBlockTypes.WOOD_TYPE.getDefaultState().blockStateHash());
        assert jeBlockState != null;
        Assertions.assertEquals("minecraft:acacia_wood[axis=x]", jeBlockState.toString());
    }

    @Test
    void testGetJeBiomeName() {
        String value = Mapping.getJeBiomeName(48);
        assert value != null;
        Assertions.assertEquals("minecraft:bamboo_jungle", value);
    }

    @Test
    void testGetBeBlockStateHash() {
        JeBlockState jeBlockState = new JeBlockState("minecraft:acacia_wood[axis=x]");
        Integer value = Mapping.getBeBlockStateHash(jeBlockState);
        assert value != null;
        Assertions.assertEquals(-1715809305, value);
    }

    @Test
    void testGetBeBiomeId() {
        Integer value = Mapping.getBeBiomeId("minecraft:birch_forest");
        assert value != null;
        Assertions.assertEquals(27, value);
    }
}
