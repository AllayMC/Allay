package org.allaymc.server.network.protocol;

import org.allaymc.testutils.AllayTestExtension;
import org.cloudburstmc.protocol.bedrock.data.inventory.ItemVersion;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.function.Function;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

/**
 * {@code ItemComponentDataTest} checks item component payload selection across
 * the recent protocol versions.
 */
@ExtendWith(AllayTestExtension.class)
class ItemComponentDataTest {

    /**
     * Compares ordinary block-item and legacy-item definitions in each selected
     * protocol. Stone with {@code ItemVersion.NONE} must have an empty
     * component tag, while cooked cod with {@code ItemVersion.LEGACY} retains
     * its component data.
     */
    @ParameterizedTest
    @ValueSource(ints = {2168, 2169, 2193})
    void itemRegistryCarriesComponentsOnlyForVersionedItemsLikeBds(int version) {
        var definitions = ProtocolRegistry.getDefault().resolve(ClientVariant.INTERNATIONAL, version).getData().itemDefinitions().stream()
                .collect(Collectors.toMap(definition -> definition.identifier(), Function.identity()));

        // Stone exercises the NONE-version branch, including block items; the
        // registry must omit component contents rather than treating all known
        // items as component-bearing definitions.
        var stone = definitions.get("minecraft:stone");
        assertEquals(ItemVersion.NONE, stone.version());
        assertTrue(stone.componentData().isEmpty());
        // Cooked cod exercises the contrasting LEGACY branch; retaining its
        // components prevents the NONE-version suppression from applying to
        // every vanilla item definition.
        var cookedCod = definitions.get("minecraft:cooked_cod");
        assertEquals(ItemVersion.LEGACY, cookedCod.version());
        assertFalse(cookedCod.componentData().isEmpty());
    }
}
