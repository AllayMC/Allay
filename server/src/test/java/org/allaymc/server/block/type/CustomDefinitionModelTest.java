package org.allaymc.server.block.type;

import org.allaymc.server.item.type.CustomItemDefinition;
import org.cloudburstmc.nbt.NbtMap;
import org.joml.Vector3f;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CustomDefinitionModelTest {
    @Test
    void blockDefinitionModelsDefensivelyCopyBuilderData() {
        var rawComponents = new LinkedHashMap<String, NbtMap>();
        rawComponents.put("test:first", NbtMap.EMPTY);
        var materialsBuilder = CustomBlockStateDefinition.Materials.builder().any("first_texture");
        var stateDefinition = CustomBlockStateDefinition.builder()
                .materials(materialsBuilder.build())
                .rawComponents(rawComponents)
                .build();

        rawComponents.put("test:second", NbtMap.EMPTY);
        materialsBuilder.any("second_texture");

        assertEquals(1, stateDefinition.rawComponents().size());
        assertEquals("first_texture",
                stateDefinition.materials().materials().get(CustomBlockStateDefinition.Materials.ANY_FACE).texture());
        assertThrows(UnsupportedOperationException.class,
                () -> stateDefinition.rawComponents().put("test:third", NbtMap.EMPTY));
        assertThrows(UnsupportedOperationException.class,
                () -> stateDefinition.materials().materials().clear());

        var states = new LinkedHashMap<Integer, CustomBlockStateDefinition>();
        states.put(1, stateDefinition);
        var definition = new CustomBlockDefinition(90, states, rawComponents);
        states.clear();
        assertEquals(1, definition.stateDefinitions().size());
        assertThrows(IllegalArgumentException.class,
                () -> new CustomBlockDefinition(45, Map.of(), Map.of()));
    }

    @Test
    void customItemDefinitionHasUsefulDefaultsAndCopiesVectors() {
        var source = new Vector3f(1, 2, 3);
        var offset = CustomItemDefinition.RenderOffsets.Offset.builder().position(source).build();
        var definition = CustomItemDefinition.builder()
                .texture("test_item")
                .renderOffsets(CustomItemDefinition.RenderOffsets.builder()
                        .mainHand(CustomItemDefinition.RenderOffsets.Hand.builder()
                                .firstPerson(offset)
                                .build())
                        .build())
                .rawComponents(new LinkedHashMap<>(Map.of("test:component", NbtMap.EMPTY)))
                .build();

        source.set(9, 9, 9);
        ((Vector3f) offset.position()).set(8, 8, 8);

        assertTrue(definition.canDestroyInCreative());
        assertEquals(1f, offset.position().x());
        assertEquals(2f, offset.position().y());
        assertEquals(3f, offset.position().z());
        assertThrows(UnsupportedOperationException.class, () -> definition.rawComponents().clear());
    }

    @Test
    void builtInModelsDoNotExposeSerializationMethods() {
        assertFalse(hasSerializationMethod(CustomBlockDefinition.class));
        assertFalse(hasSerializationMethod(CustomBlockStateDefinition.class));
        assertFalse(hasSerializationMethod(CustomItemDefinition.class));
    }

    private static boolean hasSerializationMethod(Class<?> type) {
        for (var method : type.getDeclaredMethods()) {
            if (method.getName().equalsIgnoreCase("toNbt")) {
                return true;
            }
        }
        return false;
    }
}
