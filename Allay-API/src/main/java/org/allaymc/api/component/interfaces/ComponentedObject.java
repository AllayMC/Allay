package org.allaymc.api.component.interfaces;

import java.util.List;

/**
 * The object which has been built by {@link ComponentInjector}.
 * <p>
 * Any class spawned from {@link ComponentInjector} will implement this interface and can be used for judgment.
 * <p>
 * Allay Project 2023/3/4
 *
 * @author daoge_cmd
 */
public interface ComponentedObject {
    /**
     * @return Components injected into this object
     */
    List<Component> getComponents();
}
