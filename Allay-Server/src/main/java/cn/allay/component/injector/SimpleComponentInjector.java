package cn.allay.component.injector;

import cn.allay.component.annotation.Dependency;
import cn.allay.component.annotation.Impl;
import cn.allay.component.annotation.Inject;
import cn.allay.component.exception.ComponentInjectException;
import cn.allay.component.interfaces.ComponentImpl;
import cn.allay.component.interfaces.ComponentInjector;
import cn.allay.component.interfaces.ComponentedObject;
import cn.allay.identifier.Identifier;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.implementation.FixedValue;
import net.bytebuddy.implementation.MethodDelegation;

import java.util.*;

import static net.bytebuddy.matcher.ElementMatchers.*;

/**
 * Author: daoge_cmd <br>
 * Date: 2023/3/4 <br>
 * Allay Project <br>
 * <p>
 * The default injector which use byte-buddy
 */
public class SimpleComponentInjector<T> implements ComponentInjector<T> {

    protected Class<T> parentClass;
    protected List<ComponentImpl> components = new ArrayList<>();

    public SimpleComponentInjector() {}

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
                bb = bb.method(named(method.getName())
                                .and(takesArguments(method.getParameterTypes()))
                                .and(isAnnotatedWith(Inject.class)))
                        .intercept(MethodDelegation.to(component));
            }
        }
        bb = bb.implement(ComponentedObject.class)
                .method(named("getComponents"))
                .intercept(FixedValue.value(Collections.unmodifiableList(components)));
        return (Class<T>) bb.make()
                .load(getClass().getClassLoader())
                .getLoaded();
    }

    protected void checkComponentValid() {
        Set<Identifier> identifiers = new HashSet<>();
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
            for (var field : component.getClass().getFields()) {
                var annotation = field.getAnnotation(Dependency.class);
                if (annotation != null) {
                    var type = field.getType();
                    List<ComponentImpl> dependencies = new ArrayList<>(components);
                    var count = Integer.MAX_VALUE;
                    var requireCompId = annotation.namespaceId();
                    //Try to find dependencies through inheritance
                    //Try to match by namespace ID
                    if (!requireCompId.isBlank())
                        dependencies = dependencies.stream().filter(dependency -> dependency.getNamespaceId().toString().equals(requireCompId)).toList();
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
