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
 * ComponentClass is the base class of the class that is composed of multiple components.
 *
 * @author daoge_cmd
 * @see ItemStackImpl
 * @see EntityImpl
 * @see BlockBehaviorImpl
 * @see BlockEntityImpl
 */
public abstract class ComponentClass {

    @Getter
    protected ComponentManager manager;

    public ComponentClass(ComponentInitInfo initInfo, List<ComponentProvider<? extends Component>> componentProviders) {
        Initializer.init(this, initInfo, componentProviders);
    }

    protected void setManager(ComponentManager manager) {
        this.manager = manager;
    }

    @Getter
    protected static class AllayComponentManager implements ComponentManager {

        protected final EventBus eventBus = new AllayEventBus();

        @Override
        public <E extends Event> E callEvent(E event) {
            return eventBus.callEvent(event);
        }
    }

    private static class Initializer {
        public static void init(ComponentClass instance, ComponentInitInfo initInfo, List<ComponentProvider<? extends Component>> componentProviders) {
            List<Component> componentList = new ArrayList<>();
            for (ComponentProvider<?> provider : componentProviders) {
                var component = provider.provide(initInfo);
                componentList.add(component);
            }

            var manager = new AllayComponentManager();
            instance.setManager(manager);
            injectComponentInstances(instance, componentList);
            injectComponentManagerAndSetUpEventHandlers(manager, componentList);
            injectComponentObject(instance, componentList);

            componentList.forEach(component -> callOnInitFinishMethod(component, initInfo));
        }

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

        protected static void injectComponentInstances(ComponentClass instance, List<? extends Component> componentList) {
            var fields = ReflectionUtils.getAllFields(instance.getClass());
            for (var field : fields) {
                var type = field.getType();
                if (!Component.class.isAssignableFrom(type)) {
                    continue;
                }

                var components = componentList.stream().filter(type::isInstance).toList();
                var count = components.size();
                // Matches to multiple components
                if (count > 1) {
                    throw new ComponentInjectException("Found multiple components " + type.getName() + " for " + instance.getClass().getName());
                }
                // No component available
                if (count == 0) {
                    throw new ComponentInjectException("Cannot find component " + type.getName() + " for " + instance.getClass().getName());
                }

                // Inject component
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
                // Try to find dependencies through inheritance
                // Try to match by namespace ID
                if (!requireCompId.isBlank()) {
                    dependencies = dependencies.stream().filter(dependency ->
                            Objects.requireNonNull(ComponentProvider.findComponentIdentifier(dependency.getClass())).toString().equals(requireCompId)
                    ).toList();
                } else {
                    dependencies = dependencies.stream().filter(type::isInstance).toList();
                }
                count = dependencies.size();
                // Matches to multiple dependencies
                if (count > 1) {
                    throw new ComponentInjectException("Found multiple dependencies " + type.getName() + " for " + component.getClass().getName());
                }
                // No dependencies available
                if (count == 0) {
                    if (!optional) {
                        throw new ComponentInjectException("Cannot find dependency " + type.getName() + " for " + component.getClass().getName());
                    } else continue;
                }
                // Inject dependency
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
