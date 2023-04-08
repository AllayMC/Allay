package cn.allay.block.property;

import cn.allay.block.property.vanilla.VanillaBlockPropertyTypes;
import cn.allay.block.property.vanilla.enums.PillarAxis;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Author: daoge_cmd <br>
 * Date: 2023/4/9 <br>
 * Allay Project <br>
 */
public class BlockPropertyTest {

    @Test
    void testBooleanProperty() {
        var p1 = VanillaBlockPropertyTypes.AGE_BIT.createProperty(true);
        var p2 = VanillaBlockPropertyTypes.AGE_BIT.createProperty(true);
        var p3 = VanillaBlockPropertyTypes.AGE_BIT.createProperty(false);
        assertSame(p1, p2);
        assertNotSame(p1, p3);
    }

    @Test
    void testEnumProperty() {
        var p1 = VanillaBlockPropertyTypes.PILLAR_AXIS.createProperty(PillarAxis.X);
        var p2 = VanillaBlockPropertyTypes.PILLAR_AXIS.createProperty(PillarAxis.Y);
        var p3 = VanillaBlockPropertyTypes.PILLAR_AXIS.createProperty(PillarAxis.Y);
        var p4 = VanillaBlockPropertyTypes.PILLAR_AXIS.createProperty(PillarAxis.Z);
        assertNotSame(p1, p2);
        assertSame(p2, p3);
        assertNotSame(p2, p4);
        assertNotSame(p3, p4);
    }

    @Test
    void testIntProperty() {
        var p1 = VanillaBlockPropertyTypes.CANDLES.createProperty(0);
        var p2 = VanillaBlockPropertyTypes.CANDLES.createProperty(1);
        var p3 = VanillaBlockPropertyTypes.CANDLES.createProperty(2);
        var p4 = VanillaBlockPropertyTypes.CANDLES.createProperty(3);
        var p5 = VanillaBlockPropertyTypes.CANDLES.createProperty(3);
        assertNotSame(p1, p2);
        assertNotSame(p2, p3);
        assertNotSame(p3, p4);
        assertSame(p4, p5);
    }
}
