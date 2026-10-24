package org.allaymc.server.network.protocol.v2193;

import org.allaymc.server.network.protocol.ClientVariant;
import org.allaymc.server.network.protocol.NetworkBlockPalette;
import org.allaymc.server.network.protocol.PacketEncoder;
import org.allaymc.server.network.protocol.ProtocolData;
import org.allaymc.server.network.protocol.v2169.Protocol_v2169;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodec;
import org.cloudburstmc.protocol.bedrock.codec.v2193.Bedrock_v2193;
import org.cloudburstmc.protocol.bedrock.data.BlockPropertyData;

import java.util.ArrayList;
import java.util.List;

/**
 * {@code Protocol_v2193} configures wire protocol 2193 within the international
 * protocol inheritance chain. The {@code Protocol_v2169} implementation
 * supplies processor registrations and payload generation that are not
 * overridden here. Initialization selects the {@code PacketEncoder_v2193}
 * encoder while retaining the exact codec and client distribution supplied at
 * construction.
 */
public class Protocol_v2193 extends Protocol_v2169 {
    /**
     * Creates an uninitialized international protocol using the v2193 codec.
     * The constructor records its identity without building payloads or
     * processors; callers must invoke {@code initialize} after all subtype
     * construction has finished.
     */
    public Protocol_v2193() {
        this(Bedrock_v2193.CODEC, ClientVariant.INTERNATIONAL);
    }

    /**
     * Creates an uninitialized instance retaining the supplied codec and client
     * distribution. Subclasses use this constructor to reuse implementation
     * while keeping their own identity; runtime data is built only by the later
     * initialization call.
     */
    protected Protocol_v2193(BedrockCodec codec, ClientVariant variant) {
        super(codec, variant);
    }

    /**
     * Creates the {@code PacketEncoder_v2193} implementation using the exact
     * supplied data instance. The enclosing initialization lifecycle verifies
     * this shared identity before publishing the protocol for connection use.
     */
    @Override
    protected List<BlockPropertyData> createCustomBlockProperties() {
        var properties = new ArrayList<>(VanillaData_v2193.DATA_DRIVEN_BLOCK_PROPERTIES);
        properties.addAll(super.createCustomBlockProperties());
        return List.copyOf(properties);
    }

    @Override
    protected NetworkBlockPalette createBlockPalette() {
        return NetworkBlockPalette.current();
    }

    @Override
    protected PacketEncoder createEncoder(ProtocolData data) {
        return new PacketEncoder_v2193(data);
    }
}
