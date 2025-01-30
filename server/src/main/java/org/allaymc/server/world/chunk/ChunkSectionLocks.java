package org.allaymc.server.world.chunk;

import org.allaymc.api.world.DimensionInfo;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author daoge_cmd
 */
public final class ChunkSectionLocks {

    private final int minSectionY;
    private final ReadWriteLock[] locks;

    public ChunkSectionLocks(DimensionInfo dimensionInfo) {
        this(dimensionInfo.minSectionY(), dimensionInfo.chunkSectionCount());
    }

    public ChunkSectionLocks(int minSectionY, int sectionCount) {
        this.minSectionY = minSectionY;
        this.locks = new ReentrantReadWriteLock[sectionCount];
        for (int i = 0; i < sectionCount; i++) {
            locks[i] = new ReentrantReadWriteLock();
        }
    }

    public void lockReadLockAt(int sectionY) {
        locks[sectionY - minSectionY].readLock().lock();
    }

    public void lockAllReadLocks() {
        for (var lock : locks) {
            lock.readLock().lock();
        }
    }

    public void unlockReadLockAt(int sectionY) {
        locks[sectionY - minSectionY].readLock().unlock();
    }

    public void unlockAllReadLocks() {
        for (var lock : locks) {
            lock.readLock().unlock();
        }
    }

    public void lockWriteLockAt(int sectionY) {
        locks[sectionY - minSectionY].writeLock().lock();
    }

    public void lockAllWriteLocks() {
        for (var lock : locks) {
            lock.writeLock().lock();
        }
    }

    public void unlockWriteLockAt(int sectionY) {
        locks[sectionY - minSectionY].writeLock().unlock();
    }

    public void unlockAllWriteLocks() {
        for (var lock : locks) {
            lock.writeLock().unlock();
        }
    }
}
