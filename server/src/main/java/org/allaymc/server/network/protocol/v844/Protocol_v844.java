package org.allaymc.server.network.protocol.v844;

import org.allaymc.api.block.data.TintMethod;
import org.allaymc.server.block.type.CustomBlockStateDefinition;
import org.allaymc.server.network.protocol.ClientVariant;
import org.allaymc.server.network.protocol.PacketEncoder;
import org.allaymc.server.network.protocol.ProtocolData;
import org.allaymc.server.network.protocol.v827.Protocol_v827;
import org.cloudburstmc.nbt.NbtMap;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodec;
import org.cloudburstmc.protocol.bedrock.codec.v844.Bedrock_v844;

/**
 * {@code Protocol_v844} configures wire protocol 844 within the international
 * protocol inheritance chain. Custom block materials switch to packed boolean
 * flags here, while processor registrations and other payload generation
 * continue through the previous international implementation. Initialization
 * creates the v844 encoder from the completed protocol data.
 */
public class Protocol_v844 extends Protocol_v827 {
    /**
     * Creates an uninitialized international protocol using the v844 codec. The
     * constructor records its identity without building payloads or processors;
     * callers must invoke {@code initialize} after all subtype construction has
     * finished.
     */
    public Protocol_v844() {
        this(Bedrock_v844.CODEC, ClientVariant.INTERNATIONAL);
    }

    /**
     * Creates an uninitialized instance retaining the supplied codec and client
     * distribution. Subclasses use this constructor to reuse implementation
     * while keeping their own identity; runtime data is built only by the later
     * initialization call.
     */
    protected Protocol_v844(BedrockCodec codec, ClientVariant variant) {
        super(codec, variant);
    }

    /**
     * Encodes material flags using the modern packed-boolean representation.
     * The shared encoder preserves the three supported flags in their assigned
     * bits and retains the supplied material values and tint method.
     */
    @Override
    protected NbtMap encodeMaterialInstance(
            CustomBlockStateDefinition.MaterialInstance material,
            TintMethod tintMethod
    ) {
        return encodePackedMaterialInstance(material, tintMethod);
    }

    /**
     * Creates the {@code PacketEncoder_v844} implementation using the exact
     * supplied data instance. The enclosing initialization lifecycle verifies
     * this shared identity before publishing the protocol for connection use.
     */
    @Override
    protected PacketEncoder createEncoder(ProtocolData data) {
        return new PacketEncoder_v844(data);
    }
}
