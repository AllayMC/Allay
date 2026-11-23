package org.allaymc.server.component;

import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
import lombok.Getter;
import org.allaymc.api.block.BlockBehavior;
import org.allaymc.api.blockentity.BlockEntity;
import org.allaymc.api.component.Component;
import org.allaymc.api.component.ComponentInitInfo;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.eventbus.EventHandler;
import org.allaymc.api.eventbus.event.Event;
import org.allaymc.api.item.ItemStack;
import org.allaymc.server.block.impl.BlockBehaviorImpl;
import org.allaymc.server.blockentity.impl.BlockEntityImpl;
import org.allaymc.server.component.annotation.ComponentObject;
import org.allaymc.server.component.annotation.Dependency;
import org.allaymc.server.component.annotation.Manager;
import org.allaymc.server.entity.impl.EntityImpl;
import org.allaymc.server.item.impl.ItemStackImpl;
import org.allaymc.server.utils.ComponentInjectException;
import org.allaymc.server.utils.ReflectionUtils;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.invoke.VarHandle;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * {@code ComponentBlueprint} holds the precompiled assembly and event-dispatch
 * plan for one composed object type. Compilation runs once when a type is built,
 * validating every owner delegate field, component dependency, owner-reference
 * assignment, manager field, and component event handler before any instance is
 * created.
 *
 * <p>
 * Moving reflection and handler discovery into this immutable blueprint eliminates
 * per-instance hierarchy scanning, per-instance event-bus allocation, and
 * per-type dynamic class loaders. Component wiring executes through pre-resolved
 * {@link VarHandle}s and {@link MethodHandle}s in deterministic provider order,
 * and internal component events dispatch directly through a type-level flat table
 * without swallowing handler exceptions or requiring {@code Server.getInstance()}.
 *
 * @param <T> composed owner implementation class instantiated by this blueprint
 *
 * @author daoge_cmd
 */
public final class ComponentBlueprint<T extends ComponentClass> {

    /**
     * {@code LOOKUP} anchors {@link MethodHandles#privateLookupIn} calls when
     * resolving private or protected fields, methods, and constructors.
     */
    private static final MethodHandles.Lookup LOOKUP = MethodHandles.lookup();
    /**
     * {@code EVENT_METHOD_TYPE} is the erased handle signature used for all
     * precompiled component event handlers so dispatch avoids varargs boxing.
     */
    private static final MethodType EVENT_METHOD_TYPE = MethodType.methodType(void.class, Object.class, Object.class);
    /**
     * {@code NO_ARG_CTOR_TYPE} is the erased signature for no-argument owner constructors.
     */
    private static final MethodType NO_ARG_CTOR_TYPE = MethodType.methodType(ComponentClass.class);
    /**
     * {@code LIST_CTOR_TYPE} is the erased signature for legacy list-only owner constructors.
     */
    private static final MethodType LIST_CTOR_TYPE = MethodType.methodType(ComponentClass.class, List.class);
    /**
     * {@code INIT_LIST_CTOR_TYPE} is the erased signature for legacy init-info and list constructors.
     */
    private static final MethodType INIT_LIST_CTOR_TYPE = MethodType.methodType(ComponentClass.class, ComponentInitInfo.class, List.class);

    /** Composed owner implementation class built by this blueprint. */
    @Getter
    private final Class<T> ownerClass;
    /** Ordered providers executed during instantiation; base components occupy slot zero when configured first. */
    private final ComponentProvider<? extends Component>[] providers;
    /** Declared component classes aligned by slot index with {@code providers}. */
    private final Class<?>[] componentClasses;
    /** Pre-resolved delegate field assignments on the owning object. */
    private final OwnerFieldBinding[] ownerFields;
    /** Pre-resolved sibling component dependency assignments. */
    private final DependencyBinding[] dependencyBindings;
    /** Pre-resolved manager field assignments inside components. */
    private final ObjectBinding[] managerBindings;
    /** Pre-resolved owner-reference field assignments inside components. */
    private final ObjectBinding[] ownerObjectBindings;
    /** Immutable event class to priority-ordered handler-step lookup table. */
    private final Map<Class<?>, EventStep[]> eventDispatchTable;
    /** Pre-resolved owner constructor handle, or {@code null} for wiring-only blueprints. */
    private final MethodHandle constructorHandle;
    /** Constructor invocation strategy matching {@code constructorHandle}. */
    private final ConstructorMode constructorMode;
    /** Reusable provider list passed to legacy constructors to avoid re-scanning metadata. */
    private final BlueprintProviderList legacyProviderList;

