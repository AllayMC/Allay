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

    /**
     * 当插件被读取时调用
     */
    public void onLoad() {}

    /**
     * 当插件被启用时调用
     */
    public void onEnable() {}

    /**
     * 当插件被关闭时调用
     */
    public void onDisable() {}

    /**
     * @return 插件是否可以被重新加载
     */
    public boolean isReloadable() {
        return false;
    }

    /**
     * 重新读取插件并加载
     * 与单纯的调用onEnable()/onDisable()不同
     * 此方法的默认实现会调用onLoad()方法以模拟插件从被读取到被启用的全过程
     * <p>
     * 我们不允许覆写此方法，因为对于一个代码质量良好的插件来说，不应该有覆写此方法的需求
     */
    public final void reload() {
        if (!isReloadable()) {
            throw new UnsupportedOperationException("This plugin is not a reloadable plugin!");
        }
        onDisable();
        onLoad();
        onEnable();
    }
}
