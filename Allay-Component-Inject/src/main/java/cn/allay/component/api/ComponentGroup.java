package cn.allay.component.api;

import java.util.List;

/**
 * Author: daoge_cmd <br>
 * Date: 2023/3/4 <br>
 * Allay Project <br>
 *
 * Describe a group of components <p/>
 * And can be built into object using {@link cn.allay.component.api.ComponentInjector}
 */
public interface ComponentGroup<T> {
    /**
     * @return the parent class
     */
    Class<T> getParentClass();

    /**
     * @return the components
     */
    List<Object> getComponents();
}
