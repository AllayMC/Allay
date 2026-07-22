package org.allaymc.server.network;

import org.allaymc.api.block.type.BlockState;
import org.allaymc.api.registry.Registries;
import org.allaymc.server.network.protocol.ClientVariant;
import org.allaymc.server.network.protocol.ProtocolRegistry;
import org.allaymc.server.registry.InternalRegistries;
import org.allaymc.testutils.AllayTestExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

@ExtendWith(AllayTestExtension.class)
class ProtocolRegistryInitializationTest {

    @Test
    void defaultProtocolInitializationFreezesSourceRegistries() {
        assertTrue(Registries.ITEMS.isFrozen());
        assertTrue(Registries.BLOCKS.isFrozen());
        assertTrue(Registries.BLOCK_STATE_PALETTE.isFrozen());
        assertTrue(Registries.RECIPES.isFrozen());
        assertTrue(Registries.ENTITIES.isFrozen());
        assertTrue(Registries.BIOMES.isFrozen());
        assertTrue(Registries.DIMENSIONS.isFrozen());
        assertTrue(Registries.PACKS.isFrozen());
        assertTrue(InternalRegistries.TRIM_PATTERNS.isFrozen());
        assertTrue(InternalRegistries.TRIM_MATERIALS.isFrozen());
        assertThrows(
                IllegalStateException.class,
                () -> Registries.BLOCK_STATE_PALETTE.register(Integer.MIN_VALUE, mock(BlockState.class))
        );
    }

    @Test
    void protocolCachesAreBuiltFromRegisteredDomainObjects() {
        var protocol = ProtocolRegistry.getDefault().resolve(ClientVariant.INTERNATIONAL, 975);
        var data = protocol.getData();

        assertEquals(Registries.ITEMS.getContent().size(), data.itemDefinitions().size());
        assertEquals(
                Registries.BLOCKS.getContent().values().stream()
                        .mapToInt(blockType -> blockType.getAllStates().size())
                        .sum(),
                data.blockDefinitions().size()
        );
        assertEquals(Registries.CREATIVE_ITEMS.getGroups().size(), data.creativeGroups().size());
        assertEquals(Registries.CREATIVE_ITEMS.getEntries().size(), data.creativeItems().size());
        assertFalse(data.recipeTable().recipesByNetworkId().isEmpty());
        data.recipeTable().recipesByNetworkId().values().forEach(recipe -> assertSame(
                recipe,
                Registries.RECIPES.get(recipe.getIdentifier())
        ));
    }
}
