package org.allaymc.server.network.protocol;

import org.allaymc.server.network.NetworkData;
import org.allaymc.server.network.processor.PacketProcessorHolder;
import org.allaymc.server.network.processor.PacketProcessorRegistry;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodec;
import org.cloudburstmc.protocol.bedrock.data.definitions.BlockDefinition;
import org.cloudburstmc.protocol.bedrock.data.definitions.ItemDefinition;
import org.cloudburstmc.protocol.bedrock.definition.DefinitionRegistry;
import org.cloudburstmc.protocol.bedrock.definition.SimpleDefinitionRegistry;

import java.util.Objects;
import java.util.Set;

/**
 * An immutable, fully initialized Bedrock protocol implementation.
 *
 * <p>Construction only records the protocol identity. {@link #initialize(NetworkData)} performs
 * the overridable initialization steps after the complete subtype has been constructed.</p>
 */
public abstract class Protocol {
    private final BedrockCodec codec;
    private final ClientVariant variant;

    private PacketProcessorRegistry processorRegistry;
    private ProtocolData data;
    private PacketEncoder encoder;
    private Set<ProtocolFeature> features;
    private DefinitionRegistry<ItemDefinition> itemDefinitionRegistry;
    private DefinitionRegistry<BlockDefinition> blockDefinitionRegistry;
    private volatile boolean initialized;

    protected Protocol(BedrockCodec codec, ClientVariant variant) {
        this.codec = Objects.requireNonNull(codec, "codec");
        this.variant = Objects.requireNonNull(variant, "variant");
    }

    /**
     * Completes this protocol's one-time initialization.
     *
     * @param source frozen global network source data
     */
    public final synchronized void initialize(NetworkData source) {
        if (initialized) {
            throw new IllegalStateException("Protocol is already initialized: " + this);
        }
        Objects.requireNonNull(source, "source");

        var registry = new PacketProcessorRegistry();
        registerProcessors(registry);
        registry.freeze();

        var protocolData = Objects.requireNonNull(createData(source), "createData returned null");
        if (protocolData.protocolVersion() != getProtocolVersion() || protocolData.variant() != variant) {
            throw new IllegalStateException(
                    "Protocol data identity does not match " + this + ": " +
                    protocolData.variant() + ", v" + protocolData.protocolVersion()
            );
        }
        var itemDefinitions = SimpleDefinitionRegistry.<ItemDefinition>builder()
                .addAll(protocolData.itemDefinitions())
                .build();
        var blockDefinitions = SimpleDefinitionRegistry.<BlockDefinition>builder()
                .addAll(protocolData.blockDefinitions())
                .build();
        var packetEncoder = Objects.requireNonNull(createEncoder(protocolData), "createEncoder returned null");
        if (packetEncoder.getData() != protocolData) {
            throw new IllegalStateException("Packet encoder does not reference the protocol's data instance: " + this);
        }
        var protocolFeatures = Set.copyOf(createFeatures());

        this.processorRegistry = registry;
        this.data = protocolData;
        this.encoder = packetEncoder;
        this.features = protocolFeatures;
        this.itemDefinitionRegistry = itemDefinitions;
        this.blockDefinitionRegistry = blockDefinitions;
        this.initialized = true;
    }

    protected abstract void registerProcessors(PacketProcessorRegistry registry);

    protected abstract ProtocolData createData(NetworkData source);

    protected abstract PacketEncoder createEncoder(ProtocolData data);

    protected Set<ProtocolFeature> createFeatures() {
        return Set.of();
    }

    public final BedrockCodec getCodec() {
        return codec;
    }

    public final int getProtocolVersion() {
        return codec.getProtocolVersion();
    }

    public final String getMinecraftVersion() {
        return codec.getMinecraftVersion();
    }

    public final ClientVariant getVariant() {
        return variant;
    }

    public final ProtocolData getData() {
        ensureInitialized();
        return data;
    }

    public final PacketEncoder getEncoder() {
        ensureInitialized();
        return encoder;
    }

    public final PacketProcessorHolder createProcessorHolder() {
        ensureInitialized();
        return new PacketProcessorHolder(processorRegistry);
    }

    public final boolean supports(ProtocolFeature feature) {
        ensureInitialized();
        return features.contains(feature);
    }

    public final Set<ProtocolFeature> getFeatures() {
        ensureInitialized();
        return features;
    }

    final DefinitionRegistry<ItemDefinition> getItemDefinitionRegistry() {
        ensureInitialized();
        return itemDefinitionRegistry;
    }

    final DefinitionRegistry<BlockDefinition> getBlockDefinitionRegistry() {
        ensureInitialized();
        return blockDefinitionRegistry;
    }

    public final boolean isInitialized() {
        return initialized;
    }

    private void ensureInitialized() {
        if (!initialized) {
            throw new IllegalStateException("Protocol has not been initialized: " + this);
        }
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + '[' + variant.name() + ", v" + getProtocolVersion() + ']';
    }
}
