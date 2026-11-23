package org.allaymc.server.component;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.allaymc.api.block.component.BlockComponent;
import org.allaymc.api.blockentity.component.BlockEntityComponent;
import org.allaymc.api.component.Component;
import org.allaymc.api.component.ComponentInitInfo;
import org.allaymc.api.entity.component.EntityComponent;
import org.allaymc.api.item.component.ItemComponent;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Queue;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * {@code ComponentProvider} supplies component instances together with the class
 * metadata and canonical contract key used during blueprint compilation. Providers
 * either create per-object component instances on each request or expose an
 * explicitly shared stateless singleton via {@link #singleton(Component)}.
 *
 * <p>
 * Contract-key resolution maps each component class to its unique top-level
 * {@link Component} subinterface beneath the domain marker interfaces
 * ({@link BlockComponent}, {@link ItemComponent}, {@link EntityComponent},
 * {@link BlockEntityComponent}, and {@link Component}). Specialized subclasses
 * such as {@code EntityPlayerBaseComponentImpl} and
 * {@code BlockSuspiciousFallableComponentImpl} therefore resolve to the same slot
 * key as their base contracts and replace default providers automatically, while
 * event-only components without a dedicated subinterface fall back to their
 * concrete class so distinct listeners do not collide.
 *
 * @param <T> component type supplied by this provider
 *
 * @author daoge_cmd
 */
public interface ComponentProvider<T extends Component> {

    /**
     * {@code ROOT_MARKERS} identifies the domain root interfaces excluded when
     * discovering a component's canonical contract key.
     */
    Set<Class<?>> ROOT_MARKERS = Set.of(
            Component.class,
            BlockComponent.class,
            ItemComponent.class,
            EntityComponent.class,
            BlockEntityComponent.class
    );

    /**
     * Creates a per-object provider that ignores initialization data and invokes
     * the supplied factory for each composed object. The declared class supplies
     * both contract-key resolution and build-time injection inspection.
     *
     * @param provider       factory returning a component for each request
     * @param componentClass declared component class inspected by the blueprint
     * @param <T>            supplied component type
     *
     * @return a component provider using the supplied factory
     */
    static <T extends Component> ComponentProvider<T> of(Supplier<T> provider, Class<?> componentClass) {
        Objects.requireNonNull(provider, "provider");
        return of($ -> provider.get(), componentClass);
    }

    /**
     * Creates a per-object provider that forwards initialization data to the
     * supplied factory. The contract key is derived from {@code componentClass}
     * using {@link #resolveComponentKey(Class)}.
     *
     * @param provider       factory accepting initialization info and returning a component
     * @param componentClass declared component class inspected by the blueprint
     * @param <T>            supplied component type
     *
     * @return a component provider using the supplied function
     */
    static <T extends Component> ComponentProvider<T> of(Function<ComponentInitInfo, T> provider, Class<?> componentClass) {
        return of(resolveComponentKey(componentClass), provider, componentClass);
    }

    /**
     * Creates a per-object provider with an explicit contract key. Callers use
     * this overload when a component implements multiple independent contracts or
     * when overriding a specific registration slot explicitly.
     *
     * @param componentKey   explicit registration slot key
     * @param provider       factory accepting initialization info and returning a component
     * @param componentClass declared component class inspected by the blueprint
     * @param <T>            supplied component type
     *
     * @return a component provider keyed by {@code componentKey}
     */
    static <T extends Component> ComponentProvider<T> of(
            Class<?> componentKey,
            Function<ComponentInitInfo, T> provider,
            Class<?> componentClass
    ) {
        Objects.requireNonNull(componentKey, "componentKey");
        Objects.requireNonNull(provider, "provider");
        Objects.requireNonNull(componentClass, "componentClass");
        return new Simple<>(componentKey, provider, componentClass, false);
    }

    /**
     * Creates a provider that shares one stateless component instance across all
     * composed objects built from the same blueprint. Blueprint compilation
     * rejects singleton components that declare instance-bound injections or
     * lifecycle callbacks so shared state cannot leak across owners.
     *
     * @param instance shared stateless component instance
     * @param <T>      supplied component type
     *
     * @return a singleton-backed component provider
     */
    static <T extends Component> ComponentProvider<T> singleton(T instance) {
        Objects.requireNonNull(instance, "instance");
        var componentClass = instance.getClass();
        return new Simple<>(resolveComponentKey(componentClass), $ -> instance, componentClass, true);
    }

    /**
     * Creates a singleton-backed provider with an explicit registration slot key.
     * Blueprint compilation enforces the same statelessness constraints as
     * {@link #singleton(Component)}.
     *
     * @param componentKey explicit registration slot key
     * @param instance     shared stateless component instance
     * @param <T>          supplied component type
     *
     * @return a singleton-backed component provider keyed by {@code componentKey}
     */
    static <T extends Component> ComponentProvider<T> singleton(Class<?> componentKey, T instance) {
        Objects.requireNonNull(componentKey, "componentKey");
        Objects.requireNonNull(instance, "instance");
        return new Simple<>(componentKey, $ -> instance, instance.getClass(), true);
    }

    /**
     * Copies providers into an insertion-ordered map indexed by each provider's
     * contract key. Duplicate keys throw {@link IllegalArgumentException} rather
     * than silently replacing earlier list entries.
     *
     * @param componentProviders providers to index in list order
     * @param <P>                common component supertype
     *
     * @return an insertion-ordered map from contract key to provider
     *
     * @throws IllegalArgumentException if duplicate contract keys appear in the list
     */
    static <P extends Component> Map<Class<?>, ComponentProvider<? extends P>> toMap(List<ComponentProvider<? extends P>> componentProviders) {
        Objects.requireNonNull(componentProviders, "componentProviders");
        Map<Class<?>, ComponentProvider<? extends P>> map = new LinkedHashMap<>(componentProviders.size());
        for (var provider : componentProviders) {
            var key = provider.getComponentKey();
            if (map.putIfAbsent(key, provider) != null) {
                throw new IllegalArgumentException("Duplicate component key: " + key.getName());
            }
        }
        return map;
    }

    /**
     * Resolves the canonical registration slot key for {@code componentClass}.
     * The traversal inspects all interfaces reachable through {@code componentClass}
     * and its superclasses, selecting top-level {@link Component} subinterfaces
     * whose own component superinterfaces are all in {@link #ROOT_MARKERS}.
     *
     * <p>
     * A single top-level contract returns that interface so specialized
     * implementations automatically replace their base contract slot. When no
     * subinterface exists beneath the root markers, the concrete class itself is
     * returned so independent event-only listeners coexist cleanly. Multiple
     * unrelated top-level contracts throw {@link IllegalArgumentException} to
     * force an explicit key choice.
     *
     * @param componentClass component class to classify
     *
     * @return the canonical slot key class
     *
     * @throws IllegalArgumentException if {@code componentClass} implements multiple independent top-level component contracts
     */
    static Class<?> resolveComponentKey(Class<?> componentClass) {
        Objects.requireNonNull(componentClass, "componentClass");
        Set<Class<?>> visited = new HashSet<>();
        Queue<Class<?>> queue = new ArrayDeque<>();
        Set<Class<?>> topContracts = new LinkedHashSet<>();

        // Walk the superclass chain and interface graph to collect every
        // component contract interface implemented by this class.
        for (var cur = componentClass; cur != null && cur != Object.class; cur = cur.getSuperclass()) {
            if (cur.isInterface() && visited.add(cur)) {
                queue.add(cur);
            }
            for (var iface : cur.getInterfaces()) {
                if (visited.add(iface)) {
                    queue.add(iface);
                }
            }
        }

        while (!queue.isEmpty()) {
            var iface = queue.poll();
            if (!Component.class.isAssignableFrom(iface)) {
                continue;
            }
            if (ROOT_MARKERS.contains(iface)) {
                continue;
            }

            boolean hasNonRootComponentParent = false;
            for (var parent : iface.getInterfaces()) {
                if (Component.class.isAssignableFrom(parent) && !ROOT_MARKERS.contains(parent)) {
                    hasNonRootComponentParent = true;
                }
                if (visited.add(parent)) {
                    queue.add(parent);
                }
            }

            if (!hasNonRootComponentParent) {
                topContracts.add(iface);
            }
        }

        if (topContracts.size() == 1) {
            return topContracts.iterator().next();
        }
        if (topContracts.isEmpty()) {
            // Event-only components directly implementing a root marker interface
            // keep their own concrete class as key so multiple listeners never collide.
            return componentClass;
        }
        throw new IllegalArgumentException(
                "Component " + componentClass.getName() + " implements multiple top-level component contracts: "
                + topContracts + ". Specify an explicit component key."
        );
    }

    /**
     * Supplies a component instance using initialization data from the composed
     * object's factory.
     *
     * @param info initialization payload for the composed object
     *
     * @return the component instance to wire into the composed object
     */
    T provide(ComponentInitInfo info);

    /**
     * Returns the declared component class inspected during blueprint compilation.
     *
     * @return the component class
     */
    Class<?> getComponentClass();

    /**
     * Returns the canonical registration key used to deduplicate and replace
     * providers in type builders.
     *
     * @return the registration key class
     */
    default Class<?> getComponentKey() {
        return resolveComponentKey(getComponentClass());
    }

    /**
     * Returns whether this provider always returns the same shared stateless
     * component instance across composed objects.
     *
     * @return {@code true} if the supplied instance is shared across owners
     */
    default boolean isSingleton() {
        return false;
    }

    /**
     * {@code Simple} implements {@link ComponentProvider} by retaining a contract
     * key, factory function, component class metadata, and singleton flag.
     *
     * @param <T> supplied component type
     * @param <I> expected initialization-info subtype
     */
    @Getter
    @AllArgsConstructor
    class Simple<T extends Component, I extends ComponentInitInfo> implements ComponentProvider<T> {
        /** Registration slot key used by type builders. */
        private final Class<?> componentKey;
        /** Factory invoked to obtain the component instance. */
        private final Function<I, T> provider;
        /** Declared class inspected when compiling component blueprints. */
        private final Class<?> componentClass;
        /** Whether {@code provider} returns a shared stateless instance. */
        private final boolean singleton;

        /**
         * Creates a non-singleton provider whose registration key is derived from
         * {@code componentClass}.
         */
        public Simple(Function<I, T> provider, Class<?> componentClass) {
            this(resolveComponentKey(componentClass), provider, componentClass, false);
        }

        /**
         * Forwards initialization data directly to {@code provider} and returns
         * the resulting component instance.
         */
        @SuppressWarnings("unchecked")
        @Override
        public T provide(ComponentInitInfo info) {
            return provider.apply((I) info);
        }
    }
}
