package org.allaymc.server.component.injector;

import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.description.modifier.Visibility;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.implementation.FieldAccessor;
import net.bytebuddy.implementation.Implementation;
import net.bytebuddy.implementation.MethodCall;
import net.bytebuddy.implementation.MethodDelegation;
import org.allaymc.api.component.annotation.DoNotInject;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.api.component.interfaces.ComponentInitInfo;
import org.allaymc.api.component.interfaces.ComponentManager;
import org.allaymc.api.eventbus.EventBus;
import org.allaymc.api.eventbus.event.Event;
import org.allaymc.api.utils.Identifier;
import org.allaymc.server.utils.ComponentInjectException;
import org.allaymc.server.component.annotation.*;
import org.allaymc.server.component.interfaces.ComponentProvider;
import org.allaymc.server.eventbus.AllayEventBus;
import org.allaymc.server.utils.ComponentClassCacheUtils;
import org.allaymc.server.utils.ReflectionUtils;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.*;

import static net.bytebuddy.matcher.ElementMatchers.*;

/**
 * The default injector which use byte-buddy.
 *
 * @author daoge_cmd
 */
public class AllayComponentInjector<T> {
    // NOTICE: This field should be public and static
    // because generated dynamic classes must be able to touch this class
    public static final ComponentInitInfo EMPTY = new ComponentInitInfo() {};

    protected static final String INITIALIZER_FIELD_NAME = "initializer";
    protected static final String INIT_METHOD_NAME = "initComponents";
    protected static final String MANAGER_FIELD_NAME = "manager";
    protected static final String MANAGER_GETTER_METHOD_NAME = "getManager";

    protected Class<T> interfaceClass;
    protected Class<T> injectedClass;
    protected List<ComponentProvider<? extends Component>> componentProviders = new ArrayList<>();

    /**
     * Defines the parent class for this injector
     *
     * @param interfaceClass the interface class
     *
     * @return the injector
     */
    public AllayComponentInjector<T> interfaceClass(Class<T> interfaceClass) {
        Objects.requireNonNull(interfaceClass, "The interface class cannot be null");
        if (!interfaceClass.isInterface()) throw new ComponentInjectException("Interface class must be an interface!");
        this.interfaceClass = interfaceClass;
        return this;
    }

    /**
     * Bind a set of implementations for the injector.
     * <p>
     * When the dynamic class is instantiated, the component instance will be obtained from the Provider.
     * <p>
     * If there are multiple implementation methods for a method to be injected, they will be executed in the order in the component list
     * and the return value is the return value of the last executed method
     *
     * @param providers component providers
     *
     * @return the injector
     */
    public AllayComponentInjector<T> component(List<ComponentProvider<? extends Component>> providers) {
        Objects.requireNonNull(providers, "The component providers cannot be null");
        this.componentProviders.addAll(providers);
        return this;
    }

    /**
     * Use a cached class for the injector to speed up the injection process.
     *
     * @param cachedClass the cached class
     *
     * @return the injector
     */
    public AllayComponentInjector<T> useCachedClass(Class<T> cachedClass) {
        injectedClass = cachedClass;
        return this;
    }

    /**
     * Build the class.
     * <p>
     * Note that we guarantee that the returned class implements the {@link org.allaymc.api.component.interfaces.ComponentedObject} interface
     *
     * @return the class
     */
    @SneakyThrows
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

        // Define fields for component instances
        int num = 0;
        for (var provider : componentProviders) {
            var fieldName = "f" + num++;
            componentFieldNameMapping.put(provider, fieldName);
            bb = bb.defineField(fieldName, provider.getComponentClass(), Visibility.PRIVATE);
        }
        // Define field for component manager
        bb = bb.defineField(MANAGER_FIELD_NAME, ComponentManager.class, Modifier.PRIVATE);

        bb = buildInitializer(bb);
        bb = buildConstructor(bb);

