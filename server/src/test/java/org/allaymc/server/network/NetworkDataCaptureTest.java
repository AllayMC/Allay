package org.allaymc.server.network;

import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.registry.Registries;
import org.allaymc.testutils.AllayTestExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

@ExtendWith(AllayTestExtension.class)
class NetworkDataCaptureTest {

    @Test
    void captureFreezesBlockStatePalette() {
        assertTrue(Registries.BLOCK_STATE_PALETTE.isFrozen());
        assertThrows(
                IllegalStateException.class,
                () -> Registries.BLOCK_STATE_PALETTE.register(Integer.MIN_VALUE, mock(BlockState.class))
        );
    }
}
