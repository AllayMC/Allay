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
 * {@code SetLocalPlayerAsInitializedPacketProcessor} handles the client's final
 * initialization notification after spawning. A cancellable join event controls
 * admission and the join message, while acceptance requests {@code IN_GAME},
 * resends the server location as a teleport, and presents dimension primitives.
 *
 * <p>
 * The client has moved locally before ordinary movement admission begins, so
 * the location resend establishes the server's current position after joining.
 * Primitive shapes are sent after the state request because their ordinary
 * dimension-add path sends them only to in-game players. The state setter's
 * result is not checked here, and later failures do not roll back state changes,
 * sent presentation, or join-event effects. A nonblank event message broadcasts
 * only after location and primitive presentation finish normally.
 *
 * @author daoge_cmd
 */
public class SetLocalPlayerAsInitializedPacketProcessor extends ILoginPacketProcessor<SetLocalPlayerAsInitializedPacket> {
    /**
     * Publishes the join event and finishes accepted initialization presentation.
     * Event rejection disconnects with its reason before requesting in-game state.
     *
     * <p>
     * Acceptance requests {@code IN_GAME} before sending the authoritative location
     * and primitive shapes, then optionally broadcasts the event's join message.
     * The handler assumes a controlled entity and {@code AllayDimension}, ignores
     * whether the state transition succeeds, and does not compensate earlier
     * effects if presentation or broadcasting fails. The packet's runtime ID is
     * not compared with the controlled entity here.
     */
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
        // Movement admission starts after initialization; resend the server's
        // current location as a teleport so earlier client movement does not
        // leave the newly joined view at a different height.
        player.viewEntityLocation(entity, entity.getLocation(), true);
        // Dimension addition skips primitive presentation before IN_GAME; send
        // those existing shapes now so this newly initialized player receives
        // the dimension's current debug presentation.
        ((AllayDimension) entity.getDimension()).addPrimitiveShapesTo(player);

        var joinMessage = event.getJoinMessage();
        if (joinMessage != null && !joinMessage.isBlank()) {
            Server.getInstance().getMessageChannel().broadcastTranslatable(joinMessage, allayPlayer.getLoginData().getXname());
        }
    }

    /**
     * Returns the local-player initialization packet type handled by this processor.
     */
    @Override
    public BedrockPacketType getPacketType() {
        return BedrockPacketType.SET_LOCAL_PLAYER_AS_INITIALIZED;
    }
}
