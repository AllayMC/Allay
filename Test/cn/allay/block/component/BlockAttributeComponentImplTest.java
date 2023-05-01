package cn.allay.block.component;

import cn.allay.block.component.impl.attribute.BlockAttributeComponent;
import cn.allay.block.component.impl.attribute.BlockAttributeComponentImpl;
import cn.allay.block.component.impl.attribute.BlockAttributes;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Author: daoge_cmd <br>
 * Date: 2023/3/25 <br>
 * Allay Project <br>
 */
class BlockAttributeComponentImplTest {
    private static final String json = """
            {
                "aabb": "0.0005,0.0005,0.0005,0.9995,0.1825,0.9995",
                "canBeMovingBlock": true,
                "blocksPrecipitation": true,
                "breaksFallingBlocks": true,
                "burnAbility": 0,
                "burnChance": 0,
                "canBeBrokenFromFalling": true,
                "canContainLiquid": true,
                "color": -1,
                "explosionResistance": 15,
                "friction": 0.6,
                "hardness": 3,
                "hasBlockEntity": false,
                "hasComparatorSignal": false,
                "identifier": "minecraft:trapdoor",
                "isAlwaysDestroyable": true,
                "isContainerBlock": false,
                "isLiquid": false,
                "isMotionBlockingBlock": false,
                "isPowerSource": false,
                "isSolid": false,
                "isSolidBlocking": true,
                "isUnbreakable": false,
                "isWaterBlocking": true,
                "flammable": true,
                "light": 0,
                "lightEmission": 0,
                "pushesUpFallingBlocks": false,
                "superHot": false,
                "thickness": 0,
                "translucency": 0,
                "waterSpreadCausesSpawn": false
            }
            """;

    @Test
    void of() {
        var blockAttributes = BlockAttributes.of(json);
        //Check all the values
        assertEquals(Float.toHexString(0.0005f), Float.toHexString(blockAttributes.aabb().minX()));
        assertEquals(Float.toHexString(0.0005f), Float.toHexString(blockAttributes.aabb().minY()));
        assertEquals(Float.toHexString(0.0005f), Float.toHexString(blockAttributes.aabb().minZ()));
        assertEquals(Float.toHexString(0.9995f), Float.toHexString(blockAttributes.aabb().maxX()));
        assertEquals(Float.toHexString(0.1825f), Float.toHexString(blockAttributes.aabb().maxY()));
        assertEquals(Float.toHexString(0.9995f), Float.toHexString(blockAttributes.aabb().maxZ()));
        assertTrue(blockAttributes.canBeMovingBlock());
        assertTrue(blockAttributes.blocksPrecipitation());
        assertTrue(blockAttributes.breaksFallingBlocks());
        assertEquals(0, blockAttributes.burnAbility());
        assertEquals(0, blockAttributes.burnChance());
        assertTrue(blockAttributes.canBeBrokenFromFalling());
        assertTrue(blockAttributes.canContainLiquid());
        assertEquals(-1, blockAttributes.color());
        assertEquals(15, blockAttributes.explosionResistance());
        assertEquals(Float.toHexString(0.6f), Float.toHexString(blockAttributes.friction()));
        assertEquals(3, blockAttributes.hardness());
        assertFalse(blockAttributes.hasBlockEntity());
        assertFalse(blockAttributes.hasComparatorSignal());
        assertTrue(blockAttributes.isAlwaysDestroyable());
        assertFalse(blockAttributes.isContainerBlock());
        assertFalse(blockAttributes.isLiquid());
        assertFalse(blockAttributes.isMotionBlockingBlock());
        assertFalse(blockAttributes.isPowerSource());
        assertFalse(blockAttributes.isSolid());
        assertTrue(blockAttributes.isSolidBlocking());
        assertFalse(blockAttributes.isUnbreakable());
        assertTrue(blockAttributes.isWaterBlocking());
        assertTrue(blockAttributes.flammable());
        assertEquals(0, blockAttributes.light());
        assertEquals(0, blockAttributes.lightEmission());
        assertFalse(blockAttributes.pushesUpFallingBlocks());
        assertFalse(blockAttributes.superHot());
        assertEquals(0, blockAttributes.thickness());
        assertEquals(0, blockAttributes.translucency());
        assertFalse(blockAttributes.waterSpreadCausesSpawn());
    }
}