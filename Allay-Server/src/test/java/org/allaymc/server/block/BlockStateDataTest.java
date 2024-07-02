package org.allaymc.server.block;

import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.block.component.common.BlockStateData;
import org.allaymc.api.block.registry.BlockTypeRegistry;
import org.allaymc.api.block.registry.VanillaBlockStateDataRegistry;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.data.VanillaBlockId;
import org.allaymc.testutils.AllayTestExtension;
import org.intellij.lang.annotations.Language;
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
class BlockStateDataTest {
    @Language("JSON")
    private static final String json = """
            {
                    "burnOdds": 0,
                    "canContainLiquid": false,
                    "canDropWithAnyTool": false,
                    "collisionShape": [
                        0.0005,
                        0.0005,
                        0.0005,
                        0.9995,
                        0.1825,
                        0.9995
                    ],
                    "hardness": 1.25,
                    "explosionResistance": 1.25,
                    "flameOdds": 0,
                    "friction": 0.6,
                    "light": 15,
                    "lightEmission": 0,
                    "mapColor": "#575c5cff",
                    "thickness": 0.0
            }
            """;

    @Test
    void testDeserialization() {
        var blockAttributes = BlockStateData.fromJson(json);
        assertEquals(0, blockAttributes.burnOdds());
        assertFalse(blockAttributes.canContainLiquid());
        assertFalse(blockAttributes.canDropWithAnyTool());
//        assertEquals(Float.toHexString(0.0005f), Float.toHexString(blockAttributes.collisionShape().unionAABB().minX()));
//        assertEquals(Float.toHexString(0.0005f), Float.toHexString(blockAttributes.collisionShape().unionAABB().minY()));
//        assertEquals(Float.toHexString(0.0005f), Float.toHexString(blockAttributes.collisionShape().unionAABB().minZ()));
//        assertEquals(Float.toHexString(0.9995f), Float.toHexString(blockAttributes.collisionShape().unionAABB().maxX()));
//        assertEquals(Float.toHexString(0.1825f), Float.toHexString(blockAttributes.collisionShape().unionAABB().maxY()));
//        assertEquals(Float.toHexString(0.9995f), Float.toHexString(blockAttributes.collisionShape().unionAABB().maxZ()));
        assertEquals(0.0005f, blockAttributes.collisionShape().unionAABB().minX());
        assertEquals(0.0005f, blockAttributes.collisionShape().unionAABB().minY());
        assertEquals(0.0005f, blockAttributes.collisionShape().unionAABB().minZ());
        assertEquals(0.9995f, blockAttributes.collisionShape().unionAABB().maxX());
        assertEquals(0.1825f, blockAttributes.collisionShape().unionAABB().maxY());
        assertEquals(0.9995f, blockAttributes.collisionShape().unionAABB().maxZ());
        assertEquals(1.25f, blockAttributes.hardness());
        assertEquals(1.25f, blockAttributes.explosionResistance());
        assertEquals(0, blockAttributes.flameOdds());
        assertEquals(0.6f, blockAttributes.friction());
        assertEquals(15, blockAttributes.light());
        assertEquals(0, blockAttributes.lightEmission());
        assertEquals(Color.decode("#575c5c"), blockAttributes.mapColor());
        assertEquals(0, Float.compare(0.0f, blockAttributes.thickness()));
    }

    @Test
    void testVanillaBlockStateData() {
        int error = 0;
        for (var vanillaBlockId : VanillaBlockId.values()) {
            BlockType<?> type = BlockTypeRegistry.getRegistry().get(vanillaBlockId.getIdentifier());
            assertNotNull(type);
            var blockStateDataMap = VanillaBlockStateDataRegistry.getRegistry().get(vanillaBlockId);
            assertNotNull(blockStateDataMap);
            for (var state : type.getBlockStateHashMap().values()) {
                var expected = blockStateDataMap.get(state.blockStateHash());
                if (expected == null) {
                    log.error("Missing block state data for state: {}, Block: {}", state, type.getIdentifier());
                    log.info("expected blockhash: {}", state.blockStateHash());
                    error++;
                }
            }
        }
        assertEquals(0, error, "error number not zero!");
    }
}