package org.allaymc.server.component.injector;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.description.modifier.Visibility;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.implementation.FieldAccessor;
import net.bytebuddy.implementation.Implementation;
import net.bytebuddy.implementation.MethodCall;
import net.bytebuddy.implementation.MethodDelegation;
import org.allaymc.api.component.annotation.ComponentedObject;
import org.allaymc.api.component.annotation.Dependency;
import org.allaymc.api.component.annotation.DoNotInject;
import org.allaymc.api.component.annotation.Manager;
import org.allaymc.api.component.interfaces.*;
import org.allaymc.api.eventbus.EventBus;
import org.allaymc.api.eventbus.event.Event;
import org.allaymc.api.utils.Identifier;
import org.allaymc.api.utils.ReflectionUtils;
import org.allaymc.api.utils.exception.ComponentInjectException;
import org.allaymc.server.eventbus.AllayEventBus;
import org.allaymc.server.utils.ComponentClassCacheUtils;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.*;

import static net.bytebuddy.matcher.ElementMatchers.*;

/**
 * The default injector which use byte-buddy
 * <p>
 * Allay Project 2023/3/4
 *
 * @author daoge_cmd
 */
public class AllayComponentInjector<T> implements ComponentInjector<T> {
    public static final String INITIALIZER_FIELD_NAME = "initializer";

    protected static final String COMPONENT_LIST_FIELD_NAME = "components";
    protected static final String INIT_METHOD_NAME = "initComponents";

    protected Class<T> interfaceClass;
    protected Class<T> injectedClass;
    protected List<ComponentProvider<? extends Component>> componentProviders = new ArrayList<>();

    @Override
    public ComponentInjector<T> interfaceClass(Class<T> interfaceClass) {
        Objects.requireNonNull(interfaceClass, "The interface class cannot be null");
        if (!interfaceClass.isInterface()) throw new ComponentInjectException("Interface class must be an interface!");
        this.interfaceClass = interfaceClass;
        return this;
    }

    @Override
    public ComponentInjector<T> component(List<ComponentProvider<? extends Component>> providers) {
        Objects.requireNonNull(providers, "The component providers cannot be null");
        this.componentProviders.addAll(providers);
        return this;
    }

    @Override
    public ComponentInjector<T> useCachedClass(Class<T> cachedClass) {
        injectedClass = cachedClass;
        return this;
    }

    @SneakyThrows
    @Override
    public Class<T> inject(boolean alwaysUpdate) {
        injectedClass = ComponentClassCacheUtils.getCacheClass(interfaceClass);
        if (alwaysUpdate || injectedClass == null) {
            checkComponentDuplicate();
            injectedClass = buildClass();
        }

        injectInitializer();
        return injectedClass;
    }

    protected void checkComponentDuplicate() {
        Set<Identifier> identifiers = new HashSet<>();
        for (var provider : componentProviders) {
            var identifier = provider.findComponentIdentifier();
            if (identifiers.contains(identifier))
                throw new ComponentInjectException("Duplicate component " + identifier);
            else
                identifiers.add(identifier);
        }
    }

