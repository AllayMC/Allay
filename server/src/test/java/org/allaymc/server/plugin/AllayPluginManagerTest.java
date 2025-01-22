package org.allaymc.server.plugin;

import org.allaymc.api.plugin.PluginDependency;
import org.allaymc.server.datastruct.dag.HashDirectedAcyclicGraph;
import org.allaymc.testutils.AllayTestExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author daoge_cmd
 */
@ExtendWith(AllayTestExtension.class)
public class AllayPluginManagerTest extends AllayPluginManager {
    static DescriptorMapBuilder builder() {
        return new DescriptorMapBuilder();
    }

    @Test
    void testCalculateLoadingOrderV1() {
        var descriptors = builder()
                .add("a")
                .dependOn("a")
                .end()
                .build();
        calculateLoadingOrder(new HashDirectedAcyclicGraph<>(), descriptors);
        assertFalse(this.sortedPluginList.contains("a"));
    }

    @Test
    void testCalculateLoadingOrderV2() {
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
        calculateLoadingOrder(new HashDirectedAcyclicGraph<>(), descriptors);
        assertFalse(this.sortedPluginList.contains("c"));
        assertTrue(this.sortedPluginList.contains("a"));
        assertTrue(this.sortedPluginList.contains("b"));
    }

    @Test
    void testCalculateLoadingOrderV3() {
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
        calculateLoadingOrder(new HashDirectedAcyclicGraph<>(), descriptors);
        assertFalse(this.sortedPluginList.contains("c"));
        assertTrue(this.sortedPluginList.contains("a"));
        assertTrue(this.sortedPluginList.contains("b"));
    }

    @Test
    void testCalculateLoadingOrderV4() {
        var descriptors = builder()
                .add("a")
                .softDependOn("a")
                .end()
                .build();
        calculateLoadingOrder(new HashDirectedAcyclicGraph<>(), descriptors);
        assertFalse(this.sortedPluginList.contains("a"));
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
        calculateLoadingOrder(new HashDirectedAcyclicGraph<>(), descriptors);
        assertTrue(this.sortedPluginList.contains("a"));
        assertTrue(this.sortedPluginList.contains("b"));
        assertTrue(this.sortedPluginList.contains("c"));
    }

    @Test
    void testUnexpectedDependencyVersion() {
        assertFalse(isUnexpectedDependencyVersion(
                "1.0.0", null));
        assertFalse(isUnexpectedDependencyVersion(
                "1.0.0", " "));

        assertFalse(isUnexpectedDependencyVersion(
                "1.2.3", "1.2.3"));
        assertTrue(isUnexpectedDependencyVersion(
                "1.2.3", "1.2.2"));
        assertTrue(isUnexpectedDependencyVersion(
                "1.2.3", "1.2.4"));

        // NPM
        assertFalse(isUnexpectedDependencyVersion(
                "1.2.3", ">1.2.2"));
        assertFalse(isUnexpectedDependencyVersion(
                "1.2.3", "1.1.1 || 1.2.3 - 2.0.0"));
        assertTrue(isUnexpectedDependencyVersion(
                "1.2.3", "1.1.*"));
        assertFalse(isUnexpectedDependencyVersion(
                "1.2.3", "~1.2.1"));
        assertFalse(isUnexpectedDependencyVersion(
                "1.2.3", "^1.1.1"));

        // COCOAPODS
        assertFalse(isUnexpectedDependencyVersion(
                "1.2.3", "> 1.2.2"));
        assertFalse(isUnexpectedDependencyVersion(
                "1.2.3", "~> 1.2.1"));
        assertTrue(isUnexpectedDependencyVersion(
                "1.2.3", "<= 1.1.1"));

        // IVY
        assertFalse(isUnexpectedDependencyVersion(
                "1.2.3", "1.2.+"));
        assertFalse(isUnexpectedDependencyVersion(
                "1.2.3", "(,1.8.9]"));
        assertFalse(isUnexpectedDependencyVersion(
                "1.2.3", "[0.2,1.4]"));
    }

    boolean isUnexpectedDependencyVersion(String version, String range) {
        var name = "Test";
        return isUnexpectedDependencyVersion(
                new FakePluginDescriptor(name, version),
                new PluginDependency(name, range, false)
        );
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
                dependencies.add(new PluginDependency(name, null, false));
                return this;
            }

            DescriptorBuilder softDependOn(String name) {
                dependencies.add(new PluginDependency(name, null, true));
                return this;
            }

            DescriptorMapBuilder end() {
                descriptors.put(name, new FakePluginDescriptor(
                        name,
                        "1.0.0",
                        dependencies
                ));
                return DescriptorMapBuilder.this;
            }
        }
    }
}
