package org.allaymc.server.network.protocol.v860;

import org.allaymc.server.network.protocol.ClientVariant;
import org.allaymc.server.network.protocol.PacketEncoder;
import org.allaymc.server.network.protocol.ProtocolData;
import org.allaymc.server.network.protocol.v859.Protocol_v859;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodec;
import org.cloudburstmc.protocol.bedrock.codec.v860.Bedrock_v860;

/**
 * {@code Protocol_v860} configures wire protocol 860 within the international
 * protocol inheritance chain. The {@code Protocol_v859} implementation supplies
 * processor registrations and payload generation that are not overridden here.
 * Initialization selects the {@code PacketEncoder_v860} encoder while retaining
 * the exact codec and client distribution supplied at construction.
 */
public class Protocol_v860 extends Protocol_v859 {
    /**
     * Creates an uninitialized international protocol using the v860 codec. The
     * constructor records its identity without building payloads or processors;
     * callers must invoke {@code initialize} after all subtype construction has
     * finished.
     */
    public Protocol_v860() {
        this(Bedrock_v860.CODEC, ClientVariant.INTERNATIONAL);
    }

    /**
     * Creates an uninitialized instance retaining the supplied codec and client
     * distribution. Subclasses use this constructor to reuse implementation
     * while keeping their own identity; runtime data is built only by the later
     * initialization call.
     */
    protected Protocol_v860(BedrockCodec codec, ClientVariant variant) {
        super(codec, variant);
    }

    /**
     * Creates the {@code PacketEncoder_v860} implementation using the exact
     * supplied data instance. The enclosing initialization lifecycle verifies
     * this shared identity before publishing the protocol for connection use.
     */
    @Override
    protected PacketEncoder createEncoder(ProtocolData data) {
        return new PacketEncoder_v860(data);
    }
}
