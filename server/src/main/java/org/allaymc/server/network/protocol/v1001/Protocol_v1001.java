package org.allaymc.server.network.protocol.v1001;

import org.allaymc.server.network.protocol.ClientVariant;
import org.allaymc.server.network.protocol.NetworkBlockPalette;
import org.allaymc.server.network.protocol.PacketEncoder;
import org.allaymc.server.network.protocol.ProtocolData;
import org.allaymc.server.network.protocol.v975.Protocol_v975;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodec;
import org.cloudburstmc.protocol.bedrock.codec.v1001.Bedrock_v1001;

/**
 * {@code Protocol_v1001} configures wire protocol 1001 within the international
 * protocol inheritance chain. The {@code Protocol_v975} implementation supplies
 * processor registrations and payload generation that are not overridden here.
 * Initialization selects the {@code PacketEncoder_v1001} encoder while
 * retaining the exact codec and client distribution supplied at construction.
 */
public class Protocol_v1001 extends Protocol_v975 {
    /**
     * Creates an uninitialized international protocol using the v1001 codec.
     * The constructor records its identity without building payloads or
     * processors; callers must invoke {@code initialize} after all subtype
     * construction has finished.
     */
    public Protocol_v1001() {
        this(Bedrock_v1001.CODEC, ClientVariant.INTERNATIONAL);
    }

    /**
     * Creates an uninitialized instance retaining the supplied codec and client
     * distribution. Subclasses use this constructor to reuse implementation
     * while keeping their own identity; runtime data is built only by the later
     * initialization call.
     */
    protected Protocol_v1001(BedrockCodec codec, ClientVariant variant) {
        super(codec, variant);
    }

    /**
     * Creates the {@code PacketEncoder_v1001} implementation using the exact
     * supplied data instance. The enclosing initialization lifecycle verifies
     * this shared identity before publishing the protocol for connection use.
     */
    @Override
    protected NetworkBlockPalette createBlockPalette() {
        return NetworkBlockPalette.of("1001");
    }

    @Override
    protected PacketEncoder createEncoder(ProtocolData data) {
        return new PacketEncoder_v1001(data);
    }
}
