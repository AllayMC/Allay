package org.allaymc.api.player;

import org.allaymc.api.bossbar.BossBarViewer;
import org.allaymc.api.container.ContainerViewer;
import org.allaymc.api.dialog.DialogViewer;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.form.FormViewer;
import org.allaymc.api.message.MayContainTrKey;
import org.allaymc.api.message.MessageReceiver;
import org.allaymc.api.message.TrKeys;
import org.allaymc.api.scoreboard.ScoreboardViewer;
import org.allaymc.api.world.WorldViewer;
import org.allaymc.api.world.data.DimensionInfo;
import org.jetbrains.annotations.ApiStatus;

import java.net.SocketAddress;
import java.util.Collection;

/**
 * Represents a player in the server. A {@link Player} basically 'control' an {@link EntityPlayer}.
 *
 * @author daoge_cmd
 */
public interface Player extends MessageReceiver, WorldViewer, ContainerViewer, BossBarViewer, FormViewer, ScoreboardViewer, DialogViewer {

    Speed DEFAULT_SPEED = new Speed(0.1, 1.0);
    Speed DEFAULT_FLY_SPEED = new Speed(0.05, 1.0);
    Speed DEFAULT_VERTICAL_FLY_SPEED = new Speed(1.0, 1.0);

    /**
     * Retrieves the {@link EntityPlayer} currently controlled by the player.
     *
     * @return the {@link EntityPlayer} that this player is controlling, or {@code null} if no entity is being controlled.
     */
    EntityPlayer getControlledEntity();

    /**
     * Get the login data of the client.
     *
     * @return the login data of the client
     */
    LoginData getLoginData();

    /**
     * Check if the player is a NetEase (China) client.
     *
     * @return {@code true} if the player is using a NetEase client, {@code false} otherwise
     */
    boolean isNetEasePlayer();

    /**
     * Get the origin name of the client.
     * <p>
     * Origin name is the xbox id of a client, which won't be changed unless the client does changes in his xbox account
     * This name is used for identifying a client in the server. Check the display name if you want to edit the appearance
     * of the client's name.
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
    void viewPlayerPermission(Player player);

    /**
     * Views a player list change. The provided players will be added to the player list.
     *
     * @param players the players to be added or removed from the player list
     * @param add     {@code true} to add the players to the list, {@code false} to remove the players
     */
    void viewPlayerListChange(Collection<Player> players, boolean add);

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
     * Sends the experience level to the client.
     *
     * @param value the experience level to be sent.
     */
    void sendExperienceLevel(int value);

    /**
     * Sends the experience progress to the client.
     *
     * @param value the progress value to be sent.
     */
    void sendExperienceProgress(float value);

    /**
     * Sends the food level to the client.
     *
     * @param value the food level to be sent.
     */
    void sendFoodLevel(int value);

    /**
     * Sends the food saturation level to the client.
     *
     * @param value the food saturation level to send.
     */
    void sendFoodSaturationLevel(float value);

    /**
     * Sends the food exhaustion level to the client.
     *
     * @param value the exhaustion level to be sent.
     */
    void sendFoodExhaustionLevel(float value);

    /**
     * Sends a cooldown notification to the client for the specified category.
     *
     * @param category the identifier for the cooldown category
     * @param duration the length of the cooldown in ticks
     */
    void sendCooldown(String category, int duration);

    /**
     * Represents a speed value controlled by a base speed and a multiplier. The actual
     * speed value is the multiplication of the base speed with the multiplier.
     *
     * @param baseSpeed  the base speed value
     * @param multiplier the multiplier of the base speed value
     */
    record Speed(double baseSpeed, double multiplier) {
        /**
         * Calculates the actual speed by multiplying the base speed with the multiplier.
         *
         * @return the calculated speed as a product of the base speed and multiplier
         */
        public double calculate() {
            return baseSpeed * multiplier;
        }

        /**
         * Creates a new {@code Speed} instance by adding the specified multiplier to the current multiplier.
         *
         * @param multiplier the multiplier to be added to the current multiplier
         * @return a new {@code Speed} instance with the updated multiplier
         */
        public Speed addMultiplier(double multiplier) {
            return new Speed(this.baseSpeed, this.multiplier + multiplier);
        }
    }

    /**
     * Get the speed of the player.
     *
     * @return The speed of the player
     */
    Speed getSpeed();

    /**
     * Set the speed of the player.
     *
     * @param speed The speed to set
     */
    void setSpeed(Speed speed);

    /**
     * Get the fly speed of the player.
     *
     * @return The fly speed of the player
     */
    Speed getFlySpeed();

    /**
     * Sets the fly speed of the player.
     *
     * @param flySpeed the fly speed to set
     */
    void setFlySpeed(Speed flySpeed);

    /**
     * Get the vertical fly speed of the player.
     *
     * @return The vertical fly speed of the player
     */
    Speed getVerticalFlySpeed();

    /**
     * Sets the vertical fly speed of the player.
     *
     * @param verticalFlySpeed the vertical fly speed to set
     */
    void setVerticalFlySpeed(Speed verticalFlySpeed);

    /**
     * Sets the visibility of a specific HUD element for the player.
     *
     * @param element the {@link HudElement} whose visibility is to be modified
     * @param visible {@code true} to make the HUD element visible, {@code false} to hide it
     */
    void setHudElementVisibility(HudElement element, boolean visible);

    /**
     * Determines the visibility of the specified HUD (Heads-Up Display) element.
     *
     * @param element the {@code HudElement} whose visibility status is to be checked
     * @return {@code true} if the HUD element is visible, {@code false} otherwise
     */
    boolean getHudElementVisibility(HudElement element);

    /**
     * Transfers the player to another server.
     *
     * @param address the address of the server to transfer to
     * @param port    the port of the server to transfer to
     */
    void transfer(String address, int port);

    /**
     * Whether the player's client is currently showing the dimension switching loading screen.
     *
     * @return {@code true} if a dimension change is in progress
     */
    boolean isChangingDimension();

    /**
     * Show the dimension switching loading screen to the client immediately.
     * <p>
     * Call this before performing async work (e.g. chunk loading) to give the
     * player visual feedback while the server prepares the target dimension.
     * The flag is automatically cleared when {@link #completeDimensionChange()} is called.
     *
     * @param targetDimInfo the target dimension info
     * @param x             approximate target x coordinate
     * @param y             approximate target y coordinate
     * @param z             approximate target z coordinate
     */
    void beginDimensionChange(DimensionInfo targetDimInfo, double x, double y, double z);

    /**
     * Complete a dimension change by sending the dimension ack and resetting the flag.
     * Call this after the player has been added to the target dimension,
     * or to dismiss the loading screen if the teleport fails.
     */
    @ApiStatus.Internal
    void completeDimensionChange();

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
}
