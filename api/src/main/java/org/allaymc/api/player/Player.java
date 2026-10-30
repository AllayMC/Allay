package org.allaymc.api.player;

import org.allaymc.api.bossbar.BossBarViewer;
import org.allaymc.api.container.ContainerViewer;
import org.allaymc.api.ddui.DDUIViewer;
import org.allaymc.api.dialog.DialogViewer;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.form.FormViewer;
import org.allaymc.api.message.MayContainTrKey;
import org.allaymc.api.message.MessageReceiver;
import org.allaymc.api.message.TrKeys;
import org.allaymc.api.scoreboard.ScoreboardViewer;
import org.allaymc.api.utils.tuple.Pair;
import org.allaymc.api.world.WorldViewer;
import org.allaymc.api.world.dimension.DimensionType;
import org.jetbrains.annotations.UnmodifiableView;
import org.joml.Vector3dc;

import java.awt.image.BufferedImage;
import java.net.SocketAddress;
import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * {@code Player} represents a network connection that can control a separate player
 * entity and receive world, inventory, and interface updates. Login metadata belongs
 * to the connection, while location, inventory, and survival state belong to its
 * controlled {@code EntityPlayer}; several operations therefore require login or
 * spawning to have completed first.
 *
 * <p>
 * Tracked abilities are only one input to effective capabilities. Game mode,
 * operator status, and explicit connection policy can override individual abilities,
 * and the capability queries document that precedence. Dimension-change methods
 * manage client loading presentation around transfer work rather than performing
 * teleportation themselves. Viewer operations send client state; callers must
 * continue to update the owning server entity or container when a real gameplay
 * change is required.
 *
 * @author daoge_cmd
 */
public interface Player extends MessageReceiver, WorldViewer, ContainerViewer, BossBarViewer, FormViewer, DDUIViewer, ScoreboardViewer, DialogViewer {

    /**
     * {@code DEFAULT_SPEED} supplies the initial walking configuration with base
     * {@code 0.1} and multiplier {@code 1.0} for new connections.
     */
    Speed DEFAULT_SPEED = new Speed(0.1, 1.0);
    /**
     * {@code DEFAULT_FLY_SPEED} supplies the initial horizontal flight configuration
     * with base {@code 0.05} and multiplier {@code 1.0} for new connections.
     */
    Speed DEFAULT_FLY_SPEED = new Speed(0.05, 1.0);
    /**
     * {@code DEFAULT_VERTICAL_FLY_SPEED} supplies the initial vertical flight
     * configuration with base {@code 1.0} and multiplier {@code 1.0} for new
     * connections.
     */
    Speed DEFAULT_VERTICAL_FLY_SPEED = new Speed(1.0, 1.0);

    /**
     * Returns the player entity currently controlled by this connection, when one
     * exists. Connections before spawning return {@code null}; the entity and
     * connection have separate lifetimes and should not be treated as
     * interchangeable identities.
     */
    EntityPlayer getControlledEntity();

    /**
     * Returns the identity and metadata decoded for this connection after login
     * processing. The standard implementation leaves it {@code null} before
     * decoding, so callers must check lifecycle state before using login-dependent
     * values.
     */
    LoginData getLoginData();

    /**
     * Returns whether this connection was classified as a NetEase client.
     */
    boolean isNetEasePlayer();

    /**
     * Returns the name resolved from this connection's login identity. Login data
     * must be available; the value can differ from an entity display name and is not
     * a permanent account identifier.
     */
    default String getOriginName() {
        return getLoginData().getXname();
    }

    /**
     * Disconnects this connection using the default translated disconnection reason.
     * The detailed overload performs lifecycle cleanup; repeating a completed
     * disconnect does not restart cleanup in the standard implementation.
     */
    default void disconnect() {
        disconnect(TrKeys.MC_DISCONNECT_DISCONNECTED);
    }

    /**
     * Disconnects this connection with the supplied reason after claiming its
     * lifecycle transition. The standard implementation translates after login with
     * the client language and otherwise uses the default language; cleanup failures
     * are logged.
     */
    void disconnect(@MayContainTrKey String reason);

    /**
     * Returns the connection's current packet-processing and game-joining lifecycle
     * state.
     */
    ClientState getClientState();

    /**
     * Returns the state preceding the latest successful connection lifecycle
     * transition.
     */
    ClientState getLastClientState();

    /**
     * Returns whether the current lifecycle state has reached transport connection.
     */
    default boolean isConnected() {
        return getClientState().ordinal() >= ClientState.CONNECTED.ordinal();
    }

