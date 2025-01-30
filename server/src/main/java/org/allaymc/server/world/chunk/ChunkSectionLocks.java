package org.allaymc.server.world.chunk;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author daoge_cmd
 */
public final class ChunkSectionLocks {

    private final int minSectionY;
    private final ReadWriteLock[] locks;

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

    public void unlockReadLockAt(int sectionY) {
        locks[sectionY - minSectionY].readLock().unlock();
    }

    public void lockWriteLockAt(int sectionY) {
        locks[sectionY - minSectionY].writeLock().lock();
    }

    public void unlockWriteLockAt(int sectionY) {
        locks[sectionY - minSectionY].writeLock().unlock();
    }
}
