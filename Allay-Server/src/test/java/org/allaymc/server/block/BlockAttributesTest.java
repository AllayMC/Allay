package org.allaymc.server.block;

import org.allaymc.api.block.component.common.BlockAttributes;
import org.allaymc.api.block.registry.VanillaBlockAttributeRegistry;
import org.allaymc.api.block.registry.BlockTypeRegistry;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.testutils.AllayTestExtension;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Allay Project 2023/3/25
 *
 * @author Cool_Loong
 */
@ExtendWith(AllayTestExtension.class)
@Slf4j
class BlockAttributesTest {
    private static final String json = """
            {
                "aabbCollision": "0.0005,0.0005,0.0005,0.9995,0.1825,0.9995",
                "burnAbility": 0,
                "burnChance": 0,
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
                "hasCollision": true,
                "name": "minecraft:trapdoor",
                "isAlwaysDestroyable": true,
                "isContainerBlock": false,
                "isLiquid": false,
                "isMotionBlockingBlock": false,
                "isPowerSource": false,
                "isSolid": false,
                "isUnbreakable": false,
                "isWaterBlocking": true,
                "isLavaFlammable": true,
                "lightDampening": 0,
                "lightEmission": 0,
                "pushesUpFallingBlocks": false,
                "superHot": false,
                "thickness": 0,
                "translucency": 0,
                "isAir": true,
                "isBounceBlock": true,
                "isButtonBlock": true,
                "isCropBlock": true,
                "isDoorBlock": true,
                "isFallingBlock": true,
                "isFenceBlock": true,
                "isFenceGateBlock": true,
                "isLavaFlammable": true,
                "isSlabBlock": true,
                "isStemBlock": true,
                "isThinFenceBlock": true
            }
            """;

    @Test
    void testDeserialization() {
        var blockAttributes = BlockAttributes.fromJson(json);
        //Check all the values
        assertEquals(Float.toHexString(0.0005f), Float.toHexString(blockAttributes.voxelShape().unionAABB().minX()));
        assertEquals(Float.toHexString(0.0005f), Float.toHexString(blockAttributes.voxelShape().unionAABB().minY()));
        assertEquals(Float.toHexString(0.0005f), Float.toHexString(blockAttributes.voxelShape().unionAABB().minZ()));
        assertEquals(Float.toHexString(0.9995f), Float.toHexString(blockAttributes.voxelShape().unionAABB().maxX()));
        assertEquals(Float.toHexString(0.1825f), Float.toHexString(blockAttributes.voxelShape().unionAABB().maxY()));
        assertEquals(Float.toHexString(0.9995f), Float.toHexString(blockAttributes.voxelShape().unionAABB().maxZ()));
        assertEquals(0, blockAttributes.burnAbility());
        assertEquals(0, blockAttributes.burnChance());
        assertTrue(blockAttributes.canContainLiquid());
        assertEquals(new Color(51, 76, 178, 255), blockAttributes.color());
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
        assertFalse(blockAttributes.isUnbreakable());
        assertTrue(blockAttributes.isWaterBlocking());
        assertTrue(blockAttributes.isLavaFlammable());
        assertEquals(0, blockAttributes.lightDampening());
        assertEquals(0, blockAttributes.lightEmission());
        assertFalse(blockAttributes.pushesUpFallingBlocks());
        assertFalse(blockAttributes.superHot());
        assertEquals(0, blockAttributes.thickness());
        assertEquals(0, blockAttributes.translucency());
        assertTrue(blockAttributes.isAir());
        assertTrue(blockAttributes.isBounceBlock());
        assertTrue(blockAttributes.isButtonBlock());
        assertTrue(blockAttributes.isCropBlock());
        assertTrue(blockAttributes.isDoorBlock());
        assertTrue(blockAttributes.isFallingBlock());
        assertTrue(blockAttributes.isFenceBlock());
        assertTrue(blockAttributes.isFenceGateBlock());
        assertTrue(blockAttributes.isSlabBlock());
        assertTrue(blockAttributes.isStemBlock());
        assertTrue(blockAttributes.isThinFenceBlock());
    }

    @Test
    void testVanillaBlockAttributes() {
        int error = 0;
        for (var vanillaBlockId : VanillaBlockId.values()) {
            BlockType<?> type = BlockTypeRegistry.getRegistry().get(vanillaBlockId.getIdentifier());
            assertNotNull(type);
            var attributeMap = VanillaBlockAttributeRegistry.getRegistry().get(vanillaBlockId);
            assertNotNull(attributeMap);
            for (var state : type.getBlockStateHashMap().values()) {
                var expected = attributeMap.get(state.blockStateHash());
                if (expected == null) {
                    log.error("Missing block attributes for state: " + state + ", Block: " + type.getIdentifier());
                    log.info("expected blockhash: " + state.blockStateHash());
                    error++;
                }
            }
        }
        assertEquals(0, error, "error number not zero!");
    }
}