    /**
     * Returns whether the current lifecycle state has reached completed login.
     */
    default boolean isLoggedIn() {
        return getClientState().ordinal() >= ClientState.LOGGED_IN.ordinal();
    }

    /**
     * Returns whether the current lifecycle state has reached {@code IN_GAME}.
     */
    default boolean isInitialized() {
        return getClientState().ordinal() >= ClientState.IN_GAME.ordinal();
    }

    /**
     * Returns whether this connection is currently in the {@code DISCONNECTED}
     * state.
     */
    default boolean isDisconnected() {
        return getClientState() == ClientState.DISCONNECTED;
    }

    /**
     * Returns the remote socket address reported by the connection transport.
     */
    SocketAddress getSocketAddress();

    /**
     * Returns transport-measured round-trip latency in milliseconds when available.
     * The standard network interface uses {@code -1} when it cannot provide a
     * measurement, so callers must preserve that unavailable result.
     */
    int getPing();

    /**
     * Sends another player's effective capabilities and permission presentation to
     * this client. The subject player must have a controlled entity; viewing this
     * connection's own abilities also refreshes adventure settings and schedules
     * command descriptions.
     */
    void viewPlayerAbilities(Player player);

    /**
     * Returns an unmodifiable live view of this connection's tracked abilities. The
     * view reflects later changes and is not an independent snapshot; effective
     * capability queries also consider mode and policy.
     */
    @UnmodifiableView
    Set<PlayerAbility> getAbilities();

    /**
     * Returns whether the tracked set contains the supplied player ability.
     */
    boolean hasAbility(PlayerAbility ability);

    /**
     * Changes one tracked ability and schedules client synchronization when its
     * membership changes. Flight-affecting changes require a controlled entity when
     * reconciliation updates its flying flag; update notifications run after the
     * stored set changes.
     */
    void setAbility(PlayerAbility ability, boolean value);

    /**
     * Enables or disables each supplied tracked ability without replacing unrelated
     * entries. The standard implementation snapshots prior policy, publishes an
     * update event when an entity exists, and reconciles effective flight after
     * changing membership.
     */
    void setAbilities(Set<PlayerAbility> abilities, boolean value);

    /**
     * Replaces tracked abilities only when the supplied set differs from current
     * contents. The complete live {@code getAbilities()} view compares equal and is
     * a no-op; differing replacement views backed by current storage must be copied
     * first because replacement clears storage before reading entries.
     */
    void setAbilities(Set<PlayerAbility> abilities);

    /**
     * Enables one tracked ability through the normal ability-update and
     * synchronization path.
     */
    default void addAbility(PlayerAbility ability) {
        setAbility(ability, true);
    }

    /**
     * Enables the supplied distinct abilities through the bulk update path.
     * {@code Set.of} rejects repeated or {@code null} entries before the player's
     * ability set is changed.
     *
     * @throws IllegalArgumentException if an ability occurs more than once
     * @throws NullPointerException if the array or any ability is {@code null}
     */
    default void addAbilities(PlayerAbility... abilities) {
        setAbilities(Set.of(abilities), true);
    }

    /**
     * Disables one tracked ability through the normal ability-update and
     * synchronization path.
     */
    default void removeAbility(PlayerAbility ability) {
        setAbility(ability, false);
    }

    /**
     * Disables the supplied distinct abilities through the bulk update path.
     * {@code Set.of} rejects repeated or {@code null} entries before the player's
     * ability set is changed.
     *
     * @throws IllegalArgumentException if an ability occurs more than once
     * @throws NullPointerException if the array or any ability is {@code null}
     */
    default void removeAbilities(PlayerAbility... abilities) {
        setAbilities(Set.of(abilities), false);
    }

    /**
     * Returns whether effective world policy and abilities allow this player to
     * place blocks. The standard implementation first rejects effective
     * immutable-world state and then rejects {@code ADVENTURE}, including operators
     * in that mode.
     *
     * <p>
     * An operator otherwise succeeds without requiring {@code PLACE_BLOCK}; other
     * players need that ability. Operator status bypasses the immutable-world query,
     * including its spectator-derived result, so this helper alone does not reject
     * every operator in {@code SPECTATOR}. Callers must preserve any additional
     * action-specific validation.
     */
    boolean canPlaceBlocks();

    /**
     * Returns whether effective world policy and abilities allow this player to
     * break blocks. The standard implementation first rejects effective
     * immutable-world state and then rejects {@code ADVENTURE}, including operators
     * in that mode.
     *
     * <p>
     * An operator otherwise succeeds without requiring {@code BREAK_BLOCK}; other
     * players need that ability. Operator status bypasses the immutable-world query,
     * including its spectator-derived result, so this helper alone does not reject
     * every operator in {@code SPECTATOR}. Callers must preserve any additional
     * action-specific validation.
     */
    boolean canBreakBlocks();