    /**
     * Creates an immutable compiled blueprint from validated bindings and dispatch tables.
     */
    private ComponentBlueprint(
            Class<T> ownerClass,
            ComponentProvider<? extends Component>[] providers,
            Class<?>[] componentClasses,
            OwnerFieldBinding[] ownerFields,
            DependencyBinding[] dependencyBindings,
            ObjectBinding[] managerBindings,
            ObjectBinding[] ownerObjectBindings,
            Map<Class<?>, EventStep[]> eventDispatchTable,
            MethodHandle constructorHandle,
            ConstructorMode constructorMode
    ) {
        this.ownerClass = ownerClass;
        this.providers = providers;
        this.componentClasses = componentClasses;
        this.ownerFields = ownerFields;
        this.dependencyBindings = dependencyBindings;
        this.managerBindings = managerBindings;
        this.ownerObjectBindings = ownerObjectBindings;
        this.eventDispatchTable = eventDispatchTable;
        this.constructorHandle = constructorHandle;
        this.constructorMode = constructorMode;
        this.legacyProviderList = new BlueprintProviderList(this);
    }

    /**
     * Builds an ordered provider list placing the base component at slot zero
     * followed by all remaining configured providers in map iteration order.
     * When {@code providers} already contains an entry for {@code baseKey}, that
     * provider is selected instead of {@code defaultBaseProvider}.
     *
     * @param providers           insertion-ordered component providers keyed by contract class
     * @param baseKey             canonical contract key for the domain's base component
     * @param defaultBaseProvider fallback base provider used when {@code baseKey} is absent
     * @param <P>                 common component supertype
     *
     * @return a fresh list with the base provider at slot zero
     */
    public static <P extends Component> List<ComponentProvider<? extends P>> orderWithBaseFirst(
            Map<Class<?>, ComponentProvider<? extends P>> providers,
            Class<?> baseKey,
            ComponentProvider<? extends P> defaultBaseProvider
    ) {
        List<ComponentProvider<? extends P>> ordered = new ArrayList<>(providers.size() + 1);
        var baseProvider = providers.get(baseKey);
        ordered.add(baseProvider != null ? baseProvider : Objects.requireNonNull(defaultBaseProvider, "defaultBaseProvider"));
        for (var entry : providers.entrySet()) {
            if (entry.getKey() != baseKey) {
                ordered.add(entry.getValue());
            }
        }
        return ordered;
    }

