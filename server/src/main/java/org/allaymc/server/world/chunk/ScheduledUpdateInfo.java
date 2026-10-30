package org.allaymc.server.world.chunk;

import lombok.Getter;
import org.allaymc.api.block.type.BlockTypes;
import org.cloudburstmc.nbt.NbtMap;
import org.joml.Vector3i;
import org.joml.Vector3ic;

/**
 * {@code ScheduledUpdateInfo} carries
 * an absolute block position and world tick for a scheduled block
 * update. Storage records retain a vanilla block-state field for compatibility,
 * while Allay resolves the actual block state when the scheduled update
 * executes.
 *
 * @author daoge_cmd
 */
@Getter
public final class ScheduledUpdateInfo {

    /**
     * {@code pos} holds the dimension block position without copying the
     * supplied vector. External mutation changes the target of this update.
     */
    private final Vector3ic pos;
    /**
     * Absolute world tick deadline.
     */
    private final long time;

    /**
     * Creates an update descriptor retaining the supplied position and absolute
     * world tick. The position is not copied, so callers must keep its
     * coordinates stable while the update is queued.
     */
    public ScheduledUpdateInfo(Vector3ic pos, long time) {
        this.pos = pos;
        this.time = time;
    }

    /**
     * Creates an update descriptor from stored block coordinates and its
     * absolute world tick. The saved block-state field is ignored because
     * execution looks up the current block at that position.
     */
    public static ScheduledUpdateInfo fromNBT(NbtMap nbt) {
        return new ScheduledUpdateInfo(
                new Vector3i(nbt.getInt("x"), nbt.getInt("y"), nbt.getInt("z")),
                nbt.getLong("time")
                // The saved state is deliberately omitted: scheduled execution resolves
                // the current block, so restoring an old state would misrepresent later changes.
        );
    }

    /**
     * Returns a new compound containing the target coordinates and absolute
     * execution tick. The compatibility block-state field is written as air
     * because Allay determines the actual state when executing the update.
     */
    public NbtMap toNBT() {
        return NbtMap.builder()
                .putInt("x", pos.x())
                .putInt("y", pos.y())
                .putInt("z", pos.z())
                .putLong("time", time)
                // Keep the vanilla-shaped field without snapshotting block identity;
                // Allay executes the update against the state present at the target later.
                .putCompound("blockState", BlockTypes.AIR.getDefaultState().getBlockStateNBT())
                .build();
    }
}