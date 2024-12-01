package org.allaymc.server.command;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Delegate;
import org.allaymc.api.command.CommandSender;
import org.allaymc.api.math.location.Location3fc;

/**
 * @author daoge_cmd
 */
public class ProxyCommandSender implements CommandSender {

    @Delegate
    @Getter
    protected CommandSender origin;
    @Setter
    protected Location3fc cmdExecuteLocation;

    public ProxyCommandSender(CommandSender origin) {
        this.origin = origin;
    }

    @Override
    public Location3fc getCmdExecuteLocation() {
        if (cmdExecuteLocation != null) {
            return cmdExecuteLocation;
        }
        return origin.getCmdExecuteLocation();
    }
}
