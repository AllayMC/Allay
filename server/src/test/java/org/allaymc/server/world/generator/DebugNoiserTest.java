package org.allaymc.server.world.generator;

import org.allaymc.api.block.type.BlockTypes;
import org.allaymc.api.registry.Registries;
import org.allaymc.server.network.protocol.NetworkBlockPalette;
import org.allaymc.testutils.AllayTestExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.Arrays;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(AllayTestExtension.class)
class DebugNoiserTest {

    @Test
    void everyBlockStateIsLaidOutOnceWithTheNewestBlocksFirst() {
        var noiser = new DebugNoiser();
        noiser.init("");

        int expected = Registries.BLOCKS.getContent().values().stream()
                .filter(blockType -> blockType != BlockTypes.AIR)
                .mapToInt(blockType -> blockType.getAllStates().size())
                .sum();
        assertEquals(expected, noiser.states.length);
        assertEquals(expected, new HashSet<>(Arrays.asList(noiser.states)).size());

        // The first states belong to blocks 1.26.40 lacks, the ones added in 1.26.50.
        var v2168 = NetworkBlockPalette.of("2168");
        assertFalse(v2168.knows(noiser.states[0].getBlockType()));
        int firstKnown = 0;
        while (!v2168.knows(noiser.states[firstKnown].getBlockType())) {
            firstKnown++;
        }
        int newStates = firstKnown;
        assertTrue(Arrays.stream(noiser.states).skip(newStates).allMatch(state -> v2168.knows(state.getBlockType())));
        assertTrue(newStates > 0);
        assertTrue(noiser.columns * noiser.rows >= noiser.states.length);
    }
}
