package org.allaymc.server.world.chunk;

import lombok.Getter;
import org.allaymc.api.block.type.BlockTypes;
import org.cloudburstmc.nbt.NbtMap;
import org.joml.Vector3i;
import org.joml.Vector3ic;

/**
 * @author daoge_cmd
 */
@Getter
public final class ScheduledUpdateInfo {

    // The world pos of the block that the scheduled update is for
    private final Vector3ic pos;
    // The time that the scheduled update should be executed, it is calculated
    // by adding the delay to the current world tick
    private final long time;

    public ScheduledUpdateInfo(Vector3ic pos, long time) {
        this.pos = pos;
        this.time = time;
    }

    public static ScheduledUpdateInfo fromNBT(NbtMap nbt) {
        return new ScheduledUpdateInfo(
                new Vector3i(nbt.getInt("x"), nbt.getInt("y"), nbt.getInt("z")),
                nbt.getLong("time")
                // blockState is not used, just ignore it
        );
    }

    public NbtMap toNBT() {
        return NbtMap.builder()
                .putInt("x", pos.x())
                .putInt("y", pos.y())
                .putInt("z", pos.z())
                .putLong("time", time)
                // Written in vanilla, but is not used in Allay. We just write an air here
                .putCompound("blockState", BlockTypes.AIR.getDefaultState().getBlockStateNBT())
                .build();
    }
}