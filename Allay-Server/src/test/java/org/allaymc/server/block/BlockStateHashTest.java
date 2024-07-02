package org.allaymc.server.block;

import com.google.gson.JsonParser;
import org.allaymc.api.block.palette.BlockStateHashPalette;
import org.allaymc.server.block.registry.AllayVanillaBlockStateDataRegistry;
import org.allaymc.testutils.AllayTestExtension;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

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
        try (var reader = new InputStreamReader(
                Objects.requireNonNull(
                        AllayVanillaBlockStateDataRegistry.class
                                .getClassLoader()
                                .getResourceAsStream("block_states.json"),
                        "block_states.json is missing!"))
        ) {
            JsonParser.parseReader(reader).getAsJsonArray().forEach(entry -> BLOCK_STATE_DATA.put(entry.getAsJsonObject().get("blockStateHash").getAsInt(), entry));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void testAllHashes() {
        for (var expectedHash : BLOCK_STATE_DATA.keySet()) {
            assertNotNull(BlockStateHashPalette.getRegistry().get(expectedHash));
        }
        for (var computedHash : BlockStateHashPalette.getRegistry().getContent().keySet()) {
            assertNotNull(BLOCK_STATE_DATA.get(computedHash));
        }
    }
}
