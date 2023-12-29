package org.allaymc.api.cmdv2;

import org.allaymc.api.ApiInstanceHolder;
import org.allaymc.api.registry.MappedRegistry;

import java.util.Map;

/**
 * Allay Project 2023/12/29
 *
 * @author daoge_cmd
 */
public interface CommandRegistry extends MappedRegistry<String, Command, Map<String, Command>> {

    ApiInstanceHolder<CommandRegistry> REGISTRY = ApiInstanceHolder.of();

    static CommandRegistry getRegistry() {
        return REGISTRY.get();
    }
}
