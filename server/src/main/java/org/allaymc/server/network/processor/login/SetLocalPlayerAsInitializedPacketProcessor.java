package org.allaymc.server.network.processor.login;

import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.eventbus.event.player.PlayerJoinEvent;
import org.allaymc.api.message.TrKeys;
import org.allaymc.api.player.ClientState;
import org.allaymc.server.entity.component.player.EntityPlayerBaseComponentImpl;
import org.allaymc.server.entity.component.player.EntityPlayerClientComponentImpl;
import org.allaymc.server.entity.impl.EntityPlayerImpl;
import org.allaymc.server.network.processor.ingame.ILoginPacketProcessor;
import org.allaymc.server.world.AllayDimension;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacketType;
import org.cloudburstmc.protocol.bedrock.packet.SetLocalPlayerAsInitializedPacket;

/**
 * @author daoge_cmd
 */
public class SetLocalPlayerAsInitializedPacketProcessor extends ILoginPacketProcessor<SetLocalPlayerAsInitializedPacket> {
    @Override
    public void handle(EntityPlayer player, SetLocalPlayerAsInitializedPacket packet) {
        var event = new PlayerJoinEvent(player);
        if (!event.call()) {
            player.disconnect(TrKeys.MC_DISCONNECTIONSCREEN_NOREASON);
            return;
        }

        ((EntityPlayerClientComponentImpl) ((EntityPlayerImpl) player).getPlayerClientComponent()).setClientStatus(ClientState.IN_GAME);
        // We only accept player's movement inputs, which are after SetLocalPlayerAsInitializedPacket,
        // So after player sent SetLocalPlayerAsInitializedPacket, we need to sync the pos with client
        // Otherwise the client will snap into the ground
        ((EntityPlayerBaseComponentImpl) ((EntityPlayerImpl) player).getBaseComponent()).sendLocationToSelf();
        // Send debug shapes to the player after player fully joined
        ((AllayDimension) player.getDimension()).addDebugShapesTo(player);
    }

    @Override
    public BedrockPacketType getPacketType() {
        return BedrockPacketType.SET_LOCAL_PLAYER_AS_INITIALIZED;
    }
}
