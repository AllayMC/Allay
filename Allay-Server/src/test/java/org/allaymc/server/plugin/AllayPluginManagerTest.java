package org.allaymc.server.plugin;

import org.allaymc.api.datastruct.dag.HashDirectedAcyclicGraph;
import org.allaymc.api.plugin.PluginDependency;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Allay Project 2024/2/8
 *
 * @author daoge_cmd
 */
public class AllayPluginManagerTest extends AllayPluginManager {
    static DescriptorMapBuilder builder() {
        return new DescriptorMapBuilder();
    }

    @BeforeEach
    void cleanUp() {
        dag = new HashDirectedAcyclicGraph<>();
    }

    @Test
    void testCheckCircularDependenciesV1() {
        var descriptors = builder()
                .add("a")
                .dependOn("a")
                .end()
                .build();
        checkCircularDependencies(descriptors);
        assertFalse(this.pluginsSortedList.contains("a"));
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
        checkCircularDependencies(descriptors);
        assertFalse(this.pluginsSortedList.contains("c"));
        assertTrue(this.pluginsSortedList.contains("a"));
        assertTrue(this.pluginsSortedList.contains("b"));
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
        checkCircularDependencies(descriptors);
        assertFalse(this.pluginsSortedList.contains("c"));
        assertTrue(this.pluginsSortedList.contains("a"));
        assertTrue(this.pluginsSortedList.contains("b"));
    }

    @Test
    void testCheckCircularDependenciesV4() {
        var descriptors = builder()
                .add("a")
                .softDependOn("a")
                .end()
                .build();
        checkCircularDependencies(descriptors);
        assertFalse(this.pluginsSortedList.contains("a"));
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
        checkCircularDependencies(descriptors);
        assertTrue(this.pluginsSortedList.contains("a"));
        assertTrue(this.pluginsSortedList.contains("b"));
        assertTrue(this.pluginsSortedList.contains("c"));
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
