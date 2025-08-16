package org.allaymc.server.world.generator;

import org.allaymc.api.annotation.NotThreadSafe;
import org.allaymc.api.utils.HashUtils;

import java.util.HashSet;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BiPredicate;

/**
 * @author daoge_cmd
 */
@NotThreadSafe
public final class ChunkLocks {
    private final Set<Long> locks;

    public ChunkLocks() {
        this.locks = new HashSet<>();
    }

    public boolean lock(int chunkX, int chunkZ) {
        return lock(chunkX, chunkZ, 0);
    }

    public boolean lock(int chunkX, int chunkZ, int range) {
        if (!checkRanged(chunkX, chunkZ, range, (x, z) -> !locks.contains(HashUtils.hashXZ(x, z)))) {
            return false;
        }

        forEachRanged(chunkX, chunkZ, range, (x, z) -> locks.add(HashUtils.hashXZ(x, z)));
        return true;
    }

    public void unlock(int chunkX, int chunkZ) {
        unlock(chunkX, chunkZ, 0);
    }

    public void unlock(int chunkX, int chunkZ, int range) {
        forEachRanged(chunkX, chunkZ, range, (x, z) -> locks.remove(HashUtils.hashXZ(x, z)));
    }

    public boolean isLocked(int chunkX, int chunkZ) {
        return isLocked(chunkX, chunkZ, 0);
    }

    public boolean isLocked(int chunkX, int chunkZ, int range) {
        return checkRanged(chunkX, chunkZ, range, (x, z) -> locks.contains(HashUtils.hashXZ(x, z)));
    }

    private void forEachRanged(int chunkX, int chunkZ, int range, BiConsumer<Integer, Integer> consumer) {
        if (range == 0) {
            consumer.accept(chunkX, chunkZ);
            return;
        }

        for (int i = -range; i <= range; i++) {
            for (int j = -range; j <= range; j++) {
                consumer.accept(chunkX + i, chunkZ + j);
            }
        }
    }

    private boolean checkRanged(int chunkX, int chunkZ, int range, BiPredicate<Integer, Integer> predicator) {
        if (range == 0) {
            return predicator.test(chunkX, chunkZ);
        }

        for (int i = -range; i <= range; i++) {
            for (int j = -range; j <= range; j++) {
                if (!predicator.test(chunkX + i, chunkZ + j)) {
                    return false;
                }
            }
        }

        return true;
    }
}
