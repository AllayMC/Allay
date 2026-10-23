package org.allaymc.server.component;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.allaymc.api.component.Component;
import org.allaymc.api.component.ComponentInitInfo;
import org.allaymc.api.utils.identifier.Identifier;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;

import static java.lang.reflect.Modifier.isStatic;

/**
 * {@code ComponentProvider} supplies component instances and the class metadata
 * used to identify their registration. Providers can create a fresh component or return a shared
 * instance, as block behavior construction does; this interface neither copies
 * instances nor verifies that results match the declared class. Identifier
 * lookup searches declared fields on that class and then its superclasses.
 *
 * @author daoge_cmd
 */
public interface ComponentProvider<T extends Component> {
    /**
     * Creates a provider that ignores initialization data and invokes the
     * supplied factory on every request. The supplier controls whether results
     * are fresh or shared, while the declared class supplies identifier metadata.
     *
     * @param provider       the supplier returning a component for each request
     * @param componentClass the class of the component
     * @param <T>            the type of the component
     *
     * @return a component provider using the given supplier
     */
    static <T extends Component> ComponentProvider<T> of(Supplier<T> provider, Class<?> componentClass) {
        return of($ -> provider.get(), componentClass);
    }

    /**
     * Creates a provider that forwards initialization data to the supplied
     * function. The function and declared class are retained directly; their
     * compatibility and the lifetime of returned component instances remain the
     * provider author's responsibility.
     *
     * @param provider       a function that accepts initialization info and returns a component
     * @param componentClass the class of the component
     * @param <T>            the type of the component
     *
     * @return a component provider using the given function
     */
    static <T extends Component> ComponentProvider<T> of(Function<ComponentInitInfo, T> provider, Class<?> componentClass) {
        return new Simple<>(provider, componentClass);
    }

    /**
     * Copies provider references into a mutable map indexed by their discovered
     * identifiers. Duplicate keys are rejected rather than replaced, including
     * a second provider whose identifier is missing; one missing identifier can
     * therefore occupy the map's {@code null} key.
     *
     * @param componentProviders the list of component providers
     * @param <P>                the base type of components
     *
     * @return a map from identifiers to providers
     *
     * @throws IllegalArgumentException if duplicate identifiers are found
     */
    static <P extends Component> Map<Identifier, ComponentProvider<? extends P>> toMap(List<ComponentProvider<? extends P>> componentProviders) {
        Map<Identifier, ComponentProvider<? extends P>> map = new HashMap<>(componentProviders.size());
        for (var provider : componentProviders) {
            var id = provider.findComponentIdentifier();
            if (map.putIfAbsent(id, provider) != null) {
                throw new IllegalArgumentException("Duplicate component: " + id);
            }
        }

        return map;
    }

    /**
     * Returns the first nonnull identifier discovered while walking from the
     * supplied class toward its superclasses. Implemented interfaces are not
     * traversed; a {@code null} class or an exhausted hierarchy produces {@code null}
     * rather than an identifier derived from the class name.
     *
     * @param clazz the class to inspect
     *
     * @return the identifier if found, otherwise {@code null}
     */
    static Identifier findComponentIdentifier(Class<?> clazz) {
        var current = clazz;
        while (current != null) {
            var id = findComponentIdentifierInClass(current);
            if (id != null) {
                return id;
            }

            // A missing or null identifier lets a superclass supply the shared
            // component identity used by subclasses that do not declare their own.
            current = current.getSuperclass();
        }

        return null;
    }

    /**
     * Reads the first declared static field with the component annotation and
     * exact {@link Identifier} type. Private fields are made accessible; a {@code null}
     * field value is returned without searching later fields in that class.
     * Multiple matching declarations have no explicit ordering guarantee.
     *
     * @param clazz the class to inspect
     *
     * @return the identifier if found, otherwise {@code null}
     */
    static Identifier findComponentIdentifierInClass(Class<?> clazz) {
        try {
            for (var field : clazz.getDeclaredFields()) {
                if (field.isAnnotationPresent(Identifier.Component.class) && Identifier.class == field.getType() && isStatic(field.getModifiers())) {
                    field.setAccessible(true);
                    return (Identifier) field.get(null);
                }
            }

            return null;
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Failed to access component identifier field in " + clazz.getName(), e);
        }
    }

    /**
     * Supplies a component using initialization data from the composed object's
     * constructor. Implementations define the accepted data type and whether
     * instances are fresh or shared; the component initializer retains and
     * injects the returned instance directly.
     *
     * @param info the component initialization information
     *
     * @return the created component instance
     */
    T provide(ComponentInitInfo info);

    /**
     * Returns the class metadata used to discover this provider's identifier.
     *
     * @return the component class
     */
    Class<?> getComponentClass();

    /**
     * Discovers the identifier through this provider's declared component class.
     * The result can be {@code null} when no suitable field supplies an
     * identifier; this lookup does not instantiate a component to inspect it.
     *
     * @return the component identifier, or {@code null} if not found
     */
    default Identifier findComponentIdentifier() {
        return findComponentIdentifier(getComponentClass());
    }

    /**
     * {@code Simple} implements a component provider by retaining its factory and
     * class metadata.
     * The generated constructor stores both references without validation; the
     * factory determines the accepted initialization subtype and whether returned
     * instances are shared with other composed objects.
     *
     * @param <T> the type of the component
     * @param <I> the type of the component initialization info
     */
    @Getter
    @AllArgsConstructor
    class Simple<T extends Component, I extends ComponentInitInfo> implements ComponentProvider<T> {
        /**
         * {@code provider} retains the factory invoked for each request. The
         * generated getter exposes the same function without adapting its
         * initialization-type requirements.
         */
        private Function<I, T> provider;
        /**
         * {@code componentClass} supplies identifier lookup metadata independently
         * of factory results. Callers must keep the declared class consistent with
         * the supplied components.
         */
        private Class<?> componentClass;

        /**
         * Passes initialization data directly to the retained factory and
         * returns its result. The factory's accepted subtype is not checked
         * during provider creation, so incompatible data can fail when the
         * function is invoked.
         */
        @Override
        public T provide(ComponentInitInfo info) {
            return provider.apply((I) info);
        }
    }
}
