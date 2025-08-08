package org.allaymc.server.block;

import com.google.gson.JsonParser;
import lombok.SneakyThrows;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.registry.Registries;
import org.allaymc.api.utils.Utils;
import org.allaymc.testutils.AllayTestExtension;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author daoge_cmd
 */
@ExtendWith(AllayTestExtension.class)
class BlockStateHashTest {
    static Map<Integer, Object> BLOCK_STATE_DATA = new HashMap<>();

    @SneakyThrows
    @BeforeAll
    static void load() {
        try (var reader = new InputStreamReader(Utils.getResource("block_states.json"))) {
            JsonParser.parseReader(reader).getAsJsonArray().forEach(entry -> BLOCK_STATE_DATA.put(entry.getAsJsonObject().get("blockStateHash").getAsInt(), entry));
        }
    }

    @Test
    void testAllHashes() {
        for (int expectedHash : BLOCK_STATE_DATA.keySet()) {
            assertNotNull(Registries.BLOCK_STATE_PALETTE.get(expectedHash));
        }
        for (var computedHash : Registries.BLOCK_STATE_PALETTE.getContent().keySet()) {
            assertNotNull(BLOCK_STATE_DATA.get(computedHash));
        }
    }

    @Test
    void testBlockStateEquals() {
        assertSame(BlockTypes.BLUE_CANDLE.getDefaultState(), BlockTypes.BLUE_CANDLE.getDefaultState());
        assertEquals(BlockTypes.BLUE_CANDLE.getDefaultState(), BlockTypes.BLUE_CANDLE.getDefaultState());
    }
}
