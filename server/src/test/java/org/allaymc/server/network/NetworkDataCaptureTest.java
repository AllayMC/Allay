package org.allaymc.server.network;

import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.registry.Registries;
import org.allaymc.server.network.protocol.ClientVariant;
import org.allaymc.server.network.protocol.ProtocolRegistry;
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

    @Test
    void captureKeepsRegisteredDomainObjects() {
        var source = ProtocolRegistry.getDefault()
                .resolve(ClientVariant.INTERNATIONAL, 975)
                .getData()
                .source();
        var recipe = Registries.RECIPES.getContent().values().iterator().next();
        var entityType = Registries.ENTITIES.getContent().values().iterator().next();
        var biomeType = Registries.BIOMES.getContent().m1().values().iterator().next();
        var dimensionType = Registries.DIMENSIONS.getContent().m1().values().iterator().next();
        var creativeGroup = Registries.CREATIVE_ITEMS.getGroups().getFirst();
        var creativeItem = Registries.CREATIVE_ITEMS.getEntries().getFirst();

        assertTrue(source.recipes().stream().anyMatch(value -> value == recipe));
        assertTrue(source.entityTypes().stream().anyMatch(value -> value == entityType));
        assertTrue(source.biomeTypes().stream().anyMatch(value -> value == biomeType));
        assertTrue(source.dimensionTypes().stream().anyMatch(value -> value == dimensionType));
        assertTrue(source.creativeGroups().stream().anyMatch(value -> value == creativeGroup));
        assertTrue(source.creativeItems().stream().anyMatch(value -> value == creativeItem));
    }
}
