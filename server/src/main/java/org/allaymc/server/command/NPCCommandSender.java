package org.allaymc.server.command;

import lombok.Getter;
import org.allaymc.api.command.CommandSender;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.interfaces.EntityNpc;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.i18n.TrContainer;
import org.allaymc.api.math.location.Location3dc;
import org.allaymc.api.permission.PermissionGroup;

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
    public void sendText(String text) {
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
    public PermissionGroup getPermissionGroup() {
        return npc.getPermissionGroup();
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
