package org.allaymc.api.entity.component.player;

import org.allaymc.api.entity.component.EntityComponent;
import org.allaymc.api.i18n.MayContainTrKey;
import org.allaymc.api.i18n.TrKeys;
import org.allaymc.api.network.PacketReceiver;
import org.allaymc.api.player.ClientState;
import org.allaymc.api.player.LoginData;
import org.cloudburstmc.protocol.bedrock.BedrockServerSession;

/**
 * EntityPlayerNetworkComponent is the component that manages network related things of a player.
 *
 * @author daoge_cmd
 */
public interface EntityPlayerNetworkComponent extends EntityComponent, PacketReceiver {
    /**
     * Get the login data of the player.
     *
     * @return the login data of the player
     */
    LoginData getLoginData();

    /**
     * Get the origin name of the player.
     * <p>
     * Origin name is the xbox id of a player, which won't be changed unless the player do changes in his xbox account
     * This name is used for identifying a player in the server. Check display name if you want to edit the appearance of player's name.
     *
     * @return the origin name of the player
     */
    default String getOriginName() {
        return getLoginData().getXname();
    }

    /**
     * Disconnect the player.
     */
    default void disconnect() {
        disconnect(TrKeys.MC_DISCONNECT_DISCONNECTED);
    }

    /**
     * Disconnect the player with the given reason.
     *
     * @param reason the reason for disconnecting the player
     */
    void disconnect(@MayContainTrKey String reason);

    /**
     * Get the client state of this player.
     *
     * @return the client state of this player
     */
    ClientState getClientState();

    /**
     * Get the last client state of this player.
     *
     * @return the last client state of this player
     */
    ClientState getLastClientState();

    /**
     * Check if the player is connected.
     *
     * @return {@code true} if the player is connected, {@code false} otherwise.
     */
    default boolean isConnected() {
        return getClientState().ordinal() >= ClientState.CONNECTED.ordinal();
    }

    /**
     * Check if the player is logged in.
     *
     * @return {@code true} if the player is logged in, {@code false} otherwise.
     */
    default boolean isLoggedIn() {
        return getClientState().ordinal() >= ClientState.LOGGED_IN.ordinal();
    }

    /**
     * Check if the player has been fully initialized.
     * <p>
     * This represents whether the player's client can see the world and proceed with gameplay.
     *
     * @return {@code true} if the player has been fully initialized, {@code false} otherwise.
     */
    default boolean isInitialized() {
        return getClientState().ordinal() >= ClientState.IN_GAME.ordinal();
    }

    /**
     * Check if the player is disconnected.
     *
     * @return {@code true} if the player is disconnected, {@code false} otherwise.
     */
    default boolean isDisconnected() {
        return getClientState() == ClientState.DISCONNECTED;
    }

    /**
     * Get the client session for the player.
     *
     * @return the client session for the player
     */
    BedrockServerSession getClientSession();

    /**
     * Get the ping of the player.
     *
     * @return the ping of the player
     */
    int getPing();
}
