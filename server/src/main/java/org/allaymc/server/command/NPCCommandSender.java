package org.allaymc.server.command;

import lombok.Getter;
import org.allaymc.api.command.CommandSender;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.interfaces.EntityNpc;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.math.location.Location3dc;
import org.allaymc.api.message.TrContainer;
import org.allaymc.api.permission.PermissionCalculator;

/**
 * @author daoge_cmd
 */
@Getter
public class NPCCommandSender implements CommandSender {

    protected EntityNpc npc;
    protected EntityPlayer initiator;

    @Override
    public String getCommandSenderName() {
        return npc.getDisplayName();
    }

    @Override
    public Location3dc getCommandExecuteLocation() {
        return npc.getLocation();
    }

    @Override
    public void sendMessage(String message) {
        // Do nothing
    }

    @Override
    public void sendTranslatable(String translatable, Object... args) {

    }

    @Override
    public void sendCommandOutputs(CommandSender sender, int status, TrContainer... outputs) {
        // Do nothing
    }

    @Override
    public PermissionCalculator getPermissionCalculator() {
        return npc.getPermissionCalculator();
    }

    @Override
    public void setPermissionCalculator(PermissionCalculator calculator) {
        npc.setPermissionCalculator(calculator);
    }

    @Override
    public boolean isEntity() {
        return true;
    }

    @Override
    public Entity asEntity() {
        return npc;
    }
}