    /**
     * Resolves a concrete {@link ComponentClass} implementation class from either
     * an existing implementation class or a composed API interface. The four
     * domain root interfaces ({@link BlockBehavior}, {@link ItemStack},
     * {@link Entity}, and {@link BlockEntity}) map directly to their shared
     * server base implementations. Other interfaces check the same package for
     * {@code <Name>Impl} using the interface's own class loader first (so plugin
     * interfaces resolve even when a builder defaults to the server class loader)
     * before falling back to {@code classLoader} and Allay's server
     * {@code impl} package.
     *
     * @param interfaceOrImplClass concrete implementation class or composed API interface
     * @param classLoader          fallback class loader used when locating generated implementation classes
     *
     * @return the resolved concrete {@link ComponentClass} subtype
     *
     * @throws ComponentInjectException if no matching concrete implementation class can be loaded
     */
    @SuppressWarnings("unchecked")
    public static <T extends ComponentClass> Class<T> resolveImplClass(Class<?> interfaceOrImplClass, ClassLoader classLoader) {
        Objects.requireNonNull(interfaceOrImplClass, "interfaceOrImplClass");
        if (!interfaceOrImplClass.isInterface()) {
            if (!ComponentClass.class.isAssignableFrom(interfaceOrImplClass)) {
                throw new ComponentInjectException(interfaceOrImplClass.getName() + " does not extend ComponentClass");
            }
            return (Class<T>) interfaceOrImplClass;
        }

        if (interfaceOrImplClass == BlockBehavior.class) {
            return (Class<T>) BlockBehaviorImpl.class;
        }
        if (interfaceOrImplClass == ItemStack.class) {
            return (Class<T>) ItemStackImpl.class;
        }
        if (interfaceOrImplClass == Entity.class) {
            return (Class<T>) EntityImpl.class;
        }
        if (interfaceOrImplClass == BlockEntity.class) {
            return (Class<T>) BlockEntityImpl.class;
        }

        var ifaceLoader = interfaceOrImplClass.getClassLoader();
        var samePackageCandidate = interfaceOrImplClass.getName() + "Impl";
        // Prefer the interface's own class loader first so plugin-defined interfaces
        // resolve their sibling *Impl classes when Allay*Type.Builder.build() uses Allay's loader.
        var resolved = tryLoadComponentClass(samePackageCandidate, ifaceLoader);
        if (resolved != null) {
            return (Class<T>) resolved;
        }
        if (classLoader != null && classLoader != ifaceLoader) {
            resolved = tryLoadComponentClass(samePackageCandidate, classLoader);
            if (resolved != null) {
                return (Class<T>) resolved;
            }
        }

        var name = interfaceOrImplClass.getName();
        var apiPrefix = "org.allaymc.api.";
        var interfacesToken = ".interfaces.";
        if (name.startsWith(apiPrefix) && name.contains(interfacesToken)) {
            var serverCandidate = "org.allaymc.server."
                    + name.substring(apiPrefix.length()).replace(interfacesToken, ".impl.")
                    + "Impl";
            var serverLoader = classLoader != null ? classLoader : ComponentBlueprint.class.getClassLoader();
            resolved = tryLoadComponentClass(serverCandidate, serverLoader);
            if (resolved != null) {
                return (Class<T>) resolved;
            }
        }

        throw new ComponentInjectException("Cannot resolve ComponentClass implementation for interface " + interfaceOrImplClass.getName());
    }

    /**
     * Attempts to load {@code className} from {@code loader} and returns it only
     * when it is assignable to {@link ComponentClass}, or {@code null} otherwise.
     */
    private static Class<?> tryLoadComponentClass(String className, ClassLoader loader) {
        try {
            var resolved = Class.forName(className, false, loader);
            return ComponentClass.class.isAssignableFrom(resolved) ? resolved : null;
        } catch (ClassNotFoundException ignored) {
            return null;
        }
    }

    /**
     * Compiles a full blueprint including owner constructor resolution for
     * instantiation through {@link #newInstance(ComponentInitInfo)}. Validation
     * checks owner delegate fields, component dependencies, owner-reference types,
     * singleton statelessness rules, and event-handler signatures before returning.
     *
     * @param ownerClass    concrete composed owner class
     * @param providers     ordered component providers
     * @param initInfoClass expected initialization-info parameter type, or {@code null} for list-only constructors
     * @param <T>           composed owner type
     *
     * @return the compiled immutable blueprint
     *
     * @throws ComponentInjectException if constructor lookup, component wiring, or handler validation fails
     */
    public static <T extends ComponentClass> ComponentBlueprint<T> compile(
            Class<T> ownerClass,
            List<? extends ComponentProvider<? extends Component>> providers,
            Class<? extends ComponentInitInfo> initInfoClass
    ) {
        var resolvedCtor = resolveConstructor(ownerClass, initInfoClass);
        return compileInternal(ownerClass, providers, resolvedCtor.handle(), resolvedCtor.mode());
    }

    /**
     * Compiles a wiring-only blueprint for direct superclass construction paths
     * that already have an owner instance. Validation rules match
     * {@link #compile(Class, List, Class)} except no owner constructor handle is
     * required.
     *
     * @param ownerClass concrete composed owner class
     * @param providers  ordered component providers
     * @param <T>        composed owner type
     *
     * @return the compiled wiring-only blueprint
     *
     * @throws ComponentInjectException if component wiring or handler validation fails
     */
    static <T extends ComponentClass> ComponentBlueprint<T> compileForWiring(
            Class<T> ownerClass,
            List<? extends ComponentProvider<? extends Component>> providers
    ) {
        return compileInternal(ownerClass, providers, null, ConstructorMode.NONE);
    }

