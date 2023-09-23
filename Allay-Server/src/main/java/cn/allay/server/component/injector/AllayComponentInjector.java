package cn.allay.server.component.injector;

import cn.allay.api.component.annotation.ComponentEventListener;
import cn.allay.api.component.annotation.Dependency;
import cn.allay.api.component.annotation.DoNotInject;
import cn.allay.api.component.annotation.Manager;
import cn.allay.api.component.exception.ComponentInjectException;
import cn.allay.api.component.interfaces.*;
import cn.allay.api.identifier.Identifier;
import cn.allay.api.utils.ReflectionUtils;
import cn.allay.server.utils.ComponentClassCacheUtils;
import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import lombok.SneakyThrows;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.description.modifier.Visibility;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.implementation.FieldAccessor;
import net.bytebuddy.implementation.Implementation;
import net.bytebuddy.implementation.MethodCall;
import net.bytebuddy.implementation.MethodDelegation;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
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

    public AllayComponentInjector() {
    }

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
    public Class<T> inject(boolean createCache) {
        if (injectedClass == null) {
            checkComponentDuplicate();
            injectedClass = buildClass(createCache);
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

    @SuppressWarnings("unchecked")
    protected Class<T> buildClass(boolean cache) {
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
            //Do not inject methods that are annotated with @DoNotInject
            if (method.isAnnotationPresent(DoNotInject.class)) {
                //This method must be a default method, as there won't be implementation for it
                if (!method.isDefault())
                    throw new ComponentInjectException("Annotation @DoNotInject must be used in a default method: " + interfaceClass.getName() + "::" + method.getName());
                return false;
            } else return true;
        }).toList()) {
            Implementation.Composable methodDelegation = null;
            for (var provider : componentProviders) {
                var componentFieldName = componentFieldNameMapping.get(provider);
                try {
                    Method methodImpl = provider.getComponentClass().getMethod(methodShouldBeInject.getName(), methodShouldBeInject.getParameterTypes());
                    if (methodImpl.equals(methodShouldBeInject)) {
                        //This method must be a default method, and the component implementation doesn't override it
                        //So that we don't need to handle it, as it has a default implementation
                        continue;
                    }
                    if (methodDelegation == null)
                        methodDelegation = MethodCall.invoke(methodImpl).onField(componentFieldName).withAllArguments();
                    else
                        throw new ComponentInjectException("Duplicate implementation for method: " + methodShouldBeInject.getName() + " in " + provider.getComponentClass().getName());
                } catch (NoSuchMethodException ignored) {}
            }
            if (methodDelegation == null) {
                if (methodShouldBeInject.isDefault()) {
                    //We do not enforce require a implementation for a method which is default
                    continue;
                } else {
                    throw new ComponentInjectException("Missing implementation for method: " + interfaceClass.getSimpleName() + "::" + methodShouldBeInject.getName());
                }
            }
            bb = bb.method(is(methodShouldBeInject))
                    .intercept(methodDelegation);
        }
        bb = afterInject(componentProviders, bb);
        try (var unloaded = bb.make()) {
            if (cache) {
                unloaded.saveIn(ComponentClassCacheUtils.CACHE_ROOT.toFile());
            }
            return (Class<T>) unloaded
                    .load(getClass().getClassLoader())
                    .getLoaded();
        } catch (IOException e) {
            throw new ComponentInjectException(e);
        }
    }

    protected void injectInitializer() {
        try {
            Field initializer = injectedClass.getDeclaredField(AllayComponentInjector.INITIALIZER_FIELD_NAME);
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
            //Default constructor
            bb = bb.constructor(isDefaultConstructor())
                    .intercept(MethodCall.invoke(Object.class.getDeclaredConstructor())
                            .andThen(MethodCall.invoke(named(INIT_METHOD_NAME)).withThis().with(ComponentInitInfo.EMPTY))
                    );
            //Constructor with ComponentInitInfo
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
        bb = bb.implement(ComponentedObject.class)
                .method(named("getComponents"))
                .intercept(FieldAccessor.ofField(COMPONENT_LIST_FIELD_NAME));
        return bb;
    }

    protected static class AllayComponentManager<T> implements ComponentManager<T> {

        Map<Class<? extends ComponentEvent>, List<Listener>> listenerMap = new Object2ObjectOpenHashMap<>();
        T componentedObject;

        public AllayComponentManager(T componentedObject) {
            this.componentedObject = componentedObject;
        }

        @Override
        public <E extends ComponentEvent> E callEvent(E event) {
            if (!listenerMap.containsKey(event.getClass()))
                return event;
            for (var listener : listenerMap.get(event.getClass()))
                listener.access(event);
            return event;
        }

        @Override
        public T getComponentedObject() {
            return componentedObject;
        }

        private void registerListener(Class<? extends ComponentEvent> eventClass, Listener listener) {
            listenerMap.computeIfAbsent(eventClass, k -> new ArrayList<>()).add(listener);
        }

        protected static class Listener {

            private final Method method;
            private final Object instance;

            Listener(Method method, Object instance) {
                this.method = method;
                this.instance = instance;
            }

            static Listener wrap(Method method, Object instance) {
                return new Listener(method, instance);
            }

            void access(ComponentEvent event) {
                try {
                    method.invoke(instance, event);
                } catch (IllegalAccessException | InvocationTargetException e) {
                    throw new RuntimeException(e);
                }
            }
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
            List<? extends Component> components = componentProviders.stream().map(provider -> provider.provide(initInfo)).toList();
            injectComponentInstances(instance, components);
            var componentManager = new AllayComponentManager<>(instance);
            injectComponentManager(componentManager, components);
            components.forEach(component -> component.onInitFinish(initInfo));
        }

        protected void injectComponentManager(AllayComponentManager<T> manager, List<? extends Component> components) {
            for (var component : components) {
                for (var field : ReflectionUtils.getAllFields(component.getClass())) {
                    if (field.isAnnotationPresent(Manager.class)) {
                        try {
                            field.setAccessible(true);
                            field.set(component, manager);
                        } catch (IllegalAccessException e) {
                            throw new ComponentInjectException("Cannot inject component manager to component: " + component.getClass().getName(), e);
                        }
                    }
                }
                for (var method : ReflectionUtils.getAllMethods(component.getClass())) {
                    if (!method.isAnnotationPresent(ComponentEventListener.class))
                        continue;
                    if (!(method.getReturnType() == void.class))
                        throw new ComponentInjectException("Component event listener method must be void: " + method.getName() + " in component: " + component.getClass().getName());
                    if (method.getParameterCount() != 1 || !ComponentEvent.class.isAssignableFrom(method.getParameters()[0].getType()))
                        throw new ComponentInjectException("Component event listener method must have one parameter and the parameter must be a subclass of ComponentEvent: " + method.getName() + " in component: " + component.getClass().getName());
                    method.setAccessible(true);
                    manager.registerListener((Class<? extends ComponentEvent>) method.getParameters()[0].getType(), AllayComponentManager.Listener.wrap(method, component));
                }
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
