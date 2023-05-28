package cn.allay.block.component;

import cn.allay.block.component.impl.attribute.BlockAttributes;
import cn.allay.block.component.impl.attribute.VanillaBlockAttributeRegistry;
import cn.allay.block.data.VanillaBlockId;
import cn.allay.block.type.BlockInitInfo;
import cn.allay.block.type.BlockType;
import cn.allay.block.type.BlockTypeRegistry;
import cn.allay.math.position.Pos;
import cn.allay.testutils.AllayTestExtension;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Author: daoge_cmd <br>
 * Date: 2023/3/25 <br>
 * Allay Project <br>
 */
@ExtendWith(AllayTestExtension.class)
@Slf4j
class BlockAttributesTest {
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
                "color": {
                            "a": 255,
                            "b": 178,
                            "g": 76,
                            "hexString": "#ff334cb2",
                            "nearestColorCode": "§9",
                            "r": 51
                        },
                "explosionResistance": 15,
                "friction": 0.6,
                "hardness": 3,
                "hasBlockEntity": false,
                "hasComparatorSignal": false,
                "name": "minecraft:trapdoor",
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
    void testDeserialization() {
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
        assertEquals(new Color(51,76, 178, 255), blockAttributes.color());
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

    @Test
    void testVanillaBlockAttributes() {
        for (var vanillaBlockId : VanillaBlockId.values()) {
            log.info("Testing block type: " + vanillaBlockId.getNamespaceId());
            testBlockType(vanillaBlockId, BlockTypeRegistry.getRegistry().get(vanillaBlockId.getNamespaceId()));
        }
//        System.out.println("Missing: " + missing.size());
    }

//    static List<BlockState<?>> missing = new ArrayList<>();

    void testBlockType(VanillaBlockId vanillaBlockId, BlockType<?> type) {
        var block = type.createBlock(new BlockInitInfo.Simple(Pos.of(0, 1, 2, null)));
        var attributeMap = VanillaBlockAttributeRegistry.getRegistry().get(vanillaBlockId);
        for (var state : type.allStates().values()) {
            block.setState(state);
            var expected = attributeMap.get(state.getBlockStateHash());
//            if (expected == null) {
//                missing.add(state);
//                continue;
//            }
            assertNotNull(expected, "Missing block attributes for state: " + state + ", Block: " + type.getNamespaceId());
            assertEquals(attributeMap.get(state.getBlockStateHash()), block.getBlockAttributes());
        }
    }
}