    /**
     * Returns whether effective world policy and abilities permit block interaction.
     * The standard implementation rejects effective immutable-world state before
     * explicitly rejecting {@code SPECTATOR}, so spectator operators remain rejected
     * by this helper.
     *
     * <p>
     * An operator outside spectator mode succeeds without requiring
     * {@code INTERACT_BLOCK}. Other players need that ability after the
     * immutable-world check; adventure mode contributes to immutable-world state for
     * nonoperators. This query does not perform reach checks or dispatch a
     * block-interaction event, which remain responsibilities of the action path.
     */
    boolean canInteractWithBlocks();

    /**
     * Returns whether game mode and abilities permit opening containers. The
     * standard implementation rejects {@code SPECTATOR} before considering operator
     * status, so an operator cannot bypass that mode restriction through this
     * helper.
     *
     * <p>
     * Other operators succeed without requiring {@code OPEN_CONTAINER}; remaining
     * players need that ability. The helper does not consult the independent
     * immutable-world flag or reject adventure mode separately. Callers still
     * validate the target container and its reachability before applying a
     * particular open request.
     */
    boolean canOpenContainers();

    /**
     * Returns whether game mode and abilities permit attacking another player. The
     * standard implementation rejects {@code SPECTATOR} before considering operator
     * status, so an operator cannot bypass that mode restriction through this
     * helper.
     *
     * <p>
     * Other operators succeed without requiring {@code ATTACK_PLAYER}; remaining
     * players need that ability. The query does not enforce world damage rules,
     * distance, or target validity. The attacking path must still apply those rules
     * before it changes the target's health or combat state.
     */
    boolean canAttackPlayers();

    /**
     * Returns whether game mode and abilities permit attacking nonplayer mobs. The
     * standard implementation rejects {@code SPECTATOR} before considering operator
     * status, so an operator cannot bypass that mode restriction through this
     * helper.
     *
     * <p>
     * Other operators succeed without requiring {@code ATTACK_MOB}; remaining
     * players need that ability. The query does not enforce world damage rules,
     * distance, or target validity. The attacking path must still apply those rules
     * before it changes the target's health or combat state.
     */
    boolean canAttackMobs();

    /**
     * Returns whether a controlled player entity is permitted to fly. The standard
     * implementation requires an entity even when stored flags would otherwise force
     * flight, so connections without an entity return {@code false}.
     *
     * <p>
     * With an entity, effective always-flying state or {@code MAY_FLY} grants
     * permission. The stored {@code FLYING} ability alone does not grant that
     * permission. This query does not start flight; input handling and
     * ability-update synchronization apply the entity's flying state separately.
     */
    boolean canFly();

    /**
     * Returns whether the tracked set enables {@code INFINITE_BLOCK} for this
     * connection.
     */
    boolean hasInfiniteBlock();

    /**
     * Returns whether a controlled entity is treated as moving through blocks. The
     * standard implementation requires a controlled entity, then accepts either
     * {@code NO_CLIP} or {@code SPECTATOR}; a connection without an entity returns
     * {@code false}.
     *
     * <p>
     * Operator status does not grant this result by itself. The query describes
     * effective capability rather than changing entity motion or resolving
     * collisions, and callers must still use the physics and movement paths for
     * actual location updates.
     */
    boolean isNoClip();

    /**
     * Returns whether this connection is effectively restricted from world editing.
     * The standard implementation gives operator status precedence and returns
     * {@code false} for operators even when an explicit flag or restrictive mode is
     * present.
     *
     * <p>
     * For other players, the explicit immutable-world flag takes precedence,
     * followed by {@code ADVENTURE} or {@code SPECTATOR} on a controlled entity. A
     * worldless connection without the explicit flag returns {@code false}.
     * Individual action queries may impose further restrictions after consulting
     * this result.
     */
    boolean isImmutableWorld();

    /**
     * Sets the explicit immutable-world flag and schedules an ability refresh when
     * it changes. Effective policy still gives operator status precedence and may
     * derive immutability from the controlled entity's game mode.
     */
    void setImmutableWorld(boolean immutableWorld);

