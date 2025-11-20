package org.allaymc.api.entity.component;

import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.message.MayContainTrKey;
import org.allaymc.api.message.TrKeys;
import org.allaymc.api.player.ClientState;
import org.allaymc.api.player.LoginData;
import org.jetbrains.annotations.ApiStatus;

import java.net.SocketAddress;
import java.util.Collection;

/**
 * EntityPlayerClientComponent is the component that manages network-related things of a player. It
 * holds a session and exposes the relevant interface.
 *
 * @author daoge_cmd
 */
public interface EntityPlayerClientComponent extends EntityComponent {
    /**
     * Get the login data of the client.
     *
     * @return the login data of the client
     */
    LoginData getLoginData();

    /**
     * Get the origin name of the client.
     * <p>
     * Origin name is the xbox id of a client, which won't be changed unless the client do changes in his xbox account
     * This name is used for identifying a client in the server. Check display name if you want to edit the appearance of client's name.
     *
     * @return the origin name of the client
     */
    default String getOriginName() {
        return getLoginData().getXname();
    }

    /**
     * Disconnect the client.
     */
    default void disconnect() {
        disconnect(TrKeys.MC_DISCONNECT_DISCONNECTED);
    }

    /**
     * Disconnect the client with the given reason.
     *
     * @param reason the reason for disconnecting the client
     */
    void disconnect(@MayContainTrKey String reason);

    /**
     * Get the client state of this client.
     *
     * @return the client state of this client
     */
    ClientState getClientState();

    /**
     * Get the last client state of this client.
     *
     * @return the last client state of this client
     */
    ClientState getLastClientState();

    /**
     * Check if the client is connected.
     *
     * @return {@code true} if the client is connected, {@code false} otherwise.
     */
    default boolean isConnected() {
        return getClientState().ordinal() >= ClientState.CONNECTED.ordinal();
    }

    /**
     * Check if the client is logged in.
     *
     * @return {@code true} if the client is logged in, {@code false} otherwise.
     */
    default boolean isLoggedIn() {
        return getClientState().ordinal() >= ClientState.LOGGED_IN.ordinal();
    }

    /**
     * Check if the client has been fully initialized.
     * <p>
     * This represents whether the client's client can see the world and proceed with gameplay.
     *
     * @return {@code true} if the client has been fully initialized, {@code false} otherwise.
     */
    default boolean isInitialized() {
        return getClientState().ordinal() >= ClientState.IN_GAME.ordinal();
    }

    /**
     * Check if the client is disconnected.
     *
     * @return {@code true} if the client is disconnected, {@code false} otherwise.
     */
    default boolean isDisconnected() {
        return getClientState() == ClientState.DISCONNECTED;
    }

    /**
     * Get the socket address of the client.
     *
     * @return the socket address of the client
     */
    SocketAddress getSocketAddress();

    /**
     * Get the ping of the client.
     *
     * @return the ping of the client
     */
    int getPing();

    /**
     * Views the specified player's permission. This will update the permission level shown in the player list and some
     * in-game permissions/properties like whether the player can fly, chat, and the player's (vertical) fly speed etc.
     *
     * @param player the player to view
     */
    void viewPlayerPermission(EntityPlayer player);

    /**
     * Views a player list change. The provided players will be added to the player list.
     *
     * @param players the players to be added or removed from the player list
     * @param add     {@code true} to add the players to the list, {@code false} to remove the players
     */
    void viewPlayerListChange(Collection<EntityPlayer> players, boolean add);

    /**
     * Sends a tip to the player.
     *
     * @param message The message to send
     */
    void sendTip(String message);

    /**
     * Sends a popup to the player.
     *
     * @param message The message to send
     */
    void sendPopup(String message);

    /**
     * Send a toast to the player. The toast will be displayed at the top of the screen
     * with a title and content in a period of time.
     *
     * @param title   the title of the toast
     * @param content the content of the toast
     */
    void sendToast(String title, String content);

    /**
     * Send a title to the player which will be shown in the middle of the screen.
     *
     * @param title the title to send
     */
    void sendTitle(String title);

    /**
     * Send a subtitle to the player which will be shown below the main title.
     * If no title is set, the subtitle won't be shown, in other words title must
     * be set before set subtitle.
     *
     * @param subtitle the subtitle to send
     */
    void sendSubtitle(String subtitle);

    /**
     * Send an action bar message to the player which will be shown above player's hot bar.
     *
     * @param actionBar the action bar message to send
     */
    void sendActionBar(String actionBar);

    /**
     * Set the title settings of this player. These settings will be used for the next title, subtitle and actionbar.
     *
     * @param fadeInTime  the time in ticks for the title to fade in
     * @param duration    the time in ticks for the title to stay
     * @param fadeOutTime the time in ticks for the title to fade out
     */
    void setTitleSettings(int fadeInTime, int duration, int fadeOutTime);

    /**
     * Reset the title settings of this player to the default values.
     */
    void resetTitleSettings();

    /**
     * Clear the title that is being displayed on the player's screen
     */
    void clearTitle();

    /**
     * Sends a packet to the client.
     *
     * @param packet the packet object to be sent to the client. The type of the packet depends on the implementation.
     */
    @ApiStatus.Internal
    void sendPacket(Object packet);

    /**
     * Immediately sends a packet to the client without any delay or buffering.
     *
     * @param packet the packet to be sent to the client. The type of the packet depends on the implementation.
     */
    @ApiStatus.Internal
    void sendPacketImmediately(Object packet);

    /**
     * Require encoding and resending all commands to the player next tick. This method should be called when
     * command permissions change, but usually you don't need to call this method manually since the permission
     * listener does it.
     */
    @ApiStatus.Internal
    void sendCommands();
}
