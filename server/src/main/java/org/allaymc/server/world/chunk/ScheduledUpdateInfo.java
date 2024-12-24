package org.allaymc.server.world.chunk;

import lombok.Getter;
import org.cloudburstmc.nbt.NbtMap;

/**
 * @author daoge_cmd
 */
@Getter
public final class ScheduledUpdateInfo {

    private final int chunkXYZ;
    private final int layer;

    private int delay;

    public ScheduledUpdateInfo(int chunkXYZ, int layer, int delay) {
        this.chunkXYZ = chunkXYZ;
        this.layer = layer;
        this.delay = delay;
    }

    public static ScheduledUpdateInfo fromNBT(NbtMap nbtMap) {
        return new ScheduledUpdateInfo(
                nbtMap.getInt("ChunkXYZ"),
                nbtMap.getInt("Layer"),
                nbtMap.getInt("Delay")
        );
    }

    public NbtMap toNBT() {
        return NbtMap.builder()
                .putInt("ChunkXYZ", chunkXYZ)
                .putInt("Layer", layer)
                .putInt("Delay", delay)
                .build();
    }

    public void decreaseDelay() {
        delay--;
    }
}