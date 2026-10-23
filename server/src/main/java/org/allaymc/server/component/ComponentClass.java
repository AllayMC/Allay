package org.allaymc.server.component;

import lombok.Getter;
import org.allaymc.api.component.Component;
import org.allaymc.api.component.ComponentInitInfo;
import org.allaymc.api.eventbus.EventBus;
import org.allaymc.api.eventbus.event.Event;
import org.allaymc.server.block.impl.BlockBehaviorImpl;
import org.allaymc.server.blockentity.impl.BlockEntityImpl;
import org.allaymc.server.component.annotation.ComponentObject;
import org.allaymc.server.component.annotation.Dependency;
import org.allaymc.server.component.annotation.Manager;
import org.allaymc.server.component.annotation.OnInitFinish;
import org.allaymc.server.entity.impl.EntityImpl;
import org.allaymc.server.eventbus.AllayEventBus;
import org.allaymc.server.item.impl.ItemStackImpl;
import org.allaymc.server.utils.ComponentInjectException;
import org.allaymc.server.utils.ReflectionUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * {@code ComponentClass} represents an object whose behavior is supplied by a
 * connected set of components. Each component-typed field on the object must select
 * exactly one supplied implementation by assignable type. Dependencies inside
 * components can instead select an explicit identifier, with optional absence
 * permitted only when the dependency annotation requests it.
 *
 * <p>
 * The constructor creates every component before injecting dependencies, managers,
 * event listeners, and owner references. Completion hooks run only after those
 * phases finish, so a hook can dispatch events to sibling components with
 * their injection already complete. Those hooks still execute during this
 * superclass constructor, before subclass construction finishes. Injection is
 * not transactional: a failure stops construction without undoing earlier
 * assignments, listener registration, or provider side effects.
 *
 * @author daoge_cmd
 * @see ItemStackImpl
 * @see EntityImpl
 * @see BlockBehaviorImpl
 * @see BlockEntityImpl
 */
public abstract class ComponentClass {

    /**
     * {@code manager} holds this object's event-dispatch manager after providers have run and
     * before injections and completion hooks begin; the generated getter exposes
     * the same manager instance.
     */
    @Getter
    protected ComponentManager manager;

    /**
     * Creates and wires components using the supplied initialization object and
     * providers in list order. Completion hooks run before this constructor
     * returns, so they must not depend on subclass initializers or constructor
     * statements having executed. Provider and injection failures abort
     * construction without rolling back earlier side effects.
     */
    public ComponentClass(ComponentInitInfo initInfo, List<ComponentProvider<? extends Component>> componentProviders) {
        Initializer.init(this, initInfo, componentProviders);
    }

    /**
     * Assigns the manager exposed by this composed object's generated getter.
     * Initialization calls this before injecting components; later replacement
     * does not update manager references already stored inside those components.
     */
    protected void setManager(ComponentManager manager) {
        this.manager = manager;
    }

    /**
     * {@code AllayComponentManager} implements one composed object's manager using
     * its own component event bus.
     */
    @Getter
    protected static class AllayComponentManager implements ComponentManager {

        /**
         * {@code eventBus} stores handlers registered from this owner's components.
         * The generated getter exposes this bus, whose asynchronous executor comes
         * from the server.
         */
        protected final EventBus eventBus = new AllayEventBus();

        /**
         * Dispatches through this owner's component bus and returns the supplied
         * event reference. Synchronous handlers finish during dispatch, while
         * handlers configured as asynchronous may continue after this method returns.
         */
        @Override
        public <E extends Event> E callEvent(E event) {
            return eventBus.callEvent(event);
        }
    }

    /**
     * {@code Initializer} performs component construction, reflective injection,
     * and completion-hook invocation in order.
     */
    private static class Initializer {
        /**
         * Creates all supplied components and initializes their connections to
         * the owning object. Providers run in list order before any injection,
         * and completion hooks follow that same component order after all
         * injections and listener registrations finish. Hooks within one
         * component use reflection's returned method order without sorting.
         *
         * <p>
         * Failures abort the remaining phases without rollback. Callers must
         * therefore avoid publishing the object or reusing partially initialized
         * components when a provider, injection, or hook fails.
         */
        public static void init(ComponentClass instance, ComponentInitInfo initInfo, List<ComponentProvider<? extends Component>> componentProviders) {
            List<Component> componentList = new ArrayList<>();
            for (ComponentProvider<?> provider : componentProviders) {
                var component = provider.provide(initInfo);
                componentList.add(component);
            }

            // Finish all connections before invoking hooks: a hook can emit
            // component events whose handlers need both dependency and owner references.
            var manager = new AllayComponentManager();
            instance.setManager(manager);
            injectComponentInstances(instance, componentList);
            injectComponentManagerAndSetUpEventHandlers(manager, componentList);
            injectComponentObject(instance, componentList);

            componentList.forEach(component -> callOnInitFinishMethod(component, initInfo));
        }

        /**
         * Invokes public annotated methods that return {@code void} and accept one
         * argument, including inherited public methods. The supplied initialization
         * object must be compatible with each parameter; incompatible arguments
         * or failures inside a hook become {@link ComponentInjectException}.
         * Annotated methods with other shapes are skipped, and invocation order
         * is not explicitly sorted.
         */
        protected static void callOnInitFinishMethod(Component component, ComponentInitInfo initInfo) {
            Arrays.stream(component.getClass().getMethods())
                    .filter(method ->
                            method.isAnnotationPresent(OnInitFinish.class) &&
                            method.getReturnType() == void.class &&
                            method.getParameterCount() == 1)
                    .forEach(method -> {
                        method.setAccessible(true);
                        try {
                            method.invoke(component, initInfo);
                        } catch (Exception e) {
                            throw new ComponentInjectException("Cannot call onInitFinish method on component: " + component.getClass().getName(), e);
                        }
                    });
        }