    /**
     * Returns whether flight is forced by the connection's explicit flag,
     * {@code NO_CLIP}, or a controlled spectator entity. The standard implementation
     * evaluates these alternatives independently, so an explicit flag or no-clip
     * ability can return {@code true} before an entity exists.
     *
     * <p>
     * This result describes effective policy and does not itself alter entity state.
     * {@code canFly} adds its own entity requirement, and ability-changing
     * operations synchronize the entity's flying flag using the policy observed
     * before and after their change.
     */
    boolean isAlwaysFlying();

    /**
     * Sets the explicit always-flying flag and schedules an ability refresh. The
     * standard implementation invokes entity flight synchronization with its raw
     * prior flag and post-update permission, so callers must not assume every
     * cleared policy immediately stops flight.
     */
    void setAlwaysFlying(boolean alwaysFlying);

    /**
     * Sends player-list additions or removals for the supplied connections to this
     * client. {@code add} selects the operation, and the standard encoder reads each
     * subject's initialized login, entity, and skin data.
     */
    void viewPlayerListChange(Collection<Player> players, boolean add);

    /**
     * Sends the supplied text using the client's tip message presentation.
     */
    void sendTip(String message);

    /**
     * Sends the supplied text using the client's popup message presentation.
     */
    void sendPopup(String message);

    /**
     * Sends a notification toast containing the supplied title and content.
     */
    void sendToast(String title, String content);

    /**
     * Sends the main title text for the client's centered title display.
     */
    void sendTitle(String title);

    /**
     * Sends subtitle text for the client's title display. A subtitle depends on an
     * active main title for presentation; this call does not itself send or create
     * that main title.
     */
    void sendSubtitle(String subtitle);

    /**
     * Sends action-bar text for presentation above the player's hotbar.
     */
    void sendActionBar(String actionBar);

    /**
     * Sends fade-in, visible duration, and fade-out timing values in game ticks.
     * These values configure subsequent client title presentation; this call does
     * not send title text or validate the supplied timing range.
     */
    void setTitleSettings(int fadeInTime, int duration, int fadeOutTime);

    /**
     * Requests the client's default timing and state for title presentation.
     */
    void resetTitleSettings();

    /**
     * Requests removal of the title currently displayed on this client.
     */
    void clearTitle();

    /**
     * Requests positional or rotational camera shake with the supplied intensity and
     * duration. {@code duration} is measured in seconds; this presentation request
     * does not move the server-side player entity.
     */
    void shakeCamera(CameraShakeType shakeType, float intensity, float duration);

    /**
     * Requests that the client stop its active camera shake presentation.
     */
    void stopCameraShake();

    /**
     * Appends a fog identifier and sends the updated stack to the client. Repeated
     * identifiers are retained in stack order; callers supply resource identifiers
     * understood by the client.
     */
    void pushFog(String fogId);

    /**
     * Removes the last matching fog identifier and sends the stack when a match
     * exists. The result is {@code false} when absent; earlier occurrences remain in
     * their previous order.
     */
    boolean popFog(String fogId);

    /**
     * Removes every matching fog identifier and returns the number removed. The
     * standard implementation sends the resulting stack only when at least one entry
     * changed; other identifiers retain their relative order.
     */
    int removeFog(String fogId);

    /**
     * Returns an unmodifiable live view of the connection's ordered fog identifiers.
     * Repeated identifiers are retained, and later stack mutations remain visible
     * through the returned view rather than an independent snapshot.
     */
    @UnmodifiableView
    List<String> getFogs();

    /**
     * Clears every fog identifier and sends the empty stack when entries existed. An
     * already empty stack produces no update; this operation discards the retained
     * order and does not return a prior snapshot.
     */
    void removeAllFogs();

    /**
     * Sends the supplied experience level as a client attribute update.
     */
    void sendExperienceLevel(int value);

    /**
     * Sends the supplied within-level experience progress as a client attribute
     * update.
     */
    void sendExperienceProgress(float value);

    /**
     * Sends the supplied food level as a client hunger attribute update.
     */
    void sendFoodLevel(int value);

    /**
     * Sends the supplied food saturation as a client attribute update.
     */
    void sendFoodSaturationLevel(float value);

    /**
     * Sends the supplied food exhaustion as a client attribute update.
     */
    void sendFoodExhaustionLevel(float value);

    /**
     * Sends the supplied current and maximum health as client attribute data.
     */
    void sendHealth(float health, float maxHealth);

    /**
     * Sends the supplied absorption amount as a client attribute update.
     */
    void sendAbsorption(float absorption);

    /**
     * Sends the selected cooldown category and duration in game ticks to the client.
     * This notification does not update the controlled entity's server-side cooldown
     * storage, which the owning item or gameplay logic manages.
     */
    void sendCooldown(String category, int duration);

