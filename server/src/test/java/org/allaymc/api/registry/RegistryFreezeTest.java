package org.allaymc.api.registry;

import com.google.common.collect.HashBiMap;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;

import static org.junit.jupiter.api.Assertions.*;

class RegistryFreezeTest {

    @Test
    void legacyCustomRegistryRemainsMutableWithDefaultFreezeMethods() {
        var content = new AtomicReference<List<String>>(new ArrayList<>());
        Registry<List<String>> registry = new Registry<>() {
            @Override
            public void register(Consumer<List<String>> consumer) {
                consumer.accept(content.get());
            }

            @Override
            public List<String> getContent() {
                return content.get();
            }

            @Override
            public void setContent(List<String> replacement) {
                content.set(replacement);
            }
        };

        registry.freeze();
        registry.register(values -> values.add("still mutable"));

        assertFalse(registry.isFrozen());
        assertEquals(List.of("still mutable"), registry.getContent());
    }

    @Test
    void mappedRegistryPublishesAnUnmodifiableSnapshot() {
        var registry = SimpleMappedRegistry.<Void, String, Integer>create(ignored -> new HashMap<>());
        registry.register("before", 1);
        var mutableContent = registry.getContent();

        registry.freeze();
        mutableContent.put("detached", 2);

        assertTrue(registry.isFrozen());
        assertEquals(1, registry.get("before"));
        assertThrows(UnsupportedOperationException.class, () -> registry.getContent().put("direct", 3));
        assertThrows(IllegalStateException.class, () -> registry.register("registered", 4));
        assertThrows(IllegalStateException.class, () -> registry.register(content -> content.put("consumer", 5)));
        assertThrows(IllegalStateException.class, () -> registry.setContent(new HashMap<>()));
    }

    @Test
    void doubleKeyRegistryFreezesBothIndexes() {
        var reference = new AtomicReference<DoubleKeyMappedRegistry<Integer, String, String>>();
        DoubleKeyMappedRegistry.<Void, Integer, String, String>create(
                ignored -> new DoubleKeyMappedRegistry.MapPair<>(new HashMap<>(), new HashMap<>()),
                reference::set,
                () -> {
                }
        );
        var registry = reference.get();
        registry.register(1, "one", "value");

        registry.freeze();

        assertEquals("value", registry.getByK1(1));
        assertEquals("value", registry.getByK2("one"));
        assertThrows(UnsupportedOperationException.class, () -> registry.getContent().m1().put(2, "other"));
        assertThrows(UnsupportedOperationException.class, () -> registry.getContent().m2().put("two", "other"));
        assertThrows(IllegalStateException.class, () -> registry.register(2, "two", "other"));
    }

    @Test
    void biMappedRegistryFreezesBothDirections() {
        var registry = new TestBiMappedRegistry();
        registry.register("one", 1);

        registry.freeze();

        assertEquals(1, registry.getByLeft("one"));
        assertEquals("one", registry.getByRight(1));
        assertThrows(UnsupportedOperationException.class, () -> registry.getContent().put("two", 2));
        assertThrows(UnsupportedOperationException.class, () -> registry.getContent().inverse().put(2, "two"));
        assertThrows(IllegalStateException.class, () -> registry.register("two", 2));
    }

    private static final class TestBiMappedRegistry extends BiMappedRegistry<String, Integer> {
        private TestBiMappedRegistry() {
            super(null, ignored -> HashBiMap.create());
        }
    }
}
