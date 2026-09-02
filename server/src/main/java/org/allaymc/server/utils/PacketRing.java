package org.allaymc.server.utils;

import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.player.Player;
import org.allaymc.server.network.processor.PacketProcessor;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacket;

import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicLongArray;

/**
 * A bounded, lock-free, single-consumer ring buffer for packets waiting on a world's tick thread.
 *
 * <p>Producers (network threads) claim a global sequence number with a CAS on {@link #claimCursor},
 * then fill the slot for their sequence and publish it with a release store on the slot state.
 * The consumer (world thread) walks the sequence space in strict order, so packets are delivered to
 * {@code handleSync} in the same order they were claimed. Claiming never allocates: slots are
 * pre-allocated and payload fields are plain references written before the releasing state store.</p>
 *
 * <p>The ring is bounded: when all slots are occupied, {@link #tryOffer} returns {@code false} and
 * the caller must drop the packet. This is the intended back-pressure contract — an unbounded queue
 * would keep consuming memory whenever the world thread falls behind.</p>
 *
 * <p>If a producer dies between claiming a sequence and publishing its slot, the consumer cannot
 * pass that sequence, so slot states are also validated against the stored sequence number: any
 * {@code FILLED} slot whose sequence does not match the expected one is discarded, and a claim that
 * stays open longer than {@code stallThresholdNanos} is force-released (and the wedged packet is
 * lost). This requires producers to fault within the threshold window between CAS and release
 * store, which is measured in nanoseconds.</p>
 */
@Slf4j
public final class PacketRing {

    private static final long STATE_EMPTY = 0L;
    private static final long STATE_CLAIMING = 1L;
    private static final long STATE_FILLED = 2L;

    public interface Offer {
        void onPacket(Player player, BedrockPacket packet, long receiveTime, PacketProcessor<BedrockPacket> processor);
    }

    private final int mask;
    private final AtomicLong claimCursor = new AtomicLong();
    private final AtomicLong consumeCursor = new AtomicLong();
    private final AtomicLongArray states;
    private final long[] sequenceNumbers;
    private final long[] receiveTimes;
    private final Object[] players;
    private final Object[] packets;
    private final Object[] processors;
    private final long stallThresholdNanos;

    // Watchdog state; written only by the single consumer thread.
    private long headStallSince;

    public PacketRing(int size) {
        this(size, 1_000_000_000L);
    }

    /**
     * Creates a ring buffer.
     *
     * @param size the number of slots, must be a power of two
     * @param stallThresholdNanos how long a slot may stay claimed before the consumer recovers it
     */
    public PacketRing(int size, long stallThresholdNanos) {
        if (Integer.bitCount(size) != 1 || size < 8) {
            throw new IllegalArgumentException("Size must be a power of two, got " + size);
        }
        this.mask = size - 1;
        this.states = new AtomicLongArray(size);
        this.sequenceNumbers = new long[size];
        this.receiveTimes = new long[size];
        this.players = new Object[size];
        this.packets = new Object[size];
        this.processors = new Object[size];
        this.stallThresholdNanos = stallThresholdNanos;
    }

    /**
     * Offers a packet to the ring. The packet is only published when all packets claimed before it
     * have been consumed, so producers may wait briefly here while a slot is still in use.
     *
     * @return {@code true} if the packet was accepted; {@code false} if the ring is full, in which
     * case the caller must handle the packet as dropped
     */
    public boolean tryOffer(Player player, BedrockPacket packet, long receiveTime, PacketProcessor<BedrockPacket> processor) {
        long seq;
        do {
            seq = this.claimCursor.get();
            if (seq - this.consumeCursor.get() >= size()) {
                return false;
            }
        } while (!this.claimCursor.compareAndSet(seq, seq + 1L));

        int index = (int) (seq & this.mask);
        while (!this.states.compareAndSet(index, STATE_EMPTY, STATE_CLAIMING)) {
            Thread.onSpinWait();
        }
        this.players[index] = player;
        this.packets[index] = packet;
        this.processors[index] = processor;
        this.receiveTimes[index] = receiveTime;
        this.sequenceNumbers[index] = seq;
        this.states.set(index, STATE_FILLED);
        return true;
    }

    /**
     * Consumes up to {@code maxCount} entries in global sequence order, invoking {@code offer} for
     * each one on the calling thread. Must only be called from the single consumer thread.
     *
     * @param maxCount the maximum number of entries to consume
     * @param offer the consumer callback
     * @return the number of entries consumed
     */
    public int drain(int maxCount, Offer offer) {
        int consumed = 0;
        while (consumed < maxCount) {
            long next = this.consumeCursor.get();
            int index = (int) (next & this.mask);
            long state = this.states.get(index);

            if (state == STATE_EMPTY) {
                break;
            }
            if (state == STATE_CLAIMING) {
                long now = System.nanoTime();
                if (this.headStallSince == 0) {
                    this.headStallSince = now;
                    break;
                }
                if (now - this.headStallSince < this.stallThresholdNanos) {
                    break;
                }
                // The claiming producer has not published for longer than the threshold; assume it
                // is wedged or dead and recover the slot. The packet for this sequence is lost.
                if (this.states.compareAndSet(index, STATE_CLAIMING, STATE_EMPTY)) {
                    log.warn("Packet ring recovered a stalled claim at sequence {}", next);
                    this.headStallSince = 0;
                }
                continue;
            }

            // FILLED: only accept the slot if its stored sequence matches the expected one.
            // A mismatch means a late, stale write from a recovered claim reached the slot after
            // it was already reused; discard that write and move on.
            if (this.sequenceNumbers[index] != next) {
                this.states.compareAndSet(index, STATE_FILLED, STATE_EMPTY);
                this.consumeCursor.lazySet(next + 1L);
                continue;
            }

            this.headStallSince = 0;
            offer.onPacket(
                    (Player) this.players[index],
                    (BedrockPacket) this.packets[index],
                    this.receiveTimes[index],
                    (PacketProcessor<BedrockPacket>) this.processors[index]
            );
            this.states.set(index, STATE_EMPTY);
            this.consumeCursor.lazySet(next + 1L);
            consumed++;
        }
        return consumed;
    }

    public int size() {
        return this.mask + 1;
    }

    /**
     * Returns the total number of sequences claimed by producers so far.
     */
    public long getClaimed() {
        return this.claimCursor.get();
    }

    /**
     * Returns the number of sequences consumed so far.
     */
    public long getConsumed() {
        return this.consumeCursor.get();
    }

    /**
     * Test-only hook: force the slot at the current consumer head into the claiming state as if a
     * producer had died mid-claim, so the recovery path can be exercised.
     */
    void simulateStalledClaimForTest() {
        long next = this.consumeCursor.get();
        this.states.compareAndSet((int) (next & this.mask), STATE_EMPTY, STATE_CLAIMING);
    }
}