    /**
     * Sends a map image using the identified map resource. The standard encoder
     * reads the top-left {@code 128} by {@code 128} pixels without scaling, so the
     * image must supply at least that region.
     */
    void sendMapData(long mapId, BufferedImage image);

    /**
     * Sends translated death-screen text using this connection's login language.
     * {@code deathInfo} supplies the translation key and parameters; callers must
     * provide decoded login data and a non-{@code null} pair.
     */
    void sendDeathInfo(Pair<String, String[]> deathInfo);

    /**
     * Notifies the client that its controlled entity finished charging an item.
     */
    void sendItemChargingFinished();

    /**
     * {@code Speed} holds a base speed and multiplier whose product is the effective
     * speed value. The record performs no range validation, and
     * {@code addMultiplier} returns a new configuration rather than modifying this
     * one.
     *
     * @param baseSpeed unscaled base speed value
     * @param multiplier multiplier applied to the base
     */
    record Speed(double baseSpeed, double multiplier) {
        /**
         * Returns the product of this configuration's {@code baseSpeed} and
         * {@code multiplier}.
         */
        public double calculate() {
            return baseSpeed * multiplier;
        }

        /**
         * Returns a new speed with the same base and the supplied multiplier added.
         */
        public Speed addMultiplier(double multiplier) {
            return new Speed(this.baseSpeed, this.multiplier + multiplier);
        }
    }

    /**
     * Returns the walking speed configuration used for this connection's movement
     * attribute.
     */
    Speed getSpeed();

    /**
     * Replaces the walking speed configuration and sends it when changed. The
     * standard implementation requires a controlled entity for its attribute packet
     * and does not validate the speed's numeric range.
     */
    void setSpeed(Speed speed);

    /**
     * Returns the horizontal flying speed configuration for this player connection.
     */
    Speed getFlySpeed();

    /**
     * Replaces the horizontal flying speed and refreshes this player's client
     * abilities when changed. The standard implementation requires a controlled
     * entity and sends the calculated value through its float protocol
     * representation.
     */
    void setFlySpeed(Speed flySpeed);

    /**
     * Returns the vertical flying speed configuration for this player connection.
     */
    Speed getVerticalFlySpeed();

    /**
     * Replaces the vertical flying speed and refreshes this player's client
     * abilities when changed. The standard implementation requires a controlled
     * entity and sends the calculated value through its float protocol
     * representation.
     */
    void setVerticalFlySpeed(Speed verticalFlySpeed);

    /**
     * Sends a requested motion vector for the controlled player entity to the
     * client. The standard implementation encodes the vector without changing the
     * server entity's stored motion through this helper.
     */
    void setMotion(Vector3dc motion);

    /**
     * Returns the latest input category decoded from client input packets when
     * available. The standard implementation leaves this value {@code null} before
     * the first relevant packet supplies its metadata.
     */
    InputMode getInputMode();

    /**
     * Returns the latest client play mode decoded from input packets when available.
     * The standard implementation leaves this value {@code null} before the first
     * relevant packet supplies its metadata.
     */
    ClientPlayMode getPlayMode();

    /**
     * Returns the latest interaction model decoded from client input packets when
     * available. The standard implementation leaves this value {@code null} before
     * the first relevant packet supplies its metadata.
     */
    InputInteractionModel getInputInteractionModel();

    /**
     * Records whether the selected HUD element is visible and schedules a client
     * update when changed. The standard implementation batches the visibility state
     * until its next tick rather than sending immediately.
     */
    void setHudElementVisibility(HudElement element, boolean visible);

    /**
     * Returns whether the selected HUD element is absent from the hidden set.
     */
    boolean getHudElementVisibility(HudElement element);

    /**
     * Requests that the client connect to the supplied server address and port. This
     * sends a transfer packet; it does not itself connect the server to that
     * destination or confirm the client's arrival.
     */
    void transfer(String address, int port);

    /**
     * Returns whether this connection currently records an outstanding
     * dimension-change presentation.
     */
    boolean isChangingDimension();

    /**
     * Marks dimension switching active and submits the client loading-screen request
     * before returning. Callers provide the target dimension and approximate
     * coordinates, then invoke {@code completeDimensionChange} after transfer work
     * or to dismiss the presentation after failure.
     */
    void beginDimensionChange(DimensionType targetDimensionType, double x, double y, double z);

    /**
     * Clears dimension-switching state and sends the server acknowledgement for the
     * controlled entity. Callers invoke this after adding the entity to its target
     * dimension or when dismissing an unsuccessful transfer's loading screen.
     */
    void completeDimensionChange();

}
