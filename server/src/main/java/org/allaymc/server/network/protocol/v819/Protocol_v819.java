package org.allaymc.server.network.protocol.v819;

import org.allaymc.server.network.protocol.ClientVariant;
import org.allaymc.server.network.protocol.PacketEncoder;
import org.allaymc.server.network.protocol.ProtocolData;
import org.allaymc.server.network.protocol.v818.Protocol_v818;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodec;
import org.cloudburstmc.protocol.bedrock.codec.v819.Bedrock_v819;

/**
 * {@code Protocol_v819} configures wire protocol 819 within the international
 * protocol inheritance chain. The {@code Protocol_v818} implementation supplies
 * processor registrations and payload generation that are not overridden here.
 * Initialization selects the {@code PacketEncoder_v819} encoder while retaining
 * the exact codec and client distribution supplied at construction.
 */
public class Protocol_v819 extends Protocol_v818 {
    /**
     * Creates an uninitialized international protocol using the v819 codec. The
     * constructor records its identity without building payloads or processors;
     * callers must invoke {@code initialize} after all subtype construction has
     * finished.
     */
    public Protocol_v819() {
        this(Bedrock_v819.CODEC, ClientVariant.INTERNATIONAL);
    }

    /**
     * Creates an uninitialized instance retaining the supplied codec and client
     * distribution. Subclasses use this constructor to reuse implementation
     * while keeping their own identity; runtime data is built only by the later
     * initialization call.
     */
    protected Protocol_v819(BedrockCodec codec, ClientVariant variant) {
        super(codec, variant);
    }

    /**
     * Creates the {@code PacketEncoder_v819} implementation using the exact
     * supplied data instance. The enclosing initialization lifecycle verifies
     * this shared identity before publishing the protocol for connection use.
     */
    @Override
    protected PacketEncoder createEncoder(ProtocolData data) {
        return new PacketEncoder_v819(data);
    }
}
