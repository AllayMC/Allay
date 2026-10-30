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
 * {@code AllayPluginManagerTest} exercises dependency ordering and version
 * checks through manager helper methods.
 *
 * @author daoge_cmd
 */
@ExtendWith(AllayTestExtension.class)
public class AllayPluginManagerTest extends AllayPluginManager {
    /**
     * Creates a descriptor-map builder for one dependency graph scenario.
     */
    static DescriptorMapBuilder builder() {
        return new DescriptorMapBuilder();
    }

    /**
     * Builds a plugin with a required dependency on itself. The cycle removes
     * that plugin from the calculated order instead of retaining a self-edge.
     */
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

    /**
     * Builds a three-plugin cycle through required dependencies. This fixture's
     * traversal removes {@code "c"} while keeping {@code "a"} and {@code "b"}
     * in the resulting vertex list.
     */
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

    /**
     * Closes a three-plugin dependency cycle with an optional edge. Optionality
     * does not bypass graph cycle rejection, so {@code "c"} is excluded in this
     * fixture.
     */
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

    /**
     * Builds a plugin with an optional dependency on itself. The optional edge
     * still forms a cycle and removes that plugin from the calculated order.
     */
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

    /**
     * Orders a required dependency whose own optional dependency has no descriptor.
     * The resulting graph retains the missing dependency's placeholder name
     * alongside both described plugins.
     */
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

    /**
     * Checks installed versions against absent, blank, exact, and ecosystem-style
     * version ranges. The selected cases protect accepted alternatives and
     * rejections without requiring plugins to be instantiated.
     */
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

    /**
     * Adapts version strings into descriptor and dependency records for comparison.
     * Both records use the same fixture name, while a {@code null} range
     * exercises the manager's unversioned-dependency path.
     */
    boolean isUnexpectedDependencyVersion(String version, String range) {
        var name = "Test";
        return isUnexpectedDependencyVersion(
                new FakePluginDescriptor(name, version),
                new PluginDependency(name, range, false)
        );
    }

    /**
     * {@code DescriptorMapBuilder} collects named fixture descriptors for one
     * manager ordering calculation.
     */
    static class DescriptorMapBuilder {

        /**
         * {@code descriptors} holds the mutable fixture map. {@code build} returns
         * that same map without copying it, exposing later completed descriptors.
         */
        Map<String, FakePluginDescriptor> descriptors = new HashMap<>();

        /**
         * Starts a named descriptor without publishing it to the map yet.
         */
        DescriptorBuilder add(String name) {
            return new DescriptorBuilder(name);
        }

        /**
         * Returns the same mutable descriptor map accumulated by completed builders.
         * Later completed descriptors remain visible through this reference,
         * allowing callers to inspect the fixture exactly as it was assembled.
         */
        Map<String, FakePluginDescriptor> build() {
            return descriptors;
        }

        /**
         * {@code DescriptorBuilder} accumulates dependencies before publishing
         * one descriptor to its enclosing map.
         */
        private class DescriptorBuilder {
            /** Descriptor lookup name. */
            String name;
            /**
             * {@code dependencies} holds declarations shared with the descriptor
             * when {@code end} publishes this builder, so later additions remain
             * visible through that descriptor.
             */
            List<PluginDependency> dependencies = new ArrayList<>();

            /**
             * Creates a pending descriptor with the supplied fixture name.
             */
            public DescriptorBuilder(String name) {
                this.name = name;
            }

            /**
             * Adds an unconstrained required dependency and returns this same builder.
             */
            DescriptorBuilder dependOn(String name) {
                dependencies.add(new PluginDependency(name, null, false));
                return this;
            }

            /**
             * Adds an unconstrained optional dependency and returns this same builder.
             */
            DescriptorBuilder softDependOn(String name) {
                dependencies.add(new PluginDependency(name, null, true));
                return this;
            }

            /**
             * Publishes a version-one descriptor and resumes the enclosing map builder.
             * An existing descriptor of the same name is replaced, and the
             * descriptor retains this builder's dependency list without copying.
             */
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
