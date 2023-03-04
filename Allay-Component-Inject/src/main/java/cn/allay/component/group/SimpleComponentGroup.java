package cn.allay.component.group;

import cn.allay.component.api.ComponentGroup;

import java.util.List;
import java.util.Objects;

/**
 * Author: daoge_cmd <br>
 * Date: 2023/3/4 <br>
 * Allay Project <br>
 */
public class SimpleComponentGroup<T> implements ComponentGroup<T> {

    protected Class<T> parentClass;
    protected List<Object> components;

    public SimpleComponentGroup(Class<T> parentClass, Object... components) {
        this(parentClass, List.of(components));
    }

    public SimpleComponentGroup(Class<T> parentClass, List<Object> components) {
        Objects.requireNonNull(parentClass, "The parent class cannot be null in component group");
        Objects.requireNonNull(components, "The components cannot be null in component group");
        components.forEach(component -> Objects.requireNonNull(component, "The component cannot be null in component group"));
        this.parentClass = parentClass;
        this.components = components;
    }

    @Override
    public Class<T> getParentClass() {
        return parentClass;
    }

    @Override
    public List<Object> getComponents() {
        return components;
    }
}
