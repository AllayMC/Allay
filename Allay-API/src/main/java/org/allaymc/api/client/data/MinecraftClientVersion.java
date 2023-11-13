package org.allaymc.api.client.data;

import com.google.common.base.Preconditions;
import org.jetbrains.annotations.NotNull;

public record MinecraftClientVersion(int major, int bigMinor, int smallMinor, int patch, int build) {
    @NotNull
    public static MinecraftClientVersion from(int[] versions) {
        if (versions.length == 0) {
            return new MinecraftClientVersion(0, 0, 0, 0, 0);
        }
        Preconditions.checkArgument(versions.length == 5);
        return new MinecraftClientVersion(versions[0], versions[1], versions[2], versions[3], versions[4]);
    }

    public int[] toArray() {
        return new int[]{major, bigMinor, smallMinor, patch, build};
    }
}
