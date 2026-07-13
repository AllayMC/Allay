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
 * Allay Project 2026/7/12
 *
 * @author Miroshka000
 */
@ExtendWith(AllayTestExtension.class)
class GoatHornItemTypeTest {

    @Test
    void shouldCreateEveryInstrumentVariant() {
        var horn = assertInstanceOf(ItemGoatHornStack.class, ItemTypes.GOAT_HORN.createItemStack(1));

        for (var instrument : GoatHornInstrument.values()) {
            horn.setInstrument(instrument);
            assertEquals(instrument, horn.getInstrument());
            assertEquals(instrument.getMeta(), horn.getMeta());
        }
    }

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