    /**
     * Validates all component bindings, builds pre-resolved handles, and constructs
     * the priority-sorted event dispatch table for {@code ownerClass}.
     */
    @SuppressWarnings("unchecked")
    private static <T extends ComponentClass> ComponentBlueprint<T> compileInternal(
            Class<T> ownerClass,
            List<? extends ComponentProvider<? extends Component>> providerList,
            MethodHandle constructorHandle,
            ConstructorMode constructorMode
    ) {
        Objects.requireNonNull(ownerClass, "ownerClass");
        Objects.requireNonNull(providerList, "providerList");

        int slotCount = providerList.size();
        ComponentProvider<? extends Component>[] providers = new ComponentProvider[slotCount];
        Class<?>[] componentClasses = new Class<?>[slotCount];
        for (int i = 0; i < slotCount; i++) {
            var provider = Objects.requireNonNull(providerList.get(i), "component provider at slot " + i);
            providers[i] = provider;
            componentClasses[i] = Objects.requireNonNull(provider.getComponentClass(), "componentClass at slot " + i);
        }

        // Resolve and validate every Component-typed field on the owner hierarchy.
        // Owner delegate fields for default-only contracts may carry @Dependency(optional = true)
        // so unimplemented marker types still build while concrete providers wire when supplied.
        List<OwnerFieldBinding> ownerFields = new ArrayList<>();
        for (var field : ReflectionUtils.getAllFields(ownerClass)) {
            var type = field.getType();
            if (!Component.class.isAssignableFrom(type)) {
                continue;
            }
            var dep = field.getAnnotation(Dependency.class);
            boolean optional = dep != null && dep.optional();
            int matchedSlot = findAssignableSlot(componentClasses, type, optional, ownerClass.getName(), "component");
            if (matchedSlot >= 0) {
                ownerFields.add(new OwnerFieldBinding(unreflectField(field), matchedSlot));
            }
        }

        List<DependencyBinding> dependencyBindings = new ArrayList<>();
        List<ObjectBinding> managerBindings = new ArrayList<>();
        List<ObjectBinding> ownerObjectBindings = new ArrayList<>();
        Map<Class<?>, List<EventRegistration>> rawEvents = new Object2ObjectOpenHashMap<>();
        int registrationOrder = 0;

        for (int slot = 0; slot < slotCount; slot++) {
            var provider = providers[slot];
            var componentClass = componentClasses[slot];
            var singleton = provider.isSingleton();

            if (singleton && ComponentLifecycle.class.isAssignableFrom(componentClass)) {
                throw new ComponentInjectException(
                        "Singleton component " + componentClass.getName() + " cannot implement ComponentLifecycle"
                );
            }

            for (var field : ReflectionUtils.getAllFields(componentClass)) {
                var dependency = field.getAnnotation(Dependency.class);
                if (dependency != null && Component.class.isAssignableFrom(field.getType())) {
                    if (singleton) {
                        throw new ComponentInjectException(
                                "Singleton component " + componentClass.getName() + " cannot declare @Dependency field " + field.getName()
                        );
                    }
                    int matchedSlot = findAssignableSlot(
                            componentClasses,
                            field.getType(),
                            dependency.optional(),
                            componentClass.getName(),
                            "dependency"
                    );
                    if (matchedSlot >= 0) {
                        dependencyBindings.add(new DependencyBinding(slot, unreflectField(field), matchedSlot));
                    }
                }

                if (field.isAnnotationPresent(Manager.class)) {
                    if (singleton) {
                        throw new ComponentInjectException(
                                "Singleton component " + componentClass.getName() + " cannot declare @Manager field " + field.getName()
                        );
                    }
                    if (!field.getType().isAssignableFrom(ComponentManager.class)) {
                        throw new ComponentInjectException(
                                "Field " + componentClass.getName() + "#" + field.getName() + " annotated with @Manager cannot hold ComponentManager"
                        );
                    }
                    managerBindings.add(new ObjectBinding(slot, unreflectField(field)));
                }

                if (field.isAnnotationPresent(ComponentObject.class)) {
                    if (singleton) {
                        throw new ComponentInjectException(
                                "Singleton component " + componentClass.getName() + " cannot declare @ComponentObject field " + field.getName()
                        );
                    }
                    if (!field.getType().isAssignableFrom(ownerClass)) {
                        throw new ComponentInjectException(
                                "Field " + componentClass.getName() + "#" + field.getName()
                                + " annotated with @ComponentObject requires " + field.getType().getName()
                                + " which is incompatible with owner " + ownerClass.getName()
                        );
                    }
                    ownerObjectBindings.add(new ObjectBinding(slot, unreflectField(field)));
                }
            }

            for (var method : ReflectionUtils.getAllMethods(componentClass)) {
                var annotation = method.getAnnotation(EventHandler.class);
                if (annotation == null) {
                    continue;
                }
                if (annotation.async()) {
                    throw new ComponentInjectException(
                            "Component event handler cannot be async: " + componentClass.getName() + "#" + method.getName()
                    );
                }
                if (method.getReturnType() != void.class) {
                    throw new ComponentInjectException(
                            "Component event handler must return void: " + componentClass.getName() + "#" + method.getName()
                    );
                }
                if (method.getParameterCount() != 1) {
                    throw new ComponentInjectException(
                            "Component event handler must accept exactly one parameter: " + componentClass.getName() + "#" + method.getName()
                    );
                }
                var eventClass = method.getParameterTypes()[0];
                if (!Event.class.isAssignableFrom(eventClass)) {
                    throw new ComponentInjectException(
                            "Component event handler parameter must extend Event: " + componentClass.getName() + "#" + method.getName()
                    );
                }

                var handle = unreflectEventHandler(method);
                rawEvents.computeIfAbsent(eventClass, $ -> new ArrayList<>())
                        .add(new EventRegistration(
                                slot,
                                annotation.priority(),
                                registrationOrder++,
                                handle,
                                componentClass.getName() + "#" + method.getName()
                        ));
            }
        }

        Map<Class<?>, EventStep[]> dispatchTable = new Object2ObjectOpenHashMap<>(rawEvents.size());
        for (var entry : rawEvents.entrySet()) {
            var registrations = entry.getValue();
            // Sort higher priority first and preserve component registration order on ties.
            registrations.sort(Comparator
                    .comparingInt(EventRegistration::priority).reversed()
                    .thenComparingInt(EventRegistration::order));
            var steps = new EventStep[registrations.size()];
            for (int i = 0; i < registrations.size(); i++) {
                var reg = registrations.get(i);
                steps[i] = new EventStep(reg.slot(), reg.handle(), reg.methodDesc());
            }
            dispatchTable.put(entry.getKey(), steps);
        }

        return new ComponentBlueprint<>(
                ownerClass,
                providers,
                componentClasses,
                ownerFields.toArray(OwnerFieldBinding[]::new),
                dependencyBindings.toArray(DependencyBinding[]::new),
                managerBindings.toArray(ObjectBinding[]::new),
                ownerObjectBindings.toArray(ObjectBinding[]::new),
                dispatchTable,
                constructorHandle,
                constructorMode
        );
    }

