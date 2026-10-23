package org.allaymc.server.world.chunk;

import org.allaymc.api.world.dimension.DimensionType;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * {@code ChunkSectionLocks} provides
 * one reentrant read-write lock for each section in a dimension's
 * vertical range. Absolute section coordinates are offset into the lock array;
 * whole-column operations acquire locks in increasing section order, and
 * callers pair every acquisition with a matching release.
 *
 * @author daoge_cmd
 */
public final class ChunkSectionLocks {

    /**
     * Absolute Y of the first section lock.
     */
    private final int minSectionY;
    /**
     * {@code locks} retains the independent section locks for this instance's
     * lifetime. Callers must release each acquisition on the acquiring thread.
     */
    private final ReadWriteLock[] locks;

    /**
     * Creates one independent reentrant lock for every section in the supplied
     * dimension. The resulting coordinate range follows that dimension's
     * minimum section Y and total section count.
     */
    public ChunkSectionLocks(DimensionType dimensionType) {
        this(dimensionType.minSectionY(), dimensionType.chunkSectionCount());
    }

    /**
     * Creates independent reentrant locks covering the requested contiguous
     * section range. Callers must use coordinates from the minimum through
     * minimum plus count minus one and pair acquisitions with releases.
     */
    public ChunkSectionLocks(int minSectionY, int sectionCount) {
        this.minSectionY = minSectionY;
        this.locks = new ReentrantReadWriteLock[sectionCount];
        for (int i = 0; i < sectionCount; i++) {
            locks[i] = new ReentrantReadWriteLock();
        }
    }

    /**
     * Acquires the read lock for a valid absolute section coordinate. The
     * caller must release the matching lock on the same thread, including when
     * its protected operation fails.
     */
    public void lockReadLockAt(int sectionY) {
        locks[sectionY - minSectionY].readLock().lock();
    }

    /**
     * Acquires every section read lock in increasing section order for shared
     * readers. The caller must release the corresponding locks on this thread
     * after its protected operation completes or fails.
     */
    public void lockAllReadLocks() {
        for (var lock : locks) {
            lock.readLock().lock();
        }
    }

    /**
     * Releases one read lock acquisition for a valid absolute section
     * coordinate. The current thread must own the matching acquisition; this
     * method does not track or release other sections.
     */
    public void unlockReadLockAt(int sectionY) {
        locks[sectionY - minSectionY].readLock().unlock();
    }

    /**
     * Releases one read lock acquisition for each section in increasing order.
     * The current thread must own all corresponding acquisitions, normally
     * obtained through the matching whole-column lock method.
     */
    public void unlockAllReadLocks() {
        for (var lock : locks) {
            lock.readLock().unlock();
        }
    }

    /**
     * Acquires the write lock for a valid absolute section coordinate. The
     * caller must release the matching lock on the same thread, including when
     * its protected operation fails.
     */
    public void lockWriteLockAt(int sectionY) {
        locks[sectionY - minSectionY].writeLock().lock();
    }

    /**
     * Acquires every section write lock in increasing section order for
     * exclusive writers. The caller must release the corresponding locks on
     * this thread after its protected operation completes or fails.
     */
    public void lockAllWriteLocks() {
        for (var lock : locks) {
            lock.writeLock().lock();
        }
    }

    /**
     * Releases one write lock acquisition for a valid absolute section
     * coordinate. The current thread must own the matching acquisition; this
     * method does not track or release other sections.
     */
    public void unlockWriteLockAt(int sectionY) {
        locks[sectionY - minSectionY].writeLock().unlock();
    }

    /**
     * Releases one write lock acquisition for each section in increasing order.
     * The current thread must own all corresponding acquisitions, normally
     * obtained through the matching whole-column lock method.
     */
    public void unlockAllWriteLocks() {
        for (var lock : locks) {
            lock.writeLock().unlock();
        }
    }
}
