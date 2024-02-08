package org.allaymc.server.plugin;

import org.allaymc.api.datastruct.dag.DAG;
import org.allaymc.api.plugin.PluginDependency;
import org.allaymc.api.plugin.PluginException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Allay Project 2024/2/8
 *
 * @author daoge_cmd
 */
public class AllayPluginManagerTest extends AllayPluginManager {

    @BeforeEach
    void cleanUp() {
        dag = new DAG<>();
    }

    @Test
    void testCheckCircularDependenciesV1() {
        var descriptors = builder()
                .add("a")
                .dependOn("a")
                .end()
                .build();
        assertThrows(PluginException.class, () -> checkCircularDependencies(descriptors));
    }

    @Test
    void testCheckCircularDependenciesV2() {
        var descriptors = builder()
                .add("a")
                .dependOn("b")
                .end()
                .add("b")
                .dependOn("c")
                .end()
                .add("c")
                .dependOn("a")
                .end()
                .build();
        assertThrows(PluginException.class, () -> checkCircularDependencies(descriptors));
    }

    @Test
    void testCheckCircularDependenciesV3() {
        var descriptors = builder()
                .add("a")
                .dependOn("b")
                .end()
                .add("b")
                .dependOn("c")
                .end()
                .add("c")
                .softDependOn("a")
                .end()
                .build();
        assertThrows(PluginException.class, () -> checkCircularDependencies(descriptors));
    }

    @Test
    void testCheckCircularDependenciesV4() {
        var descriptors = builder()
                .add("a")
                .softDependOn("a")
                .end()
                .build();
        assertThrows(PluginException.class, () -> checkCircularDependencies(descriptors));
    }

    @Test
    void testSoftDependency() {
        var descriptors = builder()
                .add("a")
                .dependOn("b")
                .end()
                .add("b")
                .softDependOn("c")
                .end()
                .build();
        assertDoesNotThrow(() -> checkCircularDependencies(descriptors));
    }

    static DescriptorMapBuilder builder() {
        return new DescriptorMapBuilder();
    }

    static class DescriptorMapBuilder {

        Map<String, FakePluginDescriptor> descriptors = new HashMap<>();

        DescriptorBuilder add(String name) {
            return new DescriptorBuilder(name);
        }

        Map<String, FakePluginDescriptor> build() {
            return descriptors;
        }

        private class DescriptorBuilder {
            String name;
            List<PluginDependency> dependencies = new ArrayList<>();

            public DescriptorBuilder(String name) {
                this.name = name;
            }

            DescriptorBuilder dependOn(String name) {
                dependencies.add(new PluginDependency(name, false));
                return this;
            }

            DescriptorBuilder softDependOn(String name) {
                dependencies.add(new PluginDependency(name, true));
                return this;
            }

            DescriptorMapBuilder end() {
                descriptors.put(name, new FakePluginDescriptor(
                        name,
                        dependencies
                ));
                return DescriptorMapBuilder.this;
            }
        }
    }
}
