package org.allaymc.api.command;

import lombok.Getter;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.interfaces.EntityNpc;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.i18n.TrContainer;
import org.allaymc.api.math.location.Location3dc;
import org.allaymc.api.permission.PermissionGroup;
import org.cloudburstmc.protocol.bedrock.data.command.CommandOriginData;
import org.cloudburstmc.protocol.bedrock.data.command.CommandOriginType;

import java.util.UUID;

/**
 * @author daoge_cmd
 */
@Getter
public class NPCCommandSender implements CommandSender {

    private static final CommandOriginData NPC_COMMAND_ORIGIN_DATA = new CommandOriginData(CommandOriginType.ENTITY, UUID.randomUUID(), "", 0);

    protected EntityNpc npc;
    protected EntityPlayer initiator;

    @Override
    public String getCommandSenderName() {
        return npc.getDisplayName();
    }

    @Override
    public CommandOriginData getCommandOriginData() {
        return NPC_COMMAND_ORIGIN_DATA;
    }

    @Override
    public Location3dc getCmdExecuteLocation() {
        return npc.getLocation();
    }

    @Override
    public void sendText(String text) {
        // Do nothing
    }

    @Override
    public void sendTr(String key, boolean forceTranslatedByClient, Object... args) {
        // Do nothing
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
