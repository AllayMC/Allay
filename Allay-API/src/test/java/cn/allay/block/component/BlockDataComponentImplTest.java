package cn.allay.block.component;

import cn.allay.block.data.ToolType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Author: daoge_cmd <br>
 * Date: 2023/3/25 <br>
 * Allay Project <br>
 */
class BlockDataComponentImplTest {
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
        BlockDataComponent blockDataComponent = BlockDataComponent.of(json);
        //Check all the values
        assertEquals(Float.toHexString(0.0005f), Float.toHexString(blockDataComponent.aabb().minX()));
        assertEquals(Float.toHexString(0.0005f), Float.toHexString(blockDataComponent.aabb().minY()));
        assertEquals(Float.toHexString(0.0005f), Float.toHexString(blockDataComponent.aabb().minZ()));
        assertEquals(Float.toHexString(0.9995f), Float.toHexString(blockDataComponent.aabb().maxX()));
        assertEquals(Float.toHexString(0.1825f), Float.toHexString(blockDataComponent.aabb().maxY()));
        assertEquals(Float.toHexString(0.9995f), Float.toHexString(blockDataComponent.aabb().maxZ()));
        assertTrue(blockDataComponent.canBeMovingBlock());
        assertTrue(blockDataComponent.blocksPrecipitation());
        assertTrue(blockDataComponent.breaksFallingBlocks());
        assertEquals(0, blockDataComponent.burnAbility());
        assertEquals(0, blockDataComponent.burnChance());
        assertTrue(blockDataComponent.canBeBrokenFromFalling());
        assertTrue(blockDataComponent.canContainLiquid());
        assertEquals(-1, blockDataComponent.color());
        assertEquals(15, blockDataComponent.explosionResistance());
        assertEquals(Float.toHexString(0.6f), Float.toHexString(blockDataComponent.friction()));
        assertEquals(3, blockDataComponent.hardness());
        assertFalse(blockDataComponent.hasBlockEntity());
        assertFalse(blockDataComponent.hasComparatorSignal());
        assertTrue(blockDataComponent.isAlwaysDestroyable());
        assertFalse(blockDataComponent.isContainerBlock());
        assertFalse(blockDataComponent.isLiquid());
        assertFalse(blockDataComponent.isMotionBlockingBlock());
        assertFalse(blockDataComponent.isPowerSource());
        assertFalse(blockDataComponent.isSolid());
        assertTrue(blockDataComponent.isSolidBlocking());
        assertFalse(blockDataComponent.isUnbreakable());
        assertTrue(blockDataComponent.isWaterBlocking());
        assertTrue(blockDataComponent.flammable());
        assertEquals(0, blockDataComponent.light());
        assertEquals(0, blockDataComponent.lightEmission());
        assertFalse(blockDataComponent.pushesUpFallingBlocks());
        assertFalse(blockDataComponent.superHot());
        assertEquals(0, blockDataComponent.thickness());
        assertEquals(0, blockDataComponent.translucency());
        assertFalse(blockDataComponent.waterSpreadCausesSpawn());
    }
}