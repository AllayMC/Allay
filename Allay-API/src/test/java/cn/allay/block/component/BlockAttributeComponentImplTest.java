package cn.allay.block.component;

import cn.allay.block.component.attribute.BlockAttributeComponent;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Author: daoge_cmd <br>
 * Date: 2023/3/25 <br>
 * Allay Project <br>
 */
class BlockAttributeComponentImplTest {
    private static final String json = "{\n" +
            "        \"aabb\": \"0.0005,0.0005,0.0005,0.9995,0.1825,0.9995\",\n" +
            "        \"canBeMovingBlock\": true,\n" +
            "        \"blocksPrecipitation\": true,\n" +
            "        \"breaksFallingBlocks\": true,\n" +
            "        \"burnAbility\": 0,\n" +
            "        \"burnChance\": 0,\n" +
            "        \"canBeBrokenFromFalling\": true,\n" +
            "        \"canContainLiquid\": true,\n" +
            "        \"color\": -1,\n" +
            "        \"explosionResistance\": 15,\n" +
            "        \"friction\": 0.6,\n" +
            "        \"hardness\": 3,\n" +
            "        \"hasBlockEntity\": false,\n" +
            "        \"hasComparatorSignal\": false,\n" +
            "        \"identifier\": \"minecraft:trapdoor\",\n" +
            "        \"isAlwaysDestroyable\": true,\n" +
            "        \"isContainerBlock\": false,\n" +
            "        \"isLiquid\": false,\n" +
            "        \"isMotionBlockingBlock\": false,\n" +
            "        \"isPowerSource\": false,\n" +
            "        \"isSolid\": false,\n" +
            "        \"isSolidBlocking\": true,\n" +
            "        \"isUnbreakable\": false,\n" +
            "        \"isWaterBlocking\": true,\n" +
            "        \"flammable\": true,\n" +
            "        \"light\": 0,\n" +
            "        \"lightEmission\": 0,\n" +
            "        \"pushesUpFallingBlocks\": false,\n" +
            "        \"superHot\": false,\n" +
            "        \"thickness\": 0,\n" +
            "        \"translucency\": 0,\n" +
            "        \"waterSpreadCausesSpawn\": false\n" +
            "    }";

    @Test
    void of() {
        BlockAttributeComponent blockAttributeComponent = BlockAttributeComponent.of(json);
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