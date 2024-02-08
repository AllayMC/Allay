package org.allaymc.api.plugin;

import lombok.Getter;
import lombok.Setter;

/**
 * Allay Project 2024/2/8
 *
 * @author daoge_cmd
 */
@Setter
@Getter
public abstract class Plugin {

    protected PluginContainer pluginContainer;

    public void onLoad() {}

    public void onEnable() {}

    public void onDisable() {}
}