    protected Class<T> buildClass() {
        var bb = new ByteBuddy().subclass(interfaceClass);
        var componentFieldNameMapping = new HashMap<ComponentProvider<?>, String>();

        int num = 0;
        for (var provider : componentProviders) {
            var fieldName = "f" + num++;
            componentFieldNameMapping.put(provider, fieldName);
            bb = bb.defineField(fieldName, provider.getComponentClass(), Visibility.PRIVATE);
        }

        bb = bb.defineField(COMPONENT_LIST_FIELD_NAME, List.class, Modifier.STATIC | Modifier.PRIVATE);
        bb = buildInitializer(bb);
        bb = buildConstructor(bb);

        for (var methodShouldBeInject : Arrays.stream(interfaceClass.getMethods()).filter(method -> {
            // Do not inject methods that are annotated with @DoNotInject
            if (!method.isAnnotationPresent(DoNotInject.class)) return true;

            // This method must be a default method, as there won't be implementation for it
            if (!method.isDefault())
                throw new ComponentInjectException("Annotation @DoNotInject must be used in a default method: " + interfaceClass.getName() + "::" + method.getName());
            return false;
        }).toList()) {
            Implementation.Composable methodDelegation = null;
            for (var provider : componentProviders) {
                var componentFieldName = componentFieldNameMapping.get(provider);
                try {
                    var methodImpl = provider.getComponentClass().getMethod(methodShouldBeInject.getName(), methodShouldBeInject.getParameterTypes());
                    //This method must be a default method, and the component implementation doesn't override it
                    //So that we don't need to handle it, as it has a default implementation
                    if (methodImpl.equals(methodShouldBeInject)) continue;

                    if (methodDelegation == null)
                        methodDelegation = MethodCall.invoke(methodImpl).onField(componentFieldName).withAllArguments();
                    else
                        throw new ComponentInjectException("Duplicate implementation for method: " + methodShouldBeInject.getName() + " in " + provider.getComponentClass().getName());
                } catch (NoSuchMethodException ignored) {}
            }

            if (methodDelegation == null) {
                //We do not enforce require an implementation for a method which is default
                if (methodShouldBeInject.isDefault()) continue;
                throw new ComponentInjectException("Missing implementation for method: " + interfaceClass.getSimpleName() + "::" + methodShouldBeInject.getName());
            }

            bb = bb.method(is(methodShouldBeInject)).intercept(methodDelegation);
        }

        bb = afterInject(componentProviders, bb);
        try (var unloaded = bb.make()) {
            // Why not directly use DynamicType.Unloaded#load for loading?
            // This method loads a class into a subclass of InjectionClassLoader, and each dynamically generated class resides in a different class loader. This complicates the use of Fast-Reflect to optimize constructor efficiency because the init method within the constructor cannot access this class.
            // Their class loaders are different.
            var map = unloaded.saveIn(ComponentClassCacheUtils.CACHE_ROOT_PATH.toFile());
            @SuppressWarnings("OptionalGetWithoutIsPresent")
            var pair = map.entrySet().stream().findFirst().get();
            // The simple class name of the interface to the dynamically generated implemented class name.
            ComponentClassCacheUtils.addCacheMapping(interfaceClass.getSimpleName(), pair.getKey().getName());
            return ComponentClassCacheUtils.getCacheClass(interfaceClass);
        } catch (IOException e) {
            throw new ComponentInjectException(e);
        }
    }

