package org.allaymc.server.network.processor.login;

import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.eventbus.event.player.PlayerJoinEvent;
import org.allaymc.api.i18n.TrKeys;
import org.allaymc.server.entity.component.player.EntityPlayerNetworkComponentImpl;
import org.allaymc.server.network.processor.ILoginPacketProcessor;
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
        // We only accept player's movement inputs, which are after SetLocalPlayerAsInitializedPacket,
        // So after player sent SetLocalPlayerAsInitializedPacket, we need to sync the pos with client
        // Otherwise the client will snap into the ground
        player.sendLocationToSelf();
        player.getManager().<EntityPlayerNetworkComponentImpl>getComponent(EntityPlayerNetworkComponentImpl.IDENTIFIER).setInitialized();
    }

    @Override
    public BedrockPacketType getPacketType() {
        return BedrockPacketType.SET_LOCAL_PLAYER_AS_INITIALIZED;
    }
}
