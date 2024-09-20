package org.allaymc.server.block.property;

import org.allaymc.api.block.property.enums.PillarAxis;
import org.allaymc.api.block.property.type.BlockPropertyTypes;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertSame;

/**
 * @author daoge_cmd
 */
public class BlockPropertyTest {
    @Test
    void testBooleanProperty() {
        var p1 = BlockPropertyTypes.AGE_BIT.createValue(true);
        var p2 = BlockPropertyTypes.AGE_BIT.createValue(true);
        var p3 = BlockPropertyTypes.AGE_BIT.createValue(false);
        assertSame(p1, p2);
        assertNotSame(p1, p3);
    }

    @Test
    void testEnumProperty() {
        var p1 = BlockPropertyTypes.PILLAR_AXIS.createValue(PillarAxis.X);
        var p2 = BlockPropertyTypes.PILLAR_AXIS.createValue(PillarAxis.Y);
        var p3 = BlockPropertyTypes.PILLAR_AXIS.createValue(PillarAxis.Y);
        var p4 = BlockPropertyTypes.PILLAR_AXIS.createValue(PillarAxis.Z);
        assertNotSame(p1, p2);
        assertSame(p2, p3);
        assertNotSame(p2, p4);
        assertNotSame(p3, p4);
    }

    @Test
    void testIntProperty() {
        var p1 = BlockPropertyTypes.CANDLES.createValue(0);
        var p2 = BlockPropertyTypes.CANDLES.createValue(1);
        var p3 = BlockPropertyTypes.CANDLES.createValue(2);
        var p4 = BlockPropertyTypes.CANDLES.createValue(3);
        var p5 = BlockPropertyTypes.CANDLES.createValue(3);
        assertNotSame(p1, p2);
        assertNotSame(p2, p3);
        assertNotSame(p3, p4);
        assertSame(p4, p5);
    }
}
