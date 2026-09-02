package org.allaymc.server.utils;

import lombok.SneakyThrows;
import org.allaymc.api.player.Player;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacket;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Joseph00o
 */
class PacketRingTest {

    private static final int PRODUCERS = 4;
    private static final int PER_PRODUCER = 1_000;

    private final Player player = Mockito.mock(Player.class);
    private final BedrockPacket packet = Mockito.mock(BedrockPacket.class);

    @Test
    void testSingleProducerDeliversInOrder() {
        var ring = new PacketRing(64);
        for (int i = 0; i < 50; i++) {
            assertTrue(ring.tryOffer(player, packet, i, null));
        }

        var delivered = new ArrayList<Long>();
        assertEquals(50, ring.drain(64, (p, pk, receiveTime, processor) -> delivered.add(receiveTime)));
        assertEquals(0, ring.drain(64, (p, pk, receiveTime, processor) -> fail("ring should be empty")));
        for (int i = 0; i < 50; i++) {
            assertEquals(i, delivered.get(i));
        }
    }

    @Test
    void testBackpressureReturnsFalseWhenFull() {
        var ring = new PacketRing(64);
        for (int i = 0; i < 64; i++) {
            assertTrue(ring.tryOffer(player, packet, i, null));
        }
        assertFalse(ring.tryOffer(player, packet, 64, null));

        // After draining, offers are accepted again
        assertEquals(64, ring.drain(64, (p, pk, receiveTime, processor) -> {
        }));
        assertTrue(ring.tryOffer(player, packet, 64, null));
    }

    @SneakyThrows
    @Test
    @Timeout(value = 30, unit = TimeUnit.SECONDS)
    void testMultiProducerNoLossAndPerProducerOrder() {
        var ring = new PacketRing(16_384);
        var delivered = Collections.synchronizedList(new ArrayList<Long>());
        var producersFinished = new CountDownLatch(PRODUCERS);
        var consumerDone = new CountDownLatch(1);

        try (ExecutorService pool = Executors.newFixedThreadPool(PRODUCERS + 1)) {
            for (int producerId = 0; producerId < PRODUCERS; producerId++) {
                int id = producerId;
                pool.submit(() -> {
                    try {
                        for (int k = 0; k < PER_PRODUCER; k++) {
                            long value = (long) id * PER_PRODUCER + k;
                            // Retry on back-pressure like the real caller would not; exercises full-ring offer
                            while (!ring.tryOffer(player, packet, value, null)) {
                                Thread.yield();
                            }
                        }
                    } finally {
                        producersFinished.countDown();
                    }
                });
            }

            pool.submit(() -> {
                try {
                    while (producersFinished.getCount() != 0 || ring.getClaimed() != ring.getConsumed()) {
                        ring.drain(512, (p, pk, receiveTime, processor) -> delivered.add(receiveTime));
                    }
                } finally {
                    consumerDone.countDown();
                }
            });

            assertTrue(consumerDone.await(30, TimeUnit.SECONDS));
        }

        assertEquals(PRODUCERS * PER_PRODUCER, delivered.size());
        assertEquals(delivered.size(), new HashSet<>(delivered).size(), "each packet must be delivered exactly once");

        for (int producerId = 0; producerId < PRODUCERS; producerId++) {
            long previous = -1;
            for (long value : delivered) {
                if (value / PER_PRODUCER == producerId) {
                    assertTrue(value > previous, "packets of producer " + producerId + " must arrive in offer order");
                    previous = value;
                }
            }
            assertTrue(previous != -1, "producer " + producerId + " must have delivered packets");
        }
    }

    @SneakyThrows
    @Test
    void testWatchdogRecoversStalledClaim() {
        var ring = new PacketRing(16, 5_000_000L);
        for (int i = 0; i < 4; i++) {
            ring.tryOffer(player, packet, i, null);
        }
        assertEquals(4, ring.drain(16, (p, pk, receiveTime, processor) -> {
        }));

        // Simulate a producer that claimed the head slot and died before publishing it
        ring.simulateStalledClaimForTest();

        // The first drain sees the claim, records the stall time and backs off
        assertEquals(0, ring.drain(16, (p, pk, receiveTime, processor) -> fail("must not deliver while stalled")));

        // After the stall threshold the consumer force-recovers the slot
        Thread.sleep(20);
        assertEquals(0, ring.drain(16, (p, pk, receiveTime, processor) -> fail("must not deliver while stalled")));

        // The ring must be usable again
        assertTrue(ring.tryOffer(player, packet, 42, null));
        assertEquals(1, ring.drain(16, (p, pk, receiveTime, processor) -> assertEquals(42, receiveTime)));
    }
}
