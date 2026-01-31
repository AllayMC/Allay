package org.allaymc.server.network.processor.login;

import org.allaymc.api.eventbus.event.server.PlayerJoinEvent;
import org.allaymc.api.message.TrKeys;
import org.allaymc.api.player.ClientState;
import org.allaymc.api.player.Player;
import org.allaymc.api.server.Server;
import org.allaymc.api.utils.TextFormat;
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
        var event = new PlayerJoinEvent(player, TextFormat.YELLOW + "%" + TrKeys.MC_MULTIPLAYER_PLAYER_JOINED, TrKeys.MC_DISCONNECTIONSCREEN_NOREASON);
        if (!event.call()) {
            player.disconnect(event.getDisconnectReason());
            return;
        }

        allayPlayer.setClientState(ClientState.IN_GAME);
        var entity = player.getControlledEntity();
        // We only accept player's movement inputs, which are after SetLocalPlayerAsInitializedPacket,
        // So after the player sent SetLocalPlayerAsInitializedPacket, we need to sync the pos with the
        // client, otherwise the client will snap into the ground
        player.viewEntityLocation(entity, entity.getLocation(), true);
        // Send debug shapes to the player after the player fully joined
        ((AllayDimension) entity.getDimension()).addDebugShapesTo(player);
        Server.getInstance().getMessageChannel().broadcastTranslatable(event.getJoinMessage(), allayPlayer.getLoginData().getXname());
    }

    @Override
    public BedrockPacketType getPacketType() {
        return BedrockPacketType.SET_LOCAL_PLAYER_AS_INITIALIZED;
    }
}
