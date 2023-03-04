package cn.allay.component.injector;

import cn.allay.component.annotation.ComponentDependency;
import cn.allay.component.annotation.ComponentImpl;
import cn.allay.component.annotation.ComponentInject;
import cn.allay.component.api.ComponentGroup;
import cn.allay.component.api.ComponentInjector;
import cn.allay.component.api.RuntimeComponentObject;
import cn.allay.component.exception.ComponentInjectException;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.implementation.FixedValue;
import net.bytebuddy.implementation.MethodDelegation;

import java.util.*;

import static net.bytebuddy.matcher.ElementMatchers.*;

/**
 * Author: daoge_cmd <br>
 * Date: 2023/3/4 <br>
 * Allay Project <br>
 *
 * The default injector which use byte-buddy
 */
public class SimpleComponentInjector<T> implements ComponentInjector<T> {

    protected Class<T> parentClass;
    protected List<Object> components = new ArrayList<>();

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
    public ComponentInjector<T> withComponent(List<?> components) {
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
        injectDependency();
        var bb = new ByteBuddy()
                .subclass(parentClass);
        var reversed = new ArrayList<>(components);
        //byte-buddy will give priority to matching proxy methods declared late
        //So we need to reverse the list of components to ensure that the method of the component declared first will override the method declared later
        Collections.reverse(reversed);
        for (var component : reversed) {
            for (var method : Arrays.stream(component.getClass().getMethods()).filter(method -> method.isAnnotationPresent(ComponentImpl.class)).toList()) {
                bb = bb.method(named(method.getName())
                                .and(takesArguments(method.getParameterTypes()))
                                .and(isAnnotatedWith(ComponentInject.class)))
                        .intercept(MethodDelegation.to(component));
            }
        }
        bb = bb.implement(RuntimeComponentObject.class)
                .method(named("getComponents"))
                .intercept(FixedValue.value(Collections.unmodifiableList(components)));
        return (Class<T>) bb.make()
                .load(getClass().getClassLoader())
                .getLoaded();
    }

    protected void injectDependency() {
        for (var component : components) {
            for (var field : component.getClass().getDeclaredFields()) {
                if (field.isAnnotationPresent(ComponentDependency.class)) {
                    var type = field.getType();
                    var dependencies = components.stream().filter(type::isInstance).toList();
                    var count = dependencies.size();
                    if (count == 0)
                        throw new ComponentInjectException("Cannot find dependency " + type.getName() + " for " + component.getClass().getName());
                    if (count > 1)
                        throw new ComponentInjectException("Found multiple dependencies " + type.getName() + " for " + component.getClass().getName());
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
