package org.allaymc.api.plugin;

/**
 * Allay Project 2024/2/8
 *
 * @author daoge_cmd
 */
public interface Plugin {

    default void onLoad() {}

    default void onEnable() {}

    default void onDisable() {}
}
