package cn.allay.block.component;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Author: daoge_cmd <br>
 * Date: 2023/3/25 <br>
 * Allay Project <br>
 */
class BlockDataComponentImplTest {
    private static final String json = "{\n" +
            "        \"name\":\"STONE\",\n" +
            "        \"fallable\":false,\n" +
            "        \"friction\":0.6,\n" +
            "        \"hardness\":1.5,\n" +
            "        \"resistance\":30,\n" +
            "        \"solid\":true,\n" +
            "        \"pushesOutItems\":true,\n" +
            "        \"translucency\":0,\n" +
            "        \"burnChance\":0,\n" +
            "        \"burnAbility\":0,\n" +
            "        \"filterLight\":15,\n" +
            "        \"experimental\":false,\n" +
            "        \"flammable\":false,\n" +
            "        \"replaceable\":false,\n" +
            "        \"emitLight\":0,\n" +
            "        \"diggable\":true,\n" +
            "        \"powerSource\":false,\n" +
            "        \"breakFalling\":false,\n" +
            "        \"blockWater\":true,\n" +
            "        \"canBeSilkTouched\":true,\n" +
            "        \"blockSolid\":true,\n" +
            "        \"blockMotion\":true,\n" +
            "        \"comparatorSignal\":false,\n" +
            "        \"pushUpFalling\":false,\n" +
            "        \"waterlogFlowing\":true,\n" +
            "        \"waterlogSolid\":false,\n" +
            "        \"color\":-1,\n" +
            "        \"aabb\":\"new SimpleAxisAlignedBB(0,0,0,1,1,1)\",\n" +
            "        \"targetTool\":\"ToolTypes.PICKAXE\"\n" +
            "    }";

    @Test
    void of() {
        BlockDataComponent blockDataComponent = BlockDataComponentImpl.of(json);
        assertFalse(blockDataComponent.fallable());
        assertEquals(0.6f, blockDataComponent.friction());
        assertEquals(1.5f, blockDataComponent.hardness());
        assertEquals(30, blockDataComponent.resistance());
        assertTrue(blockDataComponent.solid());
        assertTrue(blockDataComponent.pushesOutItems());
        assertEquals(0, blockDataComponent.translucency());
        assertEquals(0, blockDataComponent.burnChance());
        assertEquals(0, blockDataComponent.burnAbility());
        assertEquals(15, blockDataComponent.lightFilterFactor());
        assertFalse(blockDataComponent.experimental());
        assertFalse(blockDataComponent.flammable());
        assertFalse(blockDataComponent.replaceable());
        assertEquals(0, blockDataComponent.emitLightFactor());
        assertTrue(blockDataComponent.diggable());
        assertFalse(blockDataComponent.powerSource());
        assertFalse(blockDataComponent.canBreakFallingBlock());
        assertTrue(blockDataComponent.canBlockWater());
        assertTrue(blockDataComponent.canBeSilkTouched());
        assertTrue(blockDataComponent.blockSolid());
        assertTrue(blockDataComponent.canBeMovingBlock());
        assertFalse(blockDataComponent.comparatorSignal());
        assertFalse(blockDataComponent.pushUpFalling());
        assertTrue(blockDataComponent.waterlogFlowing());
        assertFalse(blockDataComponent.waterlogSolid());
        assertEquals(-1, blockDataComponent.color());
        assertEquals("new SimpleAxisAlignedBB(0,0,0,1,1,1)", blockDataComponent.axisAlignedBB().toString());
    }
}