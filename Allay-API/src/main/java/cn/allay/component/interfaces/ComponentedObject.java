package cn.allay.component.interfaces;

import java.util.List;

/**
 * Author: daoge_cmd <br>
 * Date: 2023/3/4 <br>
 * Allay Project <br>
 * <p>
 * The object which has been built by {@link ComponentInjector}
 */
public interface ComponentedObject {
    /**
     * @return Components provided when constructing the object
     */
    List<ComponentImpl> getComponents();
}
