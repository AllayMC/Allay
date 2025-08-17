package org.allaymc.server.command.defaults;

import org.allaymc.api.command.SimpleCommand;
import org.allaymc.api.permission.Permission;

import java.util.List;

/**
 * @author IWareQ
 */
public abstract class VanillaCommand extends SimpleCommand {
    public static final String ALLAY_BUILD_IN_COMMAND_PERMISSION_PREFIX = "allay.command.";

    public VanillaCommand(String name, String description) {
        super(name, description, List.of(Permission.createForCommand(name, ALLAY_BUILD_IN_COMMAND_PERMISSION_PREFIX + name)));
    }
}
