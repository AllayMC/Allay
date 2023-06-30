package cn.allay.server.block.property;

import cn.allay.api.block.property.enums.PillarAxis;
import cn.allay.api.block.property.VanillaBlockPropertyTypes;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertSame;

/**
 * Allay Project 2023/4/9
 *
 * @author daoge_cmd
 */
public class BlockPropertyTest {

    @Test
    void testBooleanProperty() {
        var p1 = VanillaBlockPropertyTypes.AGE_BIT.createValue(true);
        var p2 = VanillaBlockPropertyTypes.AGE_BIT.createValue(true);
        var p3 = VanillaBlockPropertyTypes.AGE_BIT.createValue(false);
        assertSame(p1, p2);
        assertNotSame(p1, p3);
    }

    @Test
    void testEnumProperty() {
        var p1 = VanillaBlockPropertyTypes.PILLAR_AXIS.createValue(PillarAxis.X);
        var p2 = VanillaBlockPropertyTypes.PILLAR_AXIS.createValue(PillarAxis.Y);
        var p3 = VanillaBlockPropertyTypes.PILLAR_AXIS.createValue(PillarAxis.Y);
        var p4 = VanillaBlockPropertyTypes.PILLAR_AXIS.createValue(PillarAxis.Z);
        assertNotSame(p1, p2);
        assertSame(p2, p3);
        assertNotSame(p2, p4);
        assertNotSame(p3, p4);
    }

    @Test
    void testIntProperty() {
        var p1 = VanillaBlockPropertyTypes.CANDLES.createValue(0);
        var p2 = VanillaBlockPropertyTypes.CANDLES.createValue(1);
        var p3 = VanillaBlockPropertyTypes.CANDLES.createValue(2);
        var p4 = VanillaBlockPropertyTypes.CANDLES.createValue(3);
        var p5 = VanillaBlockPropertyTypes.CANDLES.createValue(3);
        assertNotSame(p1, p2);
        assertNotSame(p2, p3);
        assertNotSame(p3, p4);
        assertSame(p4, p5);
    }
}
