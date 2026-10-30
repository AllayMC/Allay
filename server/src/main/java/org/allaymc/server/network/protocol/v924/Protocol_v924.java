package org.allaymc.server.network.protocol.v924;

import org.allaymc.server.network.protocol.ClientVariant;
import org.allaymc.server.network.protocol.PacketEncoder;
import org.allaymc.server.network.protocol.ProtocolData;
import org.allaymc.server.network.protocol.v898.Protocol_v898;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodec;
import org.cloudburstmc.protocol.bedrock.codec.v924.Bedrock_v924;

/**
 * {@code Protocol_v924} configures wire protocol 924 within the international
 * protocol inheritance chain. The {@code Protocol_v898} implementation supplies
 * processor registrations and payload generation that are not overridden here.
 * Initialization selects the {@code PacketEncoder_v924} encoder while retaining
 * the exact codec and client distribution supplied at construction.
 */
public class Protocol_v924 extends Protocol_v898 {
    /**
     * Creates an uninitialized international protocol using the v924 codec. The
     * constructor records its identity without building payloads or processors;
     * callers must invoke {@code initialize} after all subtype construction has
     * finished.
     */
    public Protocol_v924() {
        this(Bedrock_v924.CODEC, ClientVariant.INTERNATIONAL);
    }

    /**
     * Creates an uninitialized instance retaining the supplied codec and client
     * distribution. Subclasses use this constructor to reuse implementation
     * while keeping their own identity; runtime data is built only by the later
     * initialization call.
     */
    protected Protocol_v924(BedrockCodec codec, ClientVariant variant) {
        super(codec, variant);
    }

    /**
     * Creates the {@code PacketEncoder_v924} implementation using the exact
     * supplied data instance. The enclosing initialization lifecycle verifies
     * this shared identity before publishing the protocol for connection use.
     */
    @Override
    protected PacketEncoder createEncoder(ProtocolData data) {
        return new PacketEncoder_v924(data);
    }
}
