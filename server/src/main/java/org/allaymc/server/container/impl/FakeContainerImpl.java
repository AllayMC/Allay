package org.allaymc.server.container.impl;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.container.Container;
import org.allaymc.api.container.ContainerType;
import org.allaymc.api.container.ContainerViewer;
import org.allaymc.api.container.interfaces.FakeContainer;
import org.allaymc.api.math.MathUtils;
import org.allaymc.api.player.Player;
import org.allaymc.api.server.Server;
import org.joml.RoundingMode;
import org.joml.Vector3d;
import org.joml.Vector3i;
import org.joml.Vector3ic;

import java.util.*;
import java.util.function.Consumer;

/**
 * {@code FakeContainerImpl} manages synthetic inventory contents, click listeners, and
 * the fake block positions used for each player's window. Subclasses publish the
 * client-side block and block-entity data; this base class schedules opening afterward
 * and restores current world block states when a registered player leaves.
 *
 * <p>
 * Fake blocks must be published before the window is opened, so {@code addPlayer} waits
 * eight scheduler ticks before calling the base viewer-opening path. A rejected opening
 * schedules restoration after another delay. The current rejection path invokes the
 * supplied callback with {@code false} and then unconditionally with {@code true}; it
 * does not provide a single-outcome callback guarantee. Restoring blocks leaves the
 * recorded positions in the map.
 *
 * @author daoge_cmd
 */
public abstract class FakeContainerImpl extends BaseContainer implements FakeContainer {

    /**
     * {@code customName} is the optional title read during fake block-entity
     * publication. It starts as {@code null}, and changing it alone does not resend
     * already published data.
     */
    @Getter
    @Setter
    protected String customName;
    /**
     * {@code clickListeners} groups callbacks by fake slot. Attempted fake-slot
     * transfers invoke these registrations before movement is rejected, independently of
     * ordinary slot-change listeners.
     */
    protected Int2ObjectMap<Set<Runnable>> clickListeners;
    /**
     * {@code fakeBlockPositions} retains each player's published block coordinates for
     * window opening and restoration. Restoring blocks does not remove the player's
     * recorded position array.
     */
    protected Map<ContainerViewer, Vector3ic[]> fakeBlockPositions;

    /**
     * Creates empty synthetic storage for the supplied fake container type. Click
     * registrations and per-viewer fake block positions start empty and are populated by
     * interaction and opening operations.
     */
    public FakeContainerImpl(ContainerType<? extends Container> containerType) {
        super(containerType);
        this.clickListeners = new Int2ObjectOpenHashMap<>();
        this.fakeBlockPositions = new HashMap<>();
    }

    /**
     * Registers a callback in the fake slot's click listener set.
     */
    @Override
    public void addClickListener(int slot, Runnable listener) {
        this.clickListeners.computeIfAbsent(slot, k -> new HashSet<>()).add(listener);
    }

    /**
     * Removes a callback from the fake slot's click listener set.
     */
    @Override
    public void removeClickListener(int slot, Runnable listener) {
        this.clickListeners.computeIfAbsent(slot, k -> new HashSet<>()).remove(listener);
    }

    /**
     * Clears the click registrations for every synthetic container slot.
     */
    @Override
    public void removeAllClickListeners() {
        this.clickListeners.clear();
    }

    /**
     * Rejects direct viewer opening with {@code UnsupportedOperationException}. Callers
     * must use {@code addPlayer}, which publishes fake blocks and waits before opening
     * the container window through the base implementation.
     */
    @Override
    public boolean addViewer(ContainerViewer viewer) {
        throw new UnsupportedOperationException("Calling addViewer() in FakeContainer is unsupported! Please use addPlayer() method!");
    }

    /**
     * Removes a registered player viewer and then restores its fake block coordinates to
     * current world states. Non-player viewers and absent memberships return {@code
     * false} without restoring blocks.
     */
    @Override
    public boolean removeViewer(ContainerViewer viewer) {
        if (!(viewer instanceof Player player)) {
            return false;
        }

        if (super.removeViewer(player)) {
            removeFakeBlocks(player);
            return true;
        }

        return false;
    }

