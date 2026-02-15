package org.allaymc.server.block;

import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.registry.Registries;
import org.allaymc.server.block.data.BlockId;
import org.allaymc.server.registry.InternalRegistries;
import org.allaymc.server.registry.loader.BlockStateDataRegistryLoader;
import org.allaymc.testutils.AllayTestExtension;
import org.intellij.lang.annotations.Language;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Cool_Loong
 */
@ExtendWith(AllayTestExtension.class)
@Slf4j
class BlockStateDataTest {
    @Language("JSON")
    private static final String json = """
            {
                    "burnOdds": 0,
                    "canContainLiquidSource": false,
                    "collisionShape": [
                        [
                            0.0005,
                            0.0005,
                            0.0005,
                            0.9995,
                            0.1825,
                            0.9995
                        ]
                    ],
                    "hardness": 1.25,
                    "explosionResistance": 1.25,
                    "flameOdds": 0,
                    "friction": 0.6,
                    "lightDampening": 15,
                    "lightEmission": 0,
                    "mapColor": "#575c5cff",
                    "thickness": 0.0,
                    "requiresCorrectToolForDrops": true,
                    "isSolid": true,
                    "translucency": 0.5,
                    "translationKey": "abc"
            }
            """;

    @Test
    void testDeserialization() {
        var blockStateData = BlockStateDataRegistryLoader.fromJson(json);
        assertEquals(0, blockStateData.burnOdds());
        assertFalse(blockStateData.canContainLiquidSource());
        var unionAABB = blockStateData.collisionShape().unionAABB();
        assertEquals(0.0005, unionAABB.minX());
        assertEquals(0.0005, unionAABB.minY());
        assertEquals(0.0005, unionAABB.minZ());
        assertEquals(0.9995, unionAABB.maxX());
        assertEquals(0.1825, unionAABB.maxY());
        assertEquals(0.9995, unionAABB.maxZ());
        assertEquals(1.25f, blockStateData.hardness());
        assertEquals(1.25f, blockStateData.explosionResistance());
        assertEquals(0, blockStateData.flameOdds());
        assertEquals(0.6f, blockStateData.friction());
        assertEquals(15, blockStateData.lightDampening());
        assertEquals(0, blockStateData.lightEmission());
        assertEquals(Color.decode("#575c5c"), blockStateData.mapColor());
        assertEquals(0, Float.compare(0.0f, blockStateData.thickness()));
        assertTrue(blockStateData.requiresCorrectToolForDrops());
        assertTrue(blockStateData.isSolid());
        assertEquals(0.5f, blockStateData.translucency());
        assertEquals("abc", blockStateData.translationKey());
    }

    @Test
    void testVanillaBlockStateData() {
        int error = 0;
        for (var vanillaBlockId : BlockId.values()) {
            BlockType<?> type = Registries.BLOCKS.get(vanillaBlockId.getIdentifier());
            assertNotNull(type);
            var blockStateDataMap = InternalRegistries.BLOCK_STATE_DATA.get(vanillaBlockId);
            assertNotNull(blockStateDataMap);
            for (var state : type.getBlockStateHashMap().values()) {
                var expected = blockStateDataMap.get(state.blockStateHash());
                if (expected == null) {
                    log.error("Missing block state data for state: {}, Block: {}", state, type.getIdentifier());
                    log.info("Expected block state hash: {}", state.blockStateHash());
                    error++;
                }
            }
        }
        assertEquals(0, error, "error number not zero!");
    }
}