    /**
     * Finds the single component slot assignable to {@code requiredType}, failing
     * on absence or ambiguity.
     */
    private static int findUniqueAssignableSlot(Class<?>[] componentClasses, Class<?> requiredType, String targetName, String kind) {
        return findAssignableSlot(componentClasses, requiredType, false, targetName, kind);
    }

    /**
     * Finds the component slot assignable to {@code requiredType}, returning
     * {@code -1} only when {@code optional} permits absence and failing on
     * multiple matches regardless of optionality.
     */
    private static int findAssignableSlot(
            Class<?>[] componentClasses,
            Class<?> requiredType,
            boolean optional,
            String targetName,
            String kind
    ) {
        int matchedSlot = -1;
        for (int i = 0; i < componentClasses.length; i++) {
            if (requiredType.isAssignableFrom(componentClasses[i])) {
                if (matchedSlot != -1) {
                    throw new ComponentInjectException("Found multiple " + kind + "s " + requiredType.getName() + " for " + targetName);
                }
                matchedSlot = i;
            }
        }
        if (matchedSlot == -1 && !optional) {
            throw new ComponentInjectException("Cannot find " + kind + " " + requiredType.getName() + " for " + targetName);
        }
        return matchedSlot;
    }

    /**
     * Resolves an owner constructor preferring a no-argument constructor so
     * component wiring runs after subclass construction completes, then falling
     * back to legacy provider-list constructors.
     */
    private static ResolvedConstructor resolveConstructor(Class<?> ownerClass, Class<? extends ComponentInitInfo> initInfoClass) {
        try {
            Constructor<?> noArg = ownerClass.getDeclaredConstructor();
            var lookup = MethodHandles.privateLookupIn(ownerClass, LOOKUP);
            var handle = lookup.unreflectConstructor(noArg).asType(NO_ARG_CTOR_TYPE);
            return new ResolvedConstructor(handle, ConstructorMode.NO_ARG);
        } catch (NoSuchMethodException ignored) {
            // Fall back to legacy constructors that forward ComponentProvider lists to ComponentClass.
        } catch (IllegalAccessException e) {
            throw new ComponentInjectException("Cannot access no-arg constructor of " + ownerClass.getName(), e);
        }

        if (initInfoClass != null) {
            try {
                Constructor<?> ctor = ownerClass.getConstructor(initInfoClass, List.class);
                var lookup = MethodHandles.privateLookupIn(ownerClass, LOOKUP);
                var handle = lookup.unreflectConstructor(ctor).asType(INIT_LIST_CTOR_TYPE);
                return new ResolvedConstructor(handle, ConstructorMode.INIT_INFO_AND_LIST);
            } catch (NoSuchMethodException ignored) {
                // Continue searching for a compatible supertype constructor or list-only constructor.
            } catch (IllegalAccessException e) {
                throw new ComponentInjectException("Cannot access constructor of " + ownerClass.getName(), e);
            }
        }

        for (var ctor : ownerClass.getConstructors()) {
            var params = ctor.getParameterTypes();
            if (params.length == 2 && ComponentInitInfo.class.isAssignableFrom(params[0]) && params[1] == List.class) {
                try {
                    var lookup = MethodHandles.privateLookupIn(ownerClass, LOOKUP);
                    var handle = lookup.unreflectConstructor(ctor).asType(INIT_LIST_CTOR_TYPE);
                    return new ResolvedConstructor(handle, ConstructorMode.INIT_INFO_AND_LIST);
                } catch (IllegalAccessException e) {
                    throw new ComponentInjectException("Cannot access constructor of " + ownerClass.getName(), e);
                }
            }
        }

        try {
            Constructor<?> listCtor = ownerClass.getDeclaredConstructor(List.class);
            var lookup = MethodHandles.privateLookupIn(ownerClass, LOOKUP);
            var handle = lookup.unreflectConstructor(listCtor).asType(LIST_CTOR_TYPE);
            return new ResolvedConstructor(handle, ConstructorMode.LIST_ONLY);
        } catch (NoSuchMethodException | IllegalAccessException e) {
            throw new ComponentInjectException(
                    "Cannot find supported constructor on " + ownerClass.getName()
                    + " (expected no-arg, (ComponentInitInfo, List), or (List))", e
            );
        }
    }

