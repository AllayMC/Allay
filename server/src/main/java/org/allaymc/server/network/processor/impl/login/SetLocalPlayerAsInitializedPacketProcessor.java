package org.allaymc.server.network.processor.impl.login;

import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.eventbus.event.player.PlayerJoinEvent;
import org.allaymc.api.i18n.TrKeys;
import org.allaymc.api.network.ClientStatus;
import org.allaymc.server.entity.component.player.EntityPlayerBaseComponentImpl;
import org.allaymc.server.entity.component.player.EntityPlayerNetworkComponentImpl;
import org.allaymc.server.entity.impl.EntityPlayerImpl;
import org.allaymc.server.network.processor.impl.ingame.ILoginPacketProcessor;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacketType;
import org.cloudburstmc.protocol.bedrock.packet.SetLocalPlayerAsInitializedPacket;

/**
 * @author daoge_cmd
 */
public class SetLocalPlayerAsInitializedPacketProcessor extends ILoginPacketProcessor<SetLocalPlayerAsInitializedPacket> {
    @Override
    public void handle(EntityPlayer player, SetLocalPlayerAsInitializedPacket packet) {
        var event = new PlayerJoinEvent(player);
        event.call();
        if (event.isCancelled()) player.disconnect(TrKeys.M_DISCONNECTIONSCREEN_NOREASON);
        ((EntityPlayerNetworkComponentImpl) ((EntityPlayerImpl) player).getPlayerNetworkComponent()).getPacketProcessorHolder().setClientStatus(ClientStatus.IN_GAME);
        // We only accept player's movement inputs, which are after SetLocalPlayerAsInitializedPacket,
        // So after player sent SetLocalPlayerAsInitializedPacket, we need to sync the pos with client
        // Otherwise the client will snap into the ground
        ((EntityPlayerBaseComponentImpl) ((EntityPlayerImpl) player).getBaseComponent()).sendLocationToSelf();
    }

    @Override
    public BedrockPacketType getPacketType() {
        return BedrockPacketType.SET_LOCAL_PLAYER_AS_INITIALIZED;
    }
}
