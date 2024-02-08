package org.allaymc.exampleplugin;

import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.plugin.Plugin;

/**
 * Allay Project 2024/2/8
 *
 * @author daoge_cmd
 */
@Slf4j
public class ExamplePlugin extends Plugin {
    @Override
    public void onLoad() {
        log.info("ExamplePlugin loaded!");
    }

    @Override
    public void onEnable() {
        log.info("ExamplePlugin enabled!");
    }

    @Override
    public void onDisable() {
        log.info("ExamplePlugin disabled!");
    }
}