        for (var methodShouldBeInject : Arrays.stream(interfaceClass.getMethods()).filter(method -> {
            // Skip static methods
            if (Modifier.isStatic(method.getModifiers())) return false;

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
                    // This method is annotated with @Ignore, which means that it won't be used as a method impl, so we skip it
                    if (methodImpl.isAnnotationPresent(Ignore.class)) continue;
                    // This method must be a default method, and the component implementation doesn't override it
                    // So that we don't need to handle it, as it has a default implementation
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

        // Define getter for component manager
        bb = bb.implement(org.allaymc.api.component.interfaces.ComponentedObject.class)
                .method(named(MANAGER_GETTER_METHOD_NAME))
                .intercept(FieldAccessor.ofField(MANAGER_FIELD_NAME));

        try (var unloaded = bb.make()) {
            // Why not directly use DynamicType.Unloaded#load for loading?
            // This method loads a class into a subclass of InjectionClassLoader, and each dynamically generated class resides in a different class loader.
            // This complicates the use of Fast-Reflect to optimize constructor efficiency because the init method within the constructor cannot access this class.
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
            initializer.set(injectedClass, new Initializer<T>(componentProviders));
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
            // NOTICE: Default constructor shouldn't be removed!
            bb = bb.constructor(isDefaultConstructor()).intercept(MethodCall.invoke(Object.class.getDeclaredConstructor())
                    .andThen(MethodCall.invoke(named(INIT_METHOD_NAME)).withThis().with(EMPTY))
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

    @Getter
    protected static class AllayComponentManager implements ComponentManager {

        protected final EventBus eventBus = new AllayEventBus();
        @Setter
        protected Map<Identifier, ? extends Component> components;

        @Override
        public <E extends Event> E callEvent(E event) {
            return eventBus.callEvent(event);
        }

        @Override
        public <T> T getComponent(Identifier componentIdentifier) {
            // noinspection unchecked
            return (T) components.get(componentIdentifier);
        }
    }

    // NOTICE: This class must be public and static
    // because generated dynamic classes must be able to touch this class
    public static class Initializer<T> {

        protected final List<ComponentProvider<? extends Component>> componentProviders;
        protected final List<Identifier> componentIdentifiers;

        public Initializer(List<ComponentProvider<? extends Component>> componentProviders) {
            this.componentProviders = componentProviders;
            // Used pre-prepared component identifiers to
            // speed up the initialization process
            this.componentIdentifiers = new ArrayList<>();
            for (var provider : componentProviders) {
                componentIdentifiers.add(provider.findComponentIdentifier());
            }
        }

        public void init(T instance, ComponentInitInfo initInfo) {
            Map<Identifier, Component> componentMap = new HashMap<>();
            // The order of component instances list should be same to the
            // order of component providers list to make sure that
            // correct component instance is injected to the correct field
            List<Component> componentList = new ArrayList<>();
            for (int index = 0; index < componentProviders.size(); index++) {
                var provider = componentProviders.get(index);
                var component = provider.provide(initInfo);
                componentMap.put(componentIdentifiers.get(index), component);
                componentList.add(component);
            }

            var manager = new AllayComponentManager();
            createComponentManager(instance, manager, componentMap);
            injectComponentInstances(instance, componentList);
            injectComponentManagerAndSetUpEventHandlers(manager, componentList);
            injectComponentedObject(instance, componentList);

            componentList.forEach(component -> callOnInitFinishMethod(component, initInfo));
        }

        protected void callOnInitFinishMethod(Component component, ComponentInitInfo initInfo) {
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

        protected void createComponentManager(Object instance, AllayComponentManager manager, Map<Identifier, Component> componentMap) {
            Field managerField = null;
            try {
                managerField = instance.getClass().getDeclaredField(MANAGER_FIELD_NAME);
                managerField.setAccessible(true);
                managerField.set(instance, manager);
                ((AllayComponentManager) managerField.get(instance)).setComponents(componentMap);
            } catch (Exception e) {
                throw new ComponentInjectException("Cannot create component manager!", e);
            } finally {
                if (managerField != null) {
                    managerField.setAccessible(false);
                }
            }
        }

        protected void injectComponentedObject(T instance, List<? extends Component> componentList) {
            for (var component : componentList) {
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

        protected void injectComponentManagerAndSetUpEventHandlers(AllayComponentManager manager, List<? extends Component> componentList) {
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

        protected void injectComponentInstances(Object instance, List<? extends Component> componentList) {
            try {
                for (int index = 0; index < componentList.size(); index++) {
                    var provider = componentProviders.get(index);
                    var component = componentList.get(index);
                    injectDependency(componentList, component);
                    var field = instance.getClass().getDeclaredField("f" + index);
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
                if (annotation == null) continue;

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
                var dependency = dependencies.getFirst();
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