    /**
     * Resolves a mutable {@link VarHandle} for {@code field} through its
     * declaring class, rejecting {@code final} fields at build time so runtime
     * wiring never fails with {@code UnsupportedOperationException}.
     */
    private static VarHandle unreflectField(Field field) {
        if (Modifier.isFinal(field.getModifiers())) {
            throw new ComponentInjectException(
                    "Injected field " + field.getDeclaringClass().getName() + "#" + field.getName() + " cannot be final"
            );
        }
        try {
            var lookup = MethodHandles.privateLookupIn(field.getDeclaringClass(), LOOKUP);
            return lookup.unreflectVarHandle(field);
        } catch (IllegalAccessException e) {
            throw new ComponentInjectException(
                    "Cannot resolve VarHandle for field " + field.getDeclaringClass().getName() + "#" + field.getName(), e
            );
        }
    }

    /**
     * Resolves an erased {@code (Object, Object)void} {@link MethodHandle} for
     * one validated component event handler method.
     */
    private static MethodHandle unreflectEventHandler(Method method) {
        try {
            var lookup = MethodHandles.privateLookupIn(method.getDeclaringClass(), LOOKUP);
            return lookup.unreflect(method).asType(EVENT_METHOD_TYPE);
        } catch (IllegalAccessException e) {
            throw new ComponentInjectException(
                    "Cannot resolve MethodHandle for event handler " + method.getDeclaringClass().getName() + "#" + method.getName(), e
            );
        }
    }

