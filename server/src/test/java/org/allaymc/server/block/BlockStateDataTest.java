package org.allaymc.server.block;

import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.block.component.data.BlockStateData;
import org.allaymc.api.block.data.BlockId;
import org.allaymc.api.block.type.BlockType;
import org.allaymc.api.registry.Registries;
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
                    "canContainLiquid": false,
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
                    "lightDampening": 15,
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
        var unionAABB = blockAttributes.collisionShape().unionAABB();
        assertEquals(0.0005f, unionAABB.minX());
        assertEquals(0.0005f, unionAABB.minY());
        assertEquals(0.0005f, unionAABB.minZ());
        assertEquals(0.9995f, unionAABB.maxX());
        assertEquals(0.1825f, unionAABB.maxY());
        assertEquals(0.9995f, unionAABB.maxZ());
        assertEquals(1.25f, blockAttributes.hardness());
        assertEquals(1.25f, blockAttributes.explosionResistance());
        assertEquals(0, blockAttributes.flameOdds());
        assertEquals(0.6f, blockAttributes.friction());
        assertEquals(15, blockAttributes.lightDampening());
        assertEquals(0, blockAttributes.lightEmission());
        assertEquals(Color.decode("#575c5c"), blockAttributes.mapColor());
        assertEquals(0, Float.compare(0.0f, blockAttributes.thickness()));
    }

    @Test
    void testVanillaBlockStateData() {
        int error = 0;
        for (var vanillaBlockId : BlockId.values()) {
            BlockType<?> type = Registries.BLOCKS.get(vanillaBlockId.getIdentifier());
            assertNotNull(type);
            var blockStateDataMap = Registries.BLOCK_STATE_DATA.get(vanillaBlockId);
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
