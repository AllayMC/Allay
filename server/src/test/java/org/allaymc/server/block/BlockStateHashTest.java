package org.allaymc.server.block;

import com.google.gson.JsonParser;
import lombok.SneakyThrows;
import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.registry.Registries;
import org.allaymc.api.utils.Utils;
import org.allaymc.api.utils.identifier.Identifier;
import org.allaymc.testutils.AllayTestExtension;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/**
 * {@code BlockStateHashTest} checks registry hashes against the bundled vanilla
 * block-state fixture and verifies repeated default-state identity. Palette
 * comparison includes every fixture hash and requires reverse coverage for states in
 * the default namespace.
 *
 * @author daoge_cmd
 */
@ExtendWith(AllayTestExtension.class)
class BlockStateHashTest {
    /**
     * {@code BLOCK_STATE_DATA} holds parsed fixture entries keyed by their expected
     * state hash. {@code load} populates it before test methods inspect palette
     * coverage.
     */
    static Map<Integer, Object> BLOCK_STATE_DATA = new HashMap<>();

    /**
     * Loads bundled block-state JSON entries into the shared hash-indexed fixture map
     * before the tests run. Resource parsing failures propagate through the
     * sneaky-throws test setup rather than leaving a substitute fixture.
     */
    @SneakyThrows
    @BeforeAll
    static void load() {
        try (var reader = new InputStreamReader(Utils.getResource("block_states.json"))) {
            JsonParser.parseReader(reader).getAsJsonArray().forEach(entry -> BLOCK_STATE_DATA.put(entry.getAsJsonObject().get("blockStateHash").getAsInt(), entry));
        }
    }

    /**
     * Compares bundled vanilla state hashes with the registered block palette in both
     * directions. Reverse coverage is restricted to the default namespace, allowing
     * independently registered custom namespace states.
     */
    @Test
    void testAllHashes() {
        for (int expectedHash : BLOCK_STATE_DATA.keySet()) {
            assertNotNull(Registries.BLOCK_STATE_PALETTE.get(expectedHash));
        }
        for (var entry : Registries.BLOCK_STATE_PALETTE.getContent().entrySet()) {
            if (Identifier.DEFAULT_NAMESPACE.equals(entry.getValue().getBlockType().getIdentifier().namespace())) {
                assertNotNull(BLOCK_STATE_DATA.get(entry.getKey()));
            }
        }
    }

    /**
     * Retrieves the blue candle default state repeatedly from the same block type.
     * Both reference identity and equality must hold, protecting reuse of that
     * registered default-state instance.
     */
    @Test
    void testBlockStateEquals() {
        assertSame(BlockTypes.BLUE_CANDLE.getDefaultState(), BlockTypes.BLUE_CANDLE.getDefaultState());
        assertEquals(BlockTypes.BLUE_CANDLE.getDefaultState(), BlockTypes.BLUE_CANDLE.getDefaultState());
    }
}
