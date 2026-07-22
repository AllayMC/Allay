package org.allaymc.server.network.protocol;

import org.allaymc.server.network.NetworkData;
import org.allaymc.server.network.processor.PacketProcessorRegistry;
import org.allaymc.server.network.protocol.v766.PacketEncoderV766;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodec;
import org.cloudburstmc.protocol.bedrock.codec.v819.Bedrock_v819;
import org.cloudburstmc.protocol.bedrock.data.definitions.ItemDefinition;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ProtocolInitializationTest {
    private static final BedrockCodec CODEC = Bedrock_v819.CODEC;

    @Test
    void publishesOnlyAfterEveryComponentMatches() {
        var data = data(CODEC.getProtocolVersion(), ClientVariant.INTERNATIONAL);
        var encoder = new PacketEncoderV766(data);
        var protocol = new TestProtocol(ClientVariant.INTERNATIONAL, data, encoder);

        assertFalse(protocol.isInitialized());
        assertThrows(IllegalStateException.class, protocol::getData);
        assertThrows(IllegalStateException.class, protocol::getEncoder);
        assertThrows(IllegalStateException.class, protocol::getFeatures);

        protocol.initialize(mock(NetworkData.class));

        assertTrue(protocol.isInitialized());
        assertSame(data, protocol.getData());
        assertSame(encoder, protocol.getEncoder());
        assertTrue(protocol.supports(ProtocolFeature.DATA_DRIVEN_UI));
        assertThrows(IllegalStateException.class, () -> protocol.initialize(mock(NetworkData.class)));
        assertThrows(IllegalStateException.class, () -> protocol.initialize(null));
    }

    @Test
    void rejectsMissingDataWithoutPublishing() {
        var protocol = new TestProtocol(ClientVariant.INTERNATIONAL, null, null);

        assertThrows(NullPointerException.class, () -> protocol.initialize(mock(NetworkData.class)));
        assertFalse(protocol.isInitialized());
        assertThrows(IllegalStateException.class, protocol::getData);
    }

    @Test
    void rejectsMissingEncoderWithoutPublishing() {
        var data = data(CODEC.getProtocolVersion(), ClientVariant.INTERNATIONAL);
        var protocol = new TestProtocol(ClientVariant.INTERNATIONAL, data, null);

        assertThrows(NullPointerException.class, () -> protocol.initialize(mock(NetworkData.class)));
        assertFalse(protocol.isInitialized());
        assertThrows(IllegalStateException.class, protocol::getEncoder);
    }

    @Test
    void rejectsDataWithAnotherProtocolVersionWithoutPublishing() {
        var data = data(CODEC.getProtocolVersion() + 1, ClientVariant.INTERNATIONAL);
        var protocol = new TestProtocol(
                ClientVariant.INTERNATIONAL,
                data,
                new PacketEncoderV766(data)
        );

        assertThrows(IllegalStateException.class, () -> protocol.initialize(mock(NetworkData.class)));
        assertFalse(protocol.isInitialized());
    }

    @Test
    void rejectsDataWithAnotherVariantWithoutPublishing() {
        var data = data(CODEC.getProtocolVersion(), ClientVariant.NETEASE);
        var protocol = new TestProtocol(
                ClientVariant.INTERNATIONAL,
                data,
                new PacketEncoderV766(data)
        );

        assertThrows(IllegalStateException.class, () -> protocol.initialize(mock(NetworkData.class)));
        assertFalse(protocol.isInitialized());
    }

    @Test
    void rejectsEncoderBackedByAnotherDataInstanceWithoutPublishing() {
        var data = data(CODEC.getProtocolVersion(), ClientVariant.INTERNATIONAL);
        var encoderData = data(CODEC.getProtocolVersion(), ClientVariant.INTERNATIONAL);
        var protocol = new TestProtocol(
                ClientVariant.INTERNATIONAL,
                data,
                new PacketEncoderV766(encoderData)
        );

        assertThrows(IllegalStateException.class, () -> protocol.initialize(mock(NetworkData.class)));
        assertFalse(protocol.isInitialized());
    }

    @Test
    void rejectsDuplicateDefinitionsBeforePublishing() {
        var data = data(CODEC.getProtocolVersion(), ClientVariant.INTERNATIONAL);
        var first = mock(ItemDefinition.class);
        var duplicate = mock(ItemDefinition.class);
        when(first.runtimeId()).thenReturn(7);
        when(duplicate.runtimeId()).thenReturn(7);
        when(data.itemDefinitions()).thenReturn(List.of(first, duplicate));
        var protocol = new TestProtocol(
                ClientVariant.INTERNATIONAL,
                data,
                new PacketEncoderV766(data)
        );

        assertThrows(IllegalArgumentException.class, () -> protocol.initialize(mock(NetworkData.class)));
        assertFalse(protocol.isInitialized());
        assertThrows(IllegalStateException.class, protocol::getEncoder);
    }

    @Test
    void concurrentInitializationPublishesAllComponentsExactlyOnce() throws Exception {
        var data = data(CODEC.getProtocolVersion(), ClientVariant.INTERNATIONAL);
        var encoder = new PacketEncoderV766(data);
        var protocol = new TestProtocol(ClientVariant.INTERNATIONAL, data, encoder);
        var source = mock(NetworkData.class);
        var readerStarted = new CountDownLatch(1);
        var initializeTogether = new CountDownLatch(1);
        var successes = new AtomicInteger();
        var duplicateAttempts = new AtomicInteger();
        var unexpectedFailure = new AtomicReference<Throwable>();

        var reader = Thread.ofPlatform().start(() -> {
            readerStarted.countDown();
            try {
                long deadline = System.nanoTime() + TimeUnit.SECONDS.toNanos(5);
                while (!protocol.isInitialized() && System.nanoTime() < deadline) {
                    Thread.onSpinWait();
                }
                assertTrue(protocol.isInitialized());
                assertSame(data, protocol.getData());
                assertSame(encoder, protocol.getEncoder());
                assertTrue(protocol.supports(ProtocolFeature.DATA_DRIVEN_UI));
            } catch (Throwable throwable) {
                unexpectedFailure.compareAndSet(null, throwable);
            }
        });
        Runnable initialize = () -> {
            try {
                initializeTogether.await();
                protocol.initialize(source);
                successes.incrementAndGet();
            } catch (IllegalStateException exception) {
                duplicateAttempts.incrementAndGet();
            } catch (Throwable throwable) {
                unexpectedFailure.compareAndSet(null, throwable);
            }
        };
        var firstInitializer = Thread.ofPlatform().start(initialize);
        var secondInitializer = Thread.ofPlatform().start(initialize);

        assertTrue(readerStarted.await(5, TimeUnit.SECONDS));
        initializeTogether.countDown();
        firstInitializer.join(TimeUnit.SECONDS.toMillis(6));
        secondInitializer.join(TimeUnit.SECONDS.toMillis(6));
        reader.join(TimeUnit.SECONDS.toMillis(6));

        assertFalse(firstInitializer.isAlive());
        assertFalse(secondInitializer.isAlive());
        assertFalse(reader.isAlive());
        assertNull(unexpectedFailure.get(), () -> String.valueOf(unexpectedFailure.get()));
        assertEquals(1, successes.get());
        assertEquals(1, duplicateAttempts.get());
    }

    private static ProtocolData data(int protocolVersion, ClientVariant variant) {
        var data = mock(ProtocolData.class);
        when(data.protocolVersion()).thenReturn(protocolVersion);
        when(data.variant()).thenReturn(variant);
        return data;
    }

    private static final class TestProtocol extends Protocol {
        private final ProtocolData data;
        private final PacketEncoder encoder;

        private TestProtocol(ClientVariant variant, ProtocolData data, PacketEncoder encoder) {
            super(CODEC, variant);
            this.data = data;
            this.encoder = encoder;
        }

        @Override
        protected void registerProcessors(PacketProcessorRegistry registry) {
        }

        @Override
        protected ProtocolData createData(NetworkData source) {
            return data;
        }

        @Override
        protected PacketEncoder createEncoder(ProtocolData data) {
            return encoder;
        }

        @Override
        protected Set<ProtocolFeature> createFeatures() {
            return Set.of(ProtocolFeature.DATA_DRIVEN_UI);
        }
    }
}