    /**
     * Instantiates and wires a new composed object using this blueprint's
     * precompiled constructor and bindings. For no-argument constructors,
     * subclass construction finishes before field injection and lifecycle hooks
     * execute.
     *
     * @param initInfo initialization payload forwarded to component providers and lifecycle hooks
     *
     * @return the initialized composed object
     */
    @SuppressWarnings("unchecked")
    public T newInstance(ComponentInitInfo initInfo) {
        try {
            return switch (constructorMode) {
                case NO_ARG -> {
                    T instance = (T) (ComponentClass) constructorHandle.invokeExact();
                    initialize(instance, initInfo);
                    yield instance;
                }
                case INIT_INFO_AND_LIST -> (T) (ComponentClass) constructorHandle.invokeExact(initInfo, (List<?>) legacyProviderList);
                case LIST_ONLY -> (T) (ComponentClass) constructorHandle.invokeExact((List<?>) legacyProviderList);
                case NONE -> throw new IllegalStateException("Wiring-only blueprint for " + ownerClass.getName() + " cannot construct instances");
            };
        } catch (RuntimeException | Error e) {
            throw e;
        } catch (Throwable t) {
            throw new ComponentInjectException("Failed to construct composed instance of " + ownerClass.getName(), t);
        }
    }

    /**
     * Constructs all component instances, executes every precompiled field
     * injection, installs the lightweight component manager, and invokes
     * {@link ComponentLifecycle#onInitFinish(ComponentInitInfo)} hooks in slot
     * order.
     *
     * @param instance owning composed object being initialized
     * @param initInfo initialization payload passed to providers and lifecycle hooks
     */
    @SuppressWarnings({"unchecked", "rawtypes"})
    void initialize(ComponentClass instance, ComponentInitInfo initInfo) {
        int slotCount = providers.length;
        Component[] components = new Component[slotCount];
        for (int i = 0; i < slotCount; i++) {
            components[i] = providers[i].provide(initInfo);
        }

        // Install manager and raw component array before invoking lifecycle hooks
        // so hooks can dispatch internal events or query sibling components immediately.
        var manager = new BlueprintComponentManager(this, components);
        instance.setManager(manager);
        instance.setComponents(components);

        for (var binding : ownerFields) {
            binding.handle().set(instance, components[binding.sourceSlot()]);
        }
        for (var binding : dependencyBindings) {
            binding.handle().set(components[binding.targetSlot()], components[binding.sourceSlot()]);
        }
        for (var binding : managerBindings) {
            binding.handle().set(components[binding.targetSlot()], manager);
        }
        for (var binding : ownerObjectBindings) {
            binding.handle().set(components[binding.targetSlot()], instance);
        }

        for (int i = 0; i < slotCount; i++) {
            if (components[i] instanceof ComponentLifecycle lifecycle) {
                lifecycle.onInitFinish(initInfo);
            }
        }
    }

    /**
     * Dispatches {@code event} synchronously to every precompiled handler matching
     * its exact runtime class in descending priority order. Handler unchecked
     * exceptions and errors propagate directly rather than being swallowed.
     *
     * @param components component array owned by the dispatching object
     * @param event      internal component event to dispatch
     * @param <E>        event type
     *
     * @return the same {@code event} reference after handlers complete
     */
    <E extends Event> E dispatchEvent(Component[] components, E event) {
        var steps = eventDispatchTable.get(event.getClass());
        if (steps == null) {
            return event;
        }
        for (var step : steps) {
            try {
                step.handle().invokeExact((Object) components[step.slot()], (Object) event);
            } catch (RuntimeException | Error e) {
                throw e;
            } catch (Throwable t) {
                throw new RuntimeException("Unhandled checked exception in component event handler " + step.methodDesc(), t);
            }
        }
        return event;
    }

    /**
     * Returns whether any component in this blueprint registers an
     * {@link EventHandler} for the exact {@code eventClass}.
     *
     * @param eventClass exact event class to query
     *
     * @return {@code true} if at least one handler listens to {@code eventClass}
     */
    public boolean hasEventHandler(Class<? extends Event> eventClass) {
        return eventDispatchTable.containsKey(eventClass);
    }

    /**
     * {@code ConstructorMode} distinguishes the constructor invocation protocol
     * resolved for a composed owner class.
     */
    private enum ConstructorMode {
        /** Owner exposes a no-argument constructor; wiring runs after construction finishes. */
        NO_ARG,
        /** Owner exposes a legacy {@code (ComponentInitInfo, List)} constructor. */
        INIT_INFO_AND_LIST,
        /** Owner exposes a legacy {@code (List)} constructor without init info. */
        LIST_ONLY,
        /** Wiring-only blueprint without owner instantiation support. */
        NONE
    }