    protected void injectInitializer() {
        try {
            var initializer = injectedClass.getDeclaredField(AllayComponentInjector.INITIALIZER_FIELD_NAME);
            initializer.setAccessible(true);
            //inject initializer instance
            initializer.set(injectedClass, new AllayComponentInjector.Initializer<T>(componentProviders));
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    protected DynamicType.Builder<T> buildInitializer(DynamicType.Builder<T> bb) {
        bb = bb.defineField(INITIALIZER_FIELD_NAME, Initializer.class, Modifier.STATIC | Modifier.PRIVATE);
        bb = bb.defineMethod(INIT_METHOD_NAME, void.class, Modifier.PUBLIC)
                .withParameters(Object.class, ComponentInitInfo.class)
                .intercept(MethodDelegation.toField(INITIALIZER_FIELD_NAME));
        return bb;
    }

    protected DynamicType.Builder<T> buildConstructor(DynamicType.Builder<T> bb) {
        try {
            // Default constructor
            bb = bb.constructor(isDefaultConstructor()).intercept(MethodCall.invoke(Object.class.getDeclaredConstructor())
                    .andThen(MethodCall.invoke(named(INIT_METHOD_NAME)).withThis().with(ComponentInitInfo.EMPTY))
            );
            // Constructor with ComponentInitInfo
            bb = bb.defineConstructor(Visibility.PUBLIC)
                    .withParameters(ComponentInitInfo.class)
                    .intercept(MethodCall.invoke(Object.class.getDeclaredConstructor())
                            .andThen(MethodCall.invoke(named(INIT_METHOD_NAME)).withThis().withArgument(0))
                    );
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }

        return bb;
    }


    protected DynamicType.Builder<T> afterInject(List<ComponentProvider<? extends Component>> providers, DynamicType.Builder<T> bb) {
        bb = bb.implement(org.allaymc.api.component.interfaces.ComponentedObject.class)
                .method(named("getComponents"))
                .intercept(FieldAccessor.ofField(COMPONENT_LIST_FIELD_NAME));
        return bb;
    }

    @Getter
    @RequiredArgsConstructor
    protected static class AllayComponentManager<T> implements ComponentManager<T> {

        protected final EventBus eventBus = new AllayEventBus();
        protected final T componentedObject;

        @Override
        public <E extends Event> E callEvent(E event) {
            return eventBus.callEvent(event);
        }
    }

    public static class Initializer<T> {

        private final List<ComponentProvider<? extends Component>> componentProviders;
        private final Map<ComponentProvider<? extends Component>, String> componentFieldNameMapping;

        public Initializer(List<ComponentProvider<? extends Component>> componentProviders) {
            var componentFieldNameMapping = new HashMap<ComponentProvider<?>, String>();

            int num = 0;
            for (var provider : componentProviders) {
                var fieldName = "f" + num++;
                componentFieldNameMapping.put(provider, fieldName);
            }

            this.componentProviders = componentProviders;
            this.componentFieldNameMapping = componentFieldNameMapping;
        }

        public void init(T instance, ComponentInitInfo initInfo) {
            var components = componentProviders.stream().map(provider -> provider.provide(initInfo)).toList();
            injectComponentInstances(instance, components);

            var componentManager = new AllayComponentManager<>(instance);
            injectComponentManagerAndSetUpEventHandlers(componentManager, components);
            injectComponentedObject(instance, components);
            components.forEach(component -> component.onInitFinish(initInfo));
        }

        protected void injectComponentedObject(T instance, List<? extends Component> components) {
            for (var component : components) {
                ReflectionUtils.getAllFields(component.getClass()).stream()
                        .filter(field -> field.isAnnotationPresent(ComponentedObject.class))
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

        protected void injectComponentManagerAndSetUpEventHandlers(AllayComponentManager<T> manager, List<? extends Component> components) {
            for (var component : components) {
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

        protected void injectComponentInstances(Object instance, List<? extends Component> components) {
            try {
                var componentListField = instance.getClass().getDeclaredField(COMPONENT_LIST_FIELD_NAME);
                try {
                    componentListField.setAccessible(true);
                    componentListField.set(instance, components);
                } finally {
                    componentListField.setAccessible(false);
                }

                for (int index = 0; index < components.size(); index++) {
                    var provider = componentProviders.get(index);
                    var component = components.get(index);
                    injectDependency(components, component);
                    var field = instance.getClass().getDeclaredField(componentFieldNameMapping.get(provider));
                    field.setAccessible(true);
                    field.set(instance, component);
                }
            } catch (IllegalAccessException | NoSuchFieldException e) {
                throw new RuntimeException(e);
            }
        }

        protected void injectDependency(List<? extends Component> components, Component component) {
            for (var field : ReflectionUtils.getAllFields(component.getClass())) {
                var annotation = field.getAnnotation(Dependency.class);
                if (annotation != null) {
                    var type = field.getType();
                    List<Component> dependencies = new ArrayList<>(components);
                    var count = Integer.MAX_VALUE;
                    var requireCompId = annotation.identifier();
                    var soft = annotation.soft();
                    //Try to find dependencies through inheritance
                    //Try to match by namespace ID
                    if (!requireCompId.isBlank())
                        dependencies = dependencies.stream().filter(dependency -> ComponentProvider.findComponentIdentifier(dependency.getClass()).toString().equals(requireCompId)).toList();
                    else
                        dependencies = dependencies.stream().filter(type::isInstance).toList();
                    count = dependencies.size();
                    //Matches to multiple dependencies
                    if (count > 1) {
                        throw new ComponentInjectException("Found multiple dependencies " + type.getName() + " for " + component.getClass().getName());
                    }
                    //No dependencies available
                    if (count == 0) {
                        if (!soft) {
                            throw new ComponentInjectException("Cannot find dependency " + type.getName() + " for " + component.getClass().getName());
                        } else continue;
                    }
                    //Inject dependencies
                    var dependency = dependencies.get(0);
                    try {
                        field.setAccessible(true);
                        field.set(component, dependency);
                    } catch (IllegalAccessException e) {
                        throw new ComponentInjectException("Cannot inject dependency " + type.getName() + " for " + component.getClass().getName(), e);
                    }
                }
            }
        }
    }
}
