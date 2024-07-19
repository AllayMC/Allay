package org.allaymc.server.block;

import com.google.gson.JsonParser;
import org.allaymc.api.registry.Registries;
import org.allaymc.api.utils.Utils;
import org.allaymc.testutils.AllayTestExtension;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * This test must be executed before the AllayBlockTypeTest test,
 * otherwise the generated TestBlock will cause error.
 * Allay Project 2023/9/30
 *
 * @author daoge_cmd
 */
@ExtendWith(AllayTestExtension.class)
class BlockStateHashTest {
    static Map<Integer, Object> BLOCK_STATE_DATA = new HashMap<>();

    @BeforeAll
    static void load() {
        try (var reader = new InputStreamReader(Utils.getResource("block_states.json"))) {
            JsonParser.parseReader(reader).getAsJsonArray().forEach(entry -> BLOCK_STATE_DATA.put(entry.getAsJsonObject().get("blockStateHash").getAsInt(), entry));
        } catch (IOException e) {
            throw new RuntimeException(e);
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
}
