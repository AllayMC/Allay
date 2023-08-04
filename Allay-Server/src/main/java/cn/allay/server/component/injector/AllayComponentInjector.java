package cn.allay.server.component.injector;

import cn.allay.api.component.annotation.*;
import cn.allay.api.component.exception.ComponentInjectException;
import cn.allay.api.component.interfaces.*;
import cn.allay.api.identifier.Identifier;
import cn.allay.server.utils.ComponentClassCacheUtils;
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
import java.util.concurrent.ConcurrentHashMap;

import static net.bytebuddy.matcher.ElementMatchers.*;

/**
 * The default injector which use byte-buddy
 * <p>
 * Allay Project 2023/3/4
 *
 * @author daoge_cmd
 */
public class AllayComponentInjector<T> implements ComponentInjector<T> {
    //TODO: remove this
    protected static final boolean DEBUG = false;
    public static final String INITIALIZER_FIELD_NAME = "initializer";
    protected static final String COMPONENT_LIST_FIELD_NAME = "components";
    protected static final String INIT_METHOD_NAME = "initComponents";
    protected Class<T> interfaceClass;
    protected Class<T> injectedClass;
    protected List<ComponentProvider<? extends ComponentImpl>> componentProviders = new ArrayList<>();

    public AllayComponentInjector() {
    }

    @Override
    public ComponentInjector<T> interfaceClass(Class<T> interfaceClass) {
        Objects.requireNonNull(interfaceClass, "The interface class cannot be null");
        this.interfaceClass = interfaceClass;
        return this;
    }

    @Override
    public ComponentInjector<T> component(List<ComponentProvider<? extends ComponentImpl>> providers) {
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
    public Class<T> inject(boolean cache) {
        if (injectedClass == null) {
            checkComponentDuplicate();
            injectedClass = buildClass(cache);
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
        bb = buildInitializer(bb, componentFieldNameMapping);
        bb = buildConstructor(bb);
        for (var methodShouldBeInject : Arrays.stream(interfaceClass.getMethods()).filter(m -> m.isAnnotationPresent(Inject.class)).toList()) {
            var canDuplicate = methodShouldBeInject.getReturnType().equals(Void.class);
            Implementation.Composable methodDelegation = null;
            for (var provider : componentProviders) {
                var componentFieldName = componentFieldNameMapping.get(provider);
                try {
                    Method methodImpl = provider.getComponentClass().getMethod(methodShouldBeInject.getName(), methodShouldBeInject.getParameterTypes());
                    if (!methodImpl.isAnnotationPresent(Impl.class)) continue;
                    if (methodDelegation == null) methodDelegation = MethodDelegation.toField(componentFieldName);
                    else if (canDuplicate)
                        methodDelegation = methodDelegation.andThen(MethodDelegation.toField(componentFieldName));
                    else
                        throw new ComponentInjectException("Duplicate implementation for non-void-return method: " + methodShouldBeInject.getName() + " in " + provider.getComponentClass().getName());
                } catch (NoSuchMethodException ignored) {
                }
            }
            if (methodDelegation == null)
                throw new ComponentInjectException("Missing implementation for method: " + interfaceClass.getSimpleName() + "::" + methodShouldBeInject.getName());
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
        Field initializer = null;
        try {
            initializer = injectedClass.getDeclaredField(AllayComponentInjector.INITIALIZER_FIELD_NAME);
            initializer.setAccessible(true);
            //inject initializer instance
            initializer.set(injectedClass, new AllayComponentInjector.Initializer<T>(componentProviders));
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        } finally {
            if (initializer != null) {
                initializer.setAccessible(false);
            }
        }
    }

    protected DynamicType.Builder<T> buildInitializer(DynamicType.Builder<T> bb, HashMap<ComponentProvider<?>, String> componentFieldNameMapping) {
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


    protected DynamicType.Builder<T> afterInject(List<ComponentProvider<? extends ComponentImpl>> providers, DynamicType.Builder<T> bb) {
        bb = bb.implement(ComponentedObject.class)
                .method(named("getComponents"))
                .intercept(FieldAccessor.ofField(COMPONENT_LIST_FIELD_NAME));
        return bb;
    }

    protected static class AllayComponentManager<T> implements ComponentManager<T> {

        Map<Class<? extends ComponentEvent>, List<Listener>> listenerMap = new ConcurrentHashMap<>();
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

        private final List<ComponentProvider<? extends ComponentImpl>> componentProviders;
        private final Map<ComponentProvider<? extends ComponentImpl>, String> componentFieldNameMapping;

        public Initializer(List<ComponentProvider<? extends ComponentImpl>> componentProviders) {
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
            List<? extends ComponentImpl> components = componentProviders.stream().map(provider -> provider.provide(initInfo)).toList();
            injectComponentInstances(instance, components);
            var componentManager = new AllayComponentManager<>(instance);
            injectComponentManager(componentManager, components);
            components.forEach(ComponentImpl::onInitFinish);
        }

        protected void injectComponentManager(AllayComponentManager<T> manager, List<? extends ComponentImpl> components) {
            for (var component : components) {
                for (var field : component.getClass().getDeclaredFields()) {
                    if (field.isAnnotationPresent(Manager.class)) {
                        try {
                            field.setAccessible(true);
                            field.set(component, manager);
                        } catch (IllegalAccessException e) {
                            throw new ComponentInjectException("Cannot inject component manager to component: " + component.getClass().getName(), e);
                        } finally {
                            field.setAccessible(false);
                        }
                    }
                }
                for (var method : component.getClass().getDeclaredMethods()) {
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

        protected void injectComponentInstances(Object instance, List<? extends ComponentImpl> components) {
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
                    try {
                        field.setAccessible(true);
                        field.set(instance, component);
                    } finally {
                        field.setAccessible(false);
                    }
                }
            } catch (IllegalAccessException | NoSuchFieldException e) {
                throw new RuntimeException(e);
            }
        }

        protected void injectDependency(List<? extends ComponentImpl> components, ComponentImpl component) {
            for (var field : component.getClass().getDeclaredFields()) {
                var annotation = field.getAnnotation(Dependency.class);
                if (annotation != null) {
                    var type = field.getType();
                    List<ComponentImpl> dependencies = new ArrayList<>(components);
                    var count = Integer.MAX_VALUE;
                    var requireCompId = annotation.identifier();
                    //Try to find dependencies through inheritance
                    //Try to match by namespace ID
                    if (!requireCompId.isBlank())
                        dependencies = dependencies.stream().filter(dependency -> ComponentProvider.findComponentIdentifier(dependency.getClass()).toString().equals(requireCompId)).toList();
                    else
                        dependencies = dependencies.stream().filter(type::isInstance).toList();
                    count = dependencies.size();
                    //Matches to multiple dependencies
                    if (count > 1)
                        throw new ComponentInjectException("Found multiple dependencies " + type.getName() + " for " + component.getClass().getName());
                    //No dependencies available
                    if (count == 0)
                        throw new ComponentInjectException("Cannot find dependency " + type.getName() + " for " + component.getClass().getName());
                    //Inject dependencies
                    var dependency = dependencies.get(0);
                    field.setAccessible(true);
                    try {
                        field.set(component, dependency);
                    } catch (IllegalAccessException e) {
                        throw new ComponentInjectException("Cannot inject dependency " + type.getName() + " for " + component.getClass().getName(), e);
                    }
                }
            }
        }
    }
}
