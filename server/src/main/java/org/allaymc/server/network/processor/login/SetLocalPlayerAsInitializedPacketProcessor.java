package org.allaymc.server.network.processor.login;

import org.allaymc.api.eventbus.event.server.PlayerJoinEvent;
import org.allaymc.api.message.TrKeys;
import org.allaymc.api.player.ClientState;
import org.allaymc.api.player.Player;
import org.allaymc.server.entity.component.EntityBaseComponentImpl;
import org.allaymc.server.entity.impl.EntityPlayerImpl;
import org.allaymc.server.network.processor.ingame.ILoginPacketProcessor;
import org.allaymc.server.player.AllayPlayer;
import org.allaymc.server.world.AllayDimension;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacketType;
import org.cloudburstmc.protocol.bedrock.packet.SetLocalPlayerAsInitializedPacket;

/**
 * @author daoge_cmd
 */
public class SetLocalPlayerAsInitializedPacketProcessor extends ILoginPacketProcessor<SetLocalPlayerAsInitializedPacket> {
    @Override
    public void handle(Player player, SetLocalPlayerAsInitializedPacket packet) {
        var allayPlayer = (AllayPlayer) player;
        var event = new PlayerJoinEvent(player);
        if (!event.call()) {
            player.disconnect(TrKeys.MC_DISCONNECTIONSCREEN_NOREASON);
            return;
        }

        allayPlayer.setClientState(ClientState.IN_GAME);
        var entity = player.getControlledEntity();
        var baseComponent = (EntityBaseComponentImpl) ((EntityPlayerImpl) entity).getBaseComponent();
        // We only accept player's movement inputs, which are after SetLocalPlayerAsInitializedPacket,
        // So after the player sent SetLocalPlayerAsInitializedPacket, we need to sync the pos with the
        // client, otherwise the client will snap into the ground
        player.viewEntityLocation(entity, baseComponent.getLastSentLocation(), baseComponent.getLocation(), true);
        // Send debug shapes to the player after the player fully joined
        ((AllayDimension) entity.getDimension()).addDebugShapesTo(player);
    }

    @Override
    public BedrockPacketType getPacketType() {
        return BedrockPacketType.SET_LOCAL_PLAYER_AS_INITIALIZED;
    }
}