        /**
         * Assigns the owner to every annotated field found through each
         * component's class hierarchy. Field types must accept that owner.
         * Only {@code IllegalAccessException} is wrapped as
         * {@link ComponentInjectException}; runtime accessibility failures from
         * {@code setAccessible} and incompatible assignments propagate directly.
         */
        protected static void injectComponentObject(ComponentClass instance, List<? extends Component> componentList) {
            for (var component : componentList) {
                ReflectionUtils.getAllFields(component.getClass()).stream()
                        .filter(field -> field.isAnnotationPresent(ComponentObject.class))
                        .forEach(field -> {
                            try {
                                field.setAccessible(true);
                                field.set(component, instance);
                            } catch (IllegalAccessException e) {
                                throw new ComponentInjectException("Cannot inject componented object to component: " + component.getClass().getName(), e);
                            }
                        });
            }
        }

        /**
         * Injects the shared manager into annotated component fields and
         * registers each component as an event listener. Components are processed
         * in list order; a later injection or registration failure leaves earlier
         * assignments and listener registrations in place.
         */
        protected static void injectComponentManagerAndSetUpEventHandlers(AllayComponentManager manager, List<? extends Component> componentList) {
            for (var component : componentList) {
                ReflectionUtils.getAllFields(component.getClass()).stream()
                        .filter(field -> field.isAnnotationPresent(Manager.class))
                        .forEach(field -> {
                            try {
                                field.setAccessible(true);
                                field.set(component, manager);
                            } catch (IllegalAccessException e) {
                                throw new ComponentInjectException("Cannot inject component manager to component: " + component.getClass().getName(), e);
                            }
                        });

                manager.eventBus.registerListener(component);
            }
        }

        /**
         * Resolves every component-typed owner field to exactly one supplied
         * instance before injecting dependencies within those instances. Fields
         * are collected across the owner's superclass chain, so inherited
         * delegation fields participate alongside subclass fields. Missing or
         * multiple assignable implementations abort construction rather than
         * choosing an arbitrary provider.
         *
         * <p>
         * Dependency injection starts only after all owner fields have been
         * populated. Assignments already made remain in place when either phase
         * fails; this method does not construct replacements or roll back state.
         */
        protected static void injectComponentInstances(ComponentClass instance, List<? extends Component> componentList) {
            var fields = ReflectionUtils.getAllFields(instance.getClass());
            for (var field : fields) {
                var type = field.getType();
                if (!Component.class.isAssignableFrom(type)) {
                    continue;
                }

                var components = componentList.stream().filter(type::isInstance).toList();
                var count = components.size();
                if (count > 1) {
                    throw new ComponentInjectException("Found multiple components " + type.getName() + " for " + instance.getClass().getName());
                }
                if (count == 0) {
                    throw new ComponentInjectException("Cannot find component " + type.getName() + " for " + instance.getClass().getName());
                }

                var component = components.getFirst();
                try {
                    field.setAccessible(true);
                    field.set(instance, component);
                } catch (IllegalAccessException e) {
                    throw new ComponentInjectException("Cannot inject component " + type.getName() + " to " + component.getClass().getName(), e);
                }
            }
            for (var component : componentList) {
                injectDependency(componentList, component);
            }
        }

        /**
         * Resolves annotated component fields against the supplied instances.
         * A nonblank identifier selects by exact identifier text instead of
         * assignable type; otherwise the field's type selects candidates. More
         * than one match is always an error, while an optional missing match
         * leaves the field's existing value unchanged.
         *
         * <p>
         * Identifier selection requires every inspected candidate to expose a
         * nonnull identifier, and a selected instance must still be assignable
         * when reflection writes the field. Annotated fields whose declared
         * type does not implement {@link Component} are ignored. Dependency
         * failures preserve earlier field assignments rather than rolling them back.
         */
        protected static void injectDependency(List<? extends Component> components, Component component) {
            for (var field : ReflectionUtils.getAllFields(component.getClass())) {
                var annotation = field.getAnnotation(Dependency.class);
                if (annotation == null) {
                    continue;
                }

                var type = field.getType();
                if (!Component.class.isAssignableFrom(type)) {
                    continue;
                }

                List<Component> dependencies = new ArrayList<>(components);
                var count = Integer.MAX_VALUE;
                var requireCompId = annotation.identifier();
                var optional = annotation.optional();
                // An explicit identifier replaces type-based selection; the later
                // reflective assignment still rejects an identified component incompatible with this field.
                if (!requireCompId.isBlank()) {
                    dependencies = dependencies.stream().filter(dependency ->
                            Objects.requireNonNull(ComponentProvider.findComponentIdentifier(dependency.getClass())).toString().equals(requireCompId)
                    ).toList();
                } else {
                    dependencies = dependencies.stream().filter(type::isInstance).toList();
                }
                count = dependencies.size();
                if (count > 1) {
                    throw new ComponentInjectException("Found multiple dependencies " + type.getName() + " for " + component.getClass().getName());
                }
                if (count == 0) {
                    if (!optional) {
                        throw new ComponentInjectException("Cannot find dependency " + type.getName() + " for " + component.getClass().getName());
                    } else continue;
                }
                var dependency = dependencies.getFirst();
                try {
                    field.setAccessible(true);
                    field.set(component, dependency);
                } catch (IllegalAccessException e) {
                    throw new ComponentInjectException("Cannot inject dependency " + type.getName() + " to " + component.getClass().getName(), e);
                }
            }
        }
    }
}