    /**
     * Publishes fake blocks and schedules the player's container opening after eight
     * scheduler ticks. The subclass must record its published positions before the
     * delayed viewer callback uses them for the window location.
     *
     * <p>
     * If base opening returns {@code false}, another delayed task restores fake blocks
     * and the callback receives {@code false}. The current implementation then
     * unconditionally calls the callback with {@code true}, including that rejection
     * path. Callback exceptions or scheduling failures can interrupt the sequence
     * without cleanup or a further outcome notification.
     */
    @Override
    public void addPlayer(Player player, Consumer<Boolean> callback) {
        sendFakeBlocks(player);
        // Delay window opening until fake block updates precede it on the client; the
        // viewer callback then resolves the recorded block position.
        runDelayed(() -> {
            if (!super.addViewer(player)) {
                // Restore rejected fake blocks after the same scheduling delay,
                // allowing the failed opening attempt to finish before world states
                // are resent.
                runDelayed(() -> removeFakeBlocks(player));
                callback.accept(false);
            }

            callback.accept(true);
        });
    }

    /**
     * Returns the first recorded fake block position for the viewer, or {@code null}
     * when no array is recorded. The returned coordinate object is the stored reference
     * used for window opening.
     */
    public Vector3ic getFakeBlockPos(ContainerViewer viewer) {
        var array = this.fakeBlockPositions.get(viewer);
        if (array != null) {
            return array[0];
        }

        return null;
    }

    /**
     * Runs all callbacks registered for one fake slot. An absent registration set
     * produces no effect; callbacks run directly and an exception interrupts the
     * remaining listener iteration.
     */
    public void onClick(int slot) {
        this.clickListeners.getOrDefault(slot, Collections.emptySet()).forEach(Runnable::run);
    }

    /**
     * Publishes the subclass's synthetic blocks and block-entity data for the player.
     * Implementations record their coordinates in {@code fakeBlockPositions} before
     * returning so delayed opening can resolve the window position.
     */
    protected abstract void sendFakeBlocks(Player player);

    /**
     * Restores layer-zero world block states at the player's recorded fake coordinates.
     * The states come from the player's current dimension, and the stored position
     * record remains available after restoration.
     */
    protected void removeFakeBlocks(Player player) {
        var dimension = player.getControlledEntity().getDimension();
        for (var pos : this.fakeBlockPositions.getOrDefault(player, new Vector3ic[0])) {
            player.viewBlockUpdate(pos, 0, dimension.getBlockState(pos, 0));
        }
    }

    /**
     * Registers the callback for one delayed invocation on the server scheduler's
     * ticking thread. The delay is eight server ticks, and the one-shot registration
     * ends after its callback returns regardless of the adapter's {@code true} result.
     */
    protected void runDelayed(Runnable runnable) {
        var server = Server.getInstance();
        // The fake interface delay is measured in server scheduler ticks.
        server.getScheduler().scheduleDelayed(server, () -> {
            runnable.run();
            return true;
        }, 8);
    }

    /**
     * Computes a fake block coordinate opposite the controlled entity's look direction.
     * Each direction component is scaled by that axis's bounding-box length plus one
     * before the translated location is floored to block coordinates.
     */
    protected Vector3ic computeFakeBlockPos(Player player) {
        var entity = player.getControlledEntity();
        var location = entity.getLocation();
        var pos = MathUtils.getDirectionVector(location);
        var aabb = entity.getAABB();
        pos.x *= -(1 + aabb.lengthX());
        pos.y *= -(1 + aabb.lengthY());
        pos.z *= -(1 + aabb.lengthZ());
        // Flooring selects containing block coordinates even for negative world
        // positions.
        return location.add(pos, new Vector3d()).get(RoundingMode.FLOOR, new Vector3i());
    }
}
