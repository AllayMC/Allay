package org.allaymc.api.component.interfaces;

/**
 * Allay Project 2023/4/21
 *
 * @author daoge_cmd
 */
public interface ComponentInitInfo {

    ComponentInitInfo EMPTY = new Empty();

    class Empty implements ComponentInitInfo {}
}
