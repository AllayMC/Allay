package cn.allay.block.component;

import cn.allay.block.component.impl.attribute.BlockAttributeComponent;
import cn.allay.block.component.impl.attribute.BlockAttributeComponentImpl;
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
        BlockAttributeComponent blockAttributeComponent = BlockAttributeComponentImpl.of(json);
        //Check all the values
        assertEquals(Float.toHexString(0.0005f), Float.toHexString(blockAttributeComponent.aabb().minX()));
        assertEquals(Float.toHexString(0.0005f), Float.toHexString(blockAttributeComponent.aabb().minY()));
        assertEquals(Float.toHexString(0.0005f), Float.toHexString(blockAttributeComponent.aabb().minZ()));
        assertEquals(Float.toHexString(0.9995f), Float.toHexString(blockAttributeComponent.aabb().maxX()));
        assertEquals(Float.toHexString(0.1825f), Float.toHexString(blockAttributeComponent.aabb().maxY()));
        assertEquals(Float.toHexString(0.9995f), Float.toHexString(blockAttributeComponent.aabb().maxZ()));
        assertTrue(blockAttributeComponent.canBeMovingBlock());
        assertTrue(blockAttributeComponent.blocksPrecipitation());
        assertTrue(blockAttributeComponent.breaksFallingBlocks());
        assertEquals(0, blockAttributeComponent.burnAbility());
        assertEquals(0, blockAttributeComponent.burnChance());
        assertTrue(blockAttributeComponent.canBeBrokenFromFalling());
        assertTrue(blockAttributeComponent.canContainLiquid());
        assertEquals(-1, blockAttributeComponent.color());
        assertEquals(15, blockAttributeComponent.explosionResistance());
        assertEquals(Float.toHexString(0.6f), Float.toHexString(blockAttributeComponent.friction()));
        assertEquals(3, blockAttributeComponent.hardness());
        assertFalse(blockAttributeComponent.hasBlockEntity());
        assertFalse(blockAttributeComponent.hasComparatorSignal());
        assertTrue(blockAttributeComponent.isAlwaysDestroyable());
        assertFalse(blockAttributeComponent.isContainerBlock());
        assertFalse(blockAttributeComponent.isLiquid());
        assertFalse(blockAttributeComponent.isMotionBlockingBlock());
        assertFalse(blockAttributeComponent.isPowerSource());
        assertFalse(blockAttributeComponent.isSolid());
        assertTrue(blockAttributeComponent.isSolidBlocking());
        assertFalse(blockAttributeComponent.isUnbreakable());
        assertTrue(blockAttributeComponent.isWaterBlocking());
        assertTrue(blockAttributeComponent.flammable());
        assertEquals(0, blockAttributeComponent.light());
        assertEquals(0, blockAttributeComponent.lightEmission());
        assertFalse(blockAttributeComponent.pushesUpFallingBlocks());
        assertFalse(blockAttributeComponent.superHot());
        assertEquals(0, blockAttributeComponent.thickness());
        assertEquals(0, blockAttributeComponent.translucency());
        assertFalse(blockAttributeComponent.waterSpreadCausesSpawn());
    }
}