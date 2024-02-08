package org.allaymc.server.plugin;

import lombok.Builder;
import org.allaymc.api.plugin.Plugin;

/**
 * Allay Project 2024/2/8
 *
 * @author daoge_cmd
 */
@Builder
public class FakePlugin implements Plugin {

    @Builder.Default
    protected Runnable onLoad = () -> {};
    @Builder.Default
    protected Runnable onEnable = () -> {};
    @Builder.Default
    protected Runnable onDisable = () -> {};

    @Override
    public void onLoad() {
        onLoad.run();
    }

    @Override
    public void onEnable() {
        onEnable.run();
    }

    @Override
    public void onDisable() {
        onDisable.run();
    }
}
