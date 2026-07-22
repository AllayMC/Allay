package org.allaymc.server.network.protocol;

import org.allaymc.server.network.NetworkData;
import org.allaymc.server.network.processor.PacketProcessorRegistry;
import org.allaymc.server.network.protocol.v766.PacketEncoder_v766;
import org.allaymc.server.network.protocol.v766.Protocol_v766_NetEase;
import org.allaymc.server.network.protocol.v819.Protocol_v819;
import org.allaymc.server.network.protocol.v819.Protocol_v819_NetEase;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodec;
import org.cloudburstmc.protocol.bedrock.codec.v818.Bedrock_v818;
import org.cloudburstmc.protocol.bedrock.codec.v819.Bedrock_v819;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ProtocolRegistryTest {
    @Test
    void defaultRegistryPublishesOnlySupportedVariantVersionPairs() {
        var registry = ProtocolRegistry.createDefault(mock(NetworkData.class));

        assertNull(registry.resolve(ClientVariant.INTERNATIONAL, 766));
        assertInstanceOf(Protocol_v766_NetEase.class, registry.resolve(ClientVariant.NETEASE, 766));
        assertInstanceOf(Protocol_v819.class, registry.resolve(ClientVariant.INTERNATIONAL, 819));
        assertInstanceOf(Protocol_v819_NetEase.class, registry.resolve(ClientVariant.NETEASE, 819));
        assertNull(registry.resolve(ClientVariant.NETEASE, 818));
        assertEquals(818, registry.getLowest(ClientVariant.INTERNATIONAL).getProtocolVersion());
        assertEquals(1001, registry.getLatest(ClientVariant.INTERNATIONAL).getProtocolVersion());
        assertEquals(766, registry.getLowest(ClientVariant.NETEASE).getProtocolVersion());
        assertEquals(819, registry.getLatest(ClientVariant.NETEASE).getProtocolVersion());
    }

    @Test
    void resolvesOnlyTheExactVariantAndVersion() {
        var internationalV818 = initializedProtocol(Bedrock_v818.CODEC, ClientVariant.INTERNATIONAL);
        var internationalV819 = initializedProtocol(Bedrock_v819.CODEC, ClientVariant.INTERNATIONAL);
        var netEaseV819 = initializedProtocol(Bedrock_v819.CODEC, ClientVariant.NETEASE);
        var registry = new ProtocolRegistry();
        registry.register(internationalV819);
        registry.register(netEaseV819);
        registry.register(internationalV818);

        assertThrows(
                IllegalStateException.class,
                () -> registry.resolve(ClientVariant.INTERNATIONAL, 819)
        );

        registry.freeze();

        assertSame(internationalV818, registry.resolve(ClientVariant.INTERNATIONAL, 818));
        assertSame(internationalV819, registry.resolve(ClientVariant.INTERNATIONAL, 819));
        assertSame(netEaseV819, registry.resolve(ClientVariant.NETEASE, 819));
        assertNull(registry.resolve(ClientVariant.NETEASE, 818));
        assertNull(registry.resolve(ClientVariant.INTERNATIONAL, 820));
        assertEquals(List.of(internationalV818, internationalV819), registry.getSupported(ClientVariant.INTERNATIONAL));
        assertEquals(List.of(netEaseV819), registry.getSupported(ClientVariant.NETEASE));
        assertSame(internationalV818, registry.getLowest(ClientVariant.INTERNATIONAL));
        assertSame(internationalV819, registry.getLatest(ClientVariant.INTERNATIONAL));
    }

    @Test
    void rejectsAnUninitializedProtocol() {
        var registry = new ProtocolRegistry();
        var protocol = protocol(Bedrock_v819.CODEC, ClientVariant.INTERNATIONAL);

        assertThrows(IllegalArgumentException.class, () -> registry.register(protocol));
        assertFalse(registry.isFrozen());
    }

    @Test
    void rejectsDuplicateVariantAndVersionWithoutReplacingTheOriginal() {
        var original = initializedProtocol(Bedrock_v819.CODEC, ClientVariant.INTERNATIONAL);
        var duplicate = initializedProtocol(Bedrock_v819.CODEC, ClientVariant.INTERNATIONAL);
        var registry = new ProtocolRegistry();
        registry.register(original);

        assertThrows(IllegalArgumentException.class, () -> registry.register(duplicate));

        registry.freeze();
        assertSame(original, registry.resolve(ClientVariant.INTERNATIONAL, 819));
    }

    @Test
    void freezeRejectsEmptyRegistryAndPreventsFurtherRegistration() {
        var registry = new ProtocolRegistry();

        assertThrows(IllegalStateException.class, registry::freeze);
        assertFalse(registry.isFrozen());

        var protocol = initializedProtocol(Bedrock_v819.CODEC, ClientVariant.INTERNATIONAL);
        registry.register(protocol);
        registry.freeze();

        assertTrue(registry.isFrozen());
        assertThrows(IllegalStateException.class, () -> registry.register(
                initializedProtocol(Bedrock_v819.CODEC, ClientVariant.NETEASE)
        ));
        assertThrows(
                UnsupportedOperationException.class,
                () -> registry.getSupported(ClientVariant.INTERNATIONAL).clear()
        );
        assertThrows(UnsupportedOperationException.class, () -> registry.getProtocols().clear());
        assertThrows(IllegalStateException.class, registry::freeze);
    }

    @Test
    void freezeAtomicallyPublishesOneCompleteSnapshotToConcurrentReaders() throws Exception {
        var international = initializedProtocol(Bedrock_v819.CODEC, ClientVariant.INTERNATIONAL);
        var netEase = initializedProtocol(Bedrock_v819.CODEC, ClientVariant.NETEASE);
        var registry = new ProtocolRegistry();
        registry.register(international);
        registry.register(netEase);
        var readerStarted = new CountDownLatch(1);
        var readerFailure = new AtomicReference<Throwable>();
        var reader = Thread.ofPlatform().start(() -> {
            readerStarted.countDown();
            try {
                long deadline = System.nanoTime() + TimeUnit.SECONDS.toNanos(5);
                while (!registry.isFrozen() && System.nanoTime() < deadline) {
                    Thread.onSpinWait();
                }
                assertTrue(registry.isFrozen());
                assertSame(international, registry.resolve(ClientVariant.INTERNATIONAL, 819));
                assertSame(netEase, registry.resolve(ClientVariant.NETEASE, 819));
                assertEquals(List.of(international), registry.getSupported(ClientVariant.INTERNATIONAL));
                assertEquals(List.of(netEase), registry.getSupported(ClientVariant.NETEASE));
                assertEquals(2, registry.getProtocols().size());
            } catch (Throwable throwable) {
                readerFailure.set(throwable);
            }
        });

        assertTrue(readerStarted.await(5, TimeUnit.SECONDS));
        registry.freeze();
        reader.join(TimeUnit.SECONDS.toMillis(6));

        assertFalse(reader.isAlive());
        assertNull(readerFailure.get(), () -> String.valueOf(readerFailure.get()));
    }

    @Test
    void defaultRegistryRejectsInvalidAndRepeatedInstallation() throws Exception {
        Field defaultRegistryField = ProtocolRegistry.class.getDeclaredField("defaultRegistry");
        defaultRegistryField.setAccessible(true);

        synchronized (ProtocolRegistry.class) {
            var originalDefault = defaultRegistryField.get(null);
            try {
                defaultRegistryField.set(null, null);
                assertFalse(ProtocolRegistry.hasDefault());
                assertThrows(IllegalStateException.class, ProtocolRegistry::getDefault);
                assertThrows(
                        IllegalArgumentException.class,
                        () -> ProtocolRegistry.installDefault(new ProtocolRegistry())
                );

                var registry = new ProtocolRegistry();
                registry.register(initializedProtocol(Bedrock_v819.CODEC, ClientVariant.INTERNATIONAL));
                registry.freeze();
                ProtocolRegistry.installDefault(registry);

                assertTrue(ProtocolRegistry.hasDefault());
                assertSame(registry, ProtocolRegistry.getDefault());
                assertThrows(IllegalStateException.class, () -> ProtocolRegistry.installDefault(registry));
                assertSame(registry, ProtocolRegistry.getDefault());
            } finally {
                defaultRegistryField.set(null, originalDefault);
            }
        }
    }

    private static Protocol initializedProtocol(BedrockCodec codec, ClientVariant variant) {
        var protocol = protocol(codec, variant);
        protocol.initialize(mock(NetworkData.class));
        return protocol;
    }

    private static Protocol protocol(BedrockCodec codec, ClientVariant variant) {
        var data = mock(ProtocolData.class);
        when(data.protocolVersion()).thenReturn(codec.getProtocolVersion());
        when(data.variant()).thenReturn(variant);
        return new TestProtocol(codec, variant, data);
    }

    private static final class TestProtocol extends Protocol {
        private final ProtocolData data;

        private TestProtocol(BedrockCodec codec, ClientVariant variant, ProtocolData data) {
            super(codec, variant);
            this.data = data;
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
            return new PacketEncoder_v766(data);
        }
    }
}
