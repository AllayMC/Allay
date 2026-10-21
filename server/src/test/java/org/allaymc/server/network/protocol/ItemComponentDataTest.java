package org.allaymc.server.network.protocol;

import org.allaymc.testutils.AllayTestExtension;
import org.cloudburstmc.protocol.bedrock.data.inventory.ItemVersion;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.function.Function;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(AllayTestExtension.class)
class ItemComponentDataTest {

    @ParameterizedTest
    @ValueSource(ints = {2168, 2169, 2193})
    void itemRegistryCarriesComponentsOnlyForVersionedItemsLikeBds(int version) {
        var definitions = ProtocolRegistry.getDefault().resolve(ClientVariant.INTERNATIONAL, version).getData().itemDefinitions().stream()
                .collect(Collectors.toMap(definition -> definition.identifier(), Function.identity()));

        // Recorded from BDS 1.26.52: items of version NONE, block items included, have an empty component tag.
        var stone = definitions.get("minecraft:stone");
        assertEquals(ItemVersion.NONE, stone.version());
        assertTrue(stone.componentData().isEmpty());
        // Legacy and data-driven items keep theirs.
        var cookedCod = definitions.get("minecraft:cooked_cod");
        assertEquals(ItemVersion.LEGACY, cookedCod.version());
        assertFalse(cookedCod.componentData().isEmpty());
    }
}
