package org.allaymc.server.network.processor;

/**
 * Marks a packet processor as latency critical.
 *
 * <p>Packets handled by a processor implementing this marker are drained from the
 * world's packet queue ahead of regular packets, so actions like item release
 * (bow shots) or block placement are not delayed behind bulk requests such as
 * chunk loading.</p>
 */
public interface LatencyCriticalPacketProcessor {
}