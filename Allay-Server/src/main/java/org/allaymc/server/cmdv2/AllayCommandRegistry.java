package org.allaymc.server.cmdv2;

import org.allaymc.api.cmdv2.Command;
import org.allaymc.api.cmdv2.CommandRegistry;
import org.allaymc.api.registry.SimpleMappedRegistry;

import java.util.HashMap;
import java.util.Map;

/**
 * Allay Project 2023/12/29
 *
 * @author daoge_cmd
 */
public class AllayCommandRegistry extends SimpleMappedRegistry<String, Command, Map<String, Command>> implements CommandRegistry {

    public AllayCommandRegistry() {
        super(null, i -> new HashMap<>());
    }

    public void init() {
        // TODO
    }
}
