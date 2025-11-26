package org.allaymc.server.command;

import lombok.Getter;
import org.allaymc.api.command.CommandSender;
import org.allaymc.api.math.location.Location3d;
import org.allaymc.api.math.location.Location3dc;
import org.allaymc.api.message.TrContainer;
import org.allaymc.api.permission.ConstantPermissionCalculator;
import org.allaymc.api.permission.PermissionCalculator;
import org.allaymc.api.permission.Tristate;

import java.util.List;

/**
 * @author daoge_cmd
 */
public final class MockCommandSender implements CommandSender {

    @Getter
    private final Location3d fakeLocation = new Location3d(0, 0, 0, null);

    @Override
    public String getCommandSenderName() {
        return "";
    }

    @Override
    public Location3dc getCommandExecuteLocation() {
        return this.fakeLocation;
    }

    @Override
    public void sendMessage(String message) {
        // no-op
    }

    @Override
    public void sendTranslatable(String translatable, Object... args) {
        // no-op
    }

    @Override
    public void sendCommandOutputs(CommandSender sender, int status, List<String> permissions, TrContainer... outputs) {
        // no-op
    }

    @Override
    public PermissionCalculator getPermissionCalculator() {
        return new ConstantPermissionCalculator(Tristate.TRUE);
    }

    @Override
    public void setPermissionCalculator(PermissionCalculator calculator) {
        // no-op
    }
}
