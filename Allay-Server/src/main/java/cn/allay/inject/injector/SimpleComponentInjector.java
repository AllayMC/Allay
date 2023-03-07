package cn.allay.inject.injector;

import cn.allay.inject.annotation.Dependency;
import cn.allay.inject.annotation.Impl;
import cn.allay.inject.annotation.Inject;
import cn.allay.inject.exception.ComponentInjectException;
import cn.allay.inject.interfaces.ComponentGroup;
import cn.allay.inject.interfaces.ComponentImpl;
import cn.allay.inject.interfaces.ComponentInjector;
import cn.allay.inject.interfaces.RuntimeComponentObject;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.implementation.FixedValue;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.matcher.ElementMatchers;

import java.util.*;

/**
 * The default injector which use byte-buddy
 */
public class SimpleComponentInjector<T> implements ComponentInjector<T> {

    protected Class<T> parentClass;
    protected List<ComponentImpl> components = new ArrayList<>();

    public SimpleComponentInjector(ComponentGroup<T> componentGroup) {
        loadComponentGroup(componentGroup);
    }

    @Override
    public ComponentInjector<T> parentClass(Class<T> parentClass) {
        Objects.requireNonNull(parentClass, "The parent class cannot be null");
        this.parentClass = parentClass;
        return this;
    }

    @Override
    public ComponentInjector<T> withComponent(List<ComponentImpl> components) {
        Objects.requireNonNull(components, "The components cannot be null");
        this.components.addAll(components);
        return this;
    }

    @Override
    public ComponentInjector<T> loadComponentGroup(ComponentGroup<T> componentGroup) {
        Objects.requireNonNull(componentGroup.getParentClass(), "The parent class cannot be null");
        Objects.requireNonNull(componentGroup.getComponents(), "The components cannot be null");
        this.components = componentGroup.getComponents();
        this.parentClass = componentGroup.getParentClass();
        return this;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Class<T> inject() {
        checkComponentValid();
        injectDependency();
        var bb = new ByteBuddy()
                .subclass(parentClass);
        var reversed = new ArrayList<>(components);
        //byte-buddy will give priority to matching proxy methods declared late
        //So we need to reverse the list of components to ensure that the method of the component declared first will override the method declared later
        Collections.reverse(reversed);
        for (var component : reversed) {
            for (var method : Arrays.stream(component.getClass().getMethods()).filter(method -> method.isAnnotationPresent(Impl.class)).toList()) {
                bb = bb.method(ElementMatchers.named(method.getName())
                                .and(ElementMatchers.takesArguments(method.getParameterTypes()))
                                .and(ElementMatchers.isAnnotatedWith(Inject.class)))
                        .intercept(MethodDelegation.to(component));
            }
        }
        bb = bb.implement(RuntimeComponentObject.class)
                .method(ElementMatchers.named("getComponents"))
                .intercept(FixedValue.value(Collections.unmodifiableList(components)));
        return (Class<T>) bb.make()
                .load(getClass().getClassLoader())
                .getLoaded();
    }

    protected void checkComponentValid() {
        Set<String> identifiers = new HashSet<>();
        for (var component : components) {
            var identifier = component.getNamespaceId();
            if (identifiers.contains(identifier))
                throw new ComponentInjectException("Duplicate component " + component.getNamespaceId());
            else
                identifiers.add(identifier);
        }
    }

    protected void injectDependency() {
        for (var component : components) {
            for (var field : component.getClass().getDeclaredFields()) {
                var annotation = field.getAnnotation(Dependency.class);
                if (annotation != null) {
                    var type = field.getType();
                    List<ComponentImpl> dependencies = new ArrayList<>(components);
                    var count = Integer.MAX_VALUE;
                    var requireCompId = annotation.namespaceId();
                    //Try to find dependencies through inheritance
                    //Try to match by namespace ID
                    if (!requireCompId.isBlank())
                        dependencies = dependencies.stream().filter(dependency -> dependency.getNamespaceId().equals(requireCompId)).toList();
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
