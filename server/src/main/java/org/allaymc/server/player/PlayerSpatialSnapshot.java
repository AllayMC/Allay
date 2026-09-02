package org.allaymc.server.player;

import lombok.Getter;

/**
 * A snapshot of a player's most recently declared spatial state.
 *
 * <p>The fields are written from two places: the network thread stores the position and rotation
 * the client declared in the latest auth input packet, and the world thread re-refreshes the
 * snapshot from the authoritative entity state once per tick. Both writers publish plain
 * assignments to volatile fields, so readers may observe either value and must treat the snapshot
 * as stale by up to one tick.</p>
 *
 * <p>Snapshots are for rejection-only decisions, e.g. reach pre-filtering: a decision based on a
 * stale snapshot is acceptable (worst case the packet proceeds to the world thread, which
 * re-checks), but a snapshot must never be used to approve a packet that skipped synchronous
 * validation.</p>
 */
@Getter
public final class PlayerSpatialSnapshot {

    private volatile double x, y, z;
    private volatile double yaw, pitch;
    private volatile long tick;
    private volatile int handSlot;

    /**
     * Replaces the position and rotation of the snapshot.
     *
     * @param x the x coordinate, in world units
     * @param y the y coordinate, in world units
     * @param z the z coordinate, in world units
     * @param yaw the horizontal rotation in degrees
     * @param pitch the vertical rotation in degrees
     * @param tick the tick at which the state was recorded
     */
    public void update(double x, double y, double z, double yaw, double pitch, long tick) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.yaw = yaw;
        this.pitch = pitch;
        this.tick = tick;
    }

    /**
     * Replaces the hand slot of the snapshot.
     *
     * @param handSlot the index of the hotbar slot
     */
    public void updateHandSlot(int handSlot) {
        this.handSlot = handSlot;
    }
}