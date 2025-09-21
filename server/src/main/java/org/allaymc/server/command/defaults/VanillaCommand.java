package org.allaymc.server.command.defaults;

import org.allaymc.api.command.Command;
import org.allaymc.api.permission.Permission;

/**
 * @author IWareQ
 */
public abstract class VanillaCommand extends Command {
    public static final String ALLAY_BUILD_IN_COMMAND_PERMISSION_PREFIX = "allay.command.";

    public VanillaCommand(String name, String description) {
        super(name, description);
        this.permissions.clear();
        this.permissions.add(Permission.createForCommand(name, ALLAY_BUILD_IN_COMMAND_PERMISSION_PREFIX + name));
    }
}
