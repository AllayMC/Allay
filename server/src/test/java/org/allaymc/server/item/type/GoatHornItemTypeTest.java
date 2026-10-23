package org.allaymc.server.item.type;

import org.allaymc.api.item.data.GoatHornInstrument;
import org.allaymc.api.item.interfaces.ItemGoatHornStack;
import org.allaymc.api.item.type.ItemTypes;
import org.allaymc.api.registry.Registries;
import org.allaymc.testutils.AllayTestExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

/**
 * {@code GoatHornItemTypeTest} verifies goat-horn capability wiring and creative variant coverage.
 *
 * @author Miroshka000
 */
@ExtendWith(AllayTestExtension.class)
class GoatHornItemTypeTest {

    /**
     * Creates a registered goat horn and assigns every supported instrument.
     * The specialized stack must report each selected instrument and store its
     * corresponding metadata value through the same item instance.
     */
    @Test
    void shouldCreateEveryInstrumentVariant() {
        var horn = assertInstanceOf(ItemGoatHornStack.class, ItemTypes.GOAT_HORN.createItemStack(1));

        for (var instrument : GoatHornInstrument.values()) {
            horn.setInstrument(instrument);
            assertEquals(instrument, horn.getInstrument());
            assertEquals(instrument.getMeta(), horn.getMeta());
        }
    }

    /**
     * Collects metadata from all goat-horn entries in the creative registry.
     * The distinct values must match every supported instrument ordinal, protecting
     * complete variant coverage in the initialized creative inventory.
     */
    @Test
    void creativeInventoryShouldContainAllEightVariants() {
        var actualMetas = Registries.CREATIVE_ITEMS.getEntries().stream()
                .map(entry -> entry.itemStack())
                .filter(item -> item.getItemType() == ItemTypes.GOAT_HORN)
                .map(item -> item.getMeta())
                .collect(Collectors.toSet());
        var expectedMetas = IntStream.range(0, GoatHornInstrument.values().length)
                .boxed()
                .collect(Collectors.toSet());

        assertEquals(expectedMetas, actualMetas);
    }
}
