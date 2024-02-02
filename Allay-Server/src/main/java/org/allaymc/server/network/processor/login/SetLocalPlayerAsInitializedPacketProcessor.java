package org.allaymc.server.network.processor.login;

import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.server.Server;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacketType;
import org.cloudburstmc.protocol.bedrock.packet.SetLocalPlayerAsInitializedPacket;

/**
 * Allay Project 2024/2/2
 *
 * @author daoge_cmd
 */
public class SetLocalPlayerAsInitializedPacketProcessor extends ILoginPacketProcessor<SetLocalPlayerAsInitializedPacket> {

    @Override
    public BedrockPacketType getPacketType() {
        return BedrockPacketType.SET_LOCAL_PLAYER_AS_INITIALIZED;
    }

    @Override
    public void handle(EntityPlayer player, SetLocalPlayerAsInitializedPacket packet) {
        // We only accept player's movement inputs which are after SetLocalPlayerAsInitializedPacket
        // So after player sent SetLocalPlayerAsInitializedPacket, we need to sync the pos with client
        // Otherwise the client will snap into the ground
        player.sendLocationToSelf();
        player.setInitialized();
        Server.getInstance().broadcastTr("§e%minecraft:multiplayer.player.joined", player.getOriginName());
    }
}