    /**
     * {@code ResolvedConstructor} pairs a resolved constructor handle with its
     * invocation strategy.
     *
     * @param handle erased constructor handle
     * @param mode   invocation protocol matching {@code handle}
     */
    private record ResolvedConstructor(MethodHandle handle, ConstructorMode mode) {
    }

    /**
     * {@code OwnerFieldBinding} maps one owner delegate field to the component
     * slot supplying its runtime value.
     *
     * @param handle     pre-resolved field handle on the owner hierarchy
     * @param sourceSlot component array index providing the value
     */
    private record OwnerFieldBinding(VarHandle handle, int sourceSlot) {
    }

    /**
     * {@code DependencyBinding} maps one component's {@link Dependency} field to
     * its target sibling slot.
     *
     * @param targetSlot component slot receiving the dependency
     * @param handle     pre-resolved dependency field handle
     * @param sourceSlot component slot supplying the dependency
     */
    private record DependencyBinding(int targetSlot, VarHandle handle, int sourceSlot) {
    }

    /**
     * {@code ObjectBinding} maps a component's {@link Manager} or
     * {@link ComponentObject} field to its owning slot.
     *
     * @param targetSlot component slot receiving the injected reference
     * @param handle     pre-resolved field handle
     */
    private record ObjectBinding(int targetSlot, VarHandle handle) {
    }

    /**
     * {@code EventRegistration} holds intermediate event-handler metadata during
     * priority sorting.
     *
     * @param slot       component slot owning the handler method
     * @param priority   declared {@link EventHandler#priority()} value
     * @param order      monotonic discovery index breaking priority ties deterministically
     * @param handle     erased {@code (Object, Object)void} method handle
     * @param methodDesc diagnostic symbol description
     */
    private record EventRegistration(int slot, int priority, int order, MethodHandle handle, String methodDesc) {
    }

    /**
     * {@code EventStep} is one compiled dispatch entry executed during
     * {@link #dispatchEvent(Component[], Event)}.
     *
     * @param slot       component slot providing the receiver instance
     * @param handle     erased {@code (Object, Object)void} method handle
     * @param methodDesc diagnostic symbol description for error reporting
     */
    private record EventStep(int slot, MethodHandle handle, String methodDesc) {
    }

    /**
     * {@code BlueprintComponentManager} implements {@link ComponentManager}
     * without allocating a per-instance event bus by delegating directly to the
     * shared type-level {@link ComponentBlueprint}.
     */
    private static final class BlueprintComponentManager implements ComponentManager {
        /** Shared immutable blueprint owning the precompiled dispatch table. */
        private final ComponentBlueprint<?> blueprint;
        /** Owner's live component instances indexed by blueprint slot. */
        private final Component[] components;

        /**
         * Creates a lightweight manager bound to one composed object's blueprint
         * and component array.
         */
        private BlueprintComponentManager(ComponentBlueprint<?> blueprint, Component[] components) {
            this.blueprint = blueprint;
            this.components = components;
        }

        /**
         * Dispatches {@code event} through the shared blueprint dispatch table for
         * this owner's components.
         */
        @Override
        public <E extends Event> E callEvent(E event) {
            return blueprint.dispatchEvent(components, event);
        }
    }

    /**
     * {@code BlueprintProviderList} wraps a precompiled blueprint inside a
     * read-only {@link List} view so legacy constructors forwarding provider lists
     * to {@link ComponentClass} bypass blueprint lookup and reflection scanning.
     */
    static final class BlueprintProviderList extends AbstractList<ComponentProvider<? extends Component>> {
        /** Precompiled blueprint carried through legacy constructor chains. */
        private final ComponentBlueprint<?> blueprint;

        /**
         * Creates a list view backed by {@code blueprint}'s provider array.
         */
        private BlueprintProviderList(ComponentBlueprint<?> blueprint) {
            this.blueprint = blueprint;
        }

        /**
         * Returns the precompiled blueprint wrapped by this list.
         */
        ComponentBlueprint<?> blueprint() {
            return blueprint;
        }

        /**
         * Returns the provider at slot {@code index}.
         */
        @Override
        public ComponentProvider<? extends Component> get(int index) {
            return blueprint.providers[index];
        }

        /**
         * Returns the number of configured component providers.
         */
        @Override
        public int size() {
            return blueprint.providers.length;
        }
    }
}
