package org.allaymc.server.command;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Delegate;
import org.allaymc.api.command.CommandSender;
import org.allaymc.api.math.location.Location3dc;

/**
 * @author daoge_cmd
 */
public class ProxyCommandSender implements CommandSender {

    @Delegate
    @Getter
    protected CommandSender origin;
    @Setter
    protected Location3dc cmdExecuteLocation;

    public ProxyCommandSender(CommandSender origin) {
        this.origin = origin;
    }

    @Override
    public Location3dc getCmdExecuteLocation() {
        if (cmdExecuteLocation != null) {
            return cmdExecuteLocation;
        }
        return origin.getCmdExecuteLocation();
    }
}
