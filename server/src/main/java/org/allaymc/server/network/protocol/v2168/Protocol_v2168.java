package org.allaymc.server.network.protocol.v2168;

import org.allaymc.server.network.protocol.ClientVariant;
import org.allaymc.server.network.protocol.NetworkBlockPalette;
import org.allaymc.server.network.protocol.NetworkItemPalette;
import org.allaymc.server.network.protocol.PacketEncoder;
import org.allaymc.server.network.protocol.ProtocolData;
import org.allaymc.server.network.protocol.v1001.Protocol_v1001;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodec;
import org.cloudburstmc.protocol.bedrock.codec.v2168.Bedrock_v2168_hotfix4;

/**
 * {@code Protocol_v2168} configures wire protocol 2168 within the international
 * protocol inheritance chain. The public constructor selects the hotfix4 codec
 * for the international variant. Payload and processor setup remains inherited,
 * while the encoder supplies the entry-action and item-stack-response
 * adjustments associated with this wire version.
 */
public class Protocol_v2168 extends Protocol_v1001 {

    /**
     * Creates an uninitialized international protocol using the v2168 hotfix4
     * codec. The constructor records its identity without building payloads or
     * processors; callers must invoke {@code initialize} after all subtype
     * construction has finished.
     */
    public Protocol_v2168() {
        this(Bedrock_v2168_hotfix4.CODEC, ClientVariant.INTERNATIONAL);
    }

    /**
     * Creates an uninitialized instance retaining the supplied codec and client
     * distribution. Subclasses use this constructor to reuse implementation
     * while keeping their own identity; runtime data is built only by the later
     * initialization call.
     */
    protected Protocol_v2168(BedrockCodec codec, ClientVariant variant) {
        super(codec, variant);
    }

    /**
     * Creates the {@code PacketEncoder_v2168} implementation using the exact
     * supplied data instance. The enclosing initialization lifecycle verifies
     * this shared identity before publishing the protocol for connection use.
     */
    @Override
    protected NetworkBlockPalette createBlockPalette() {
        return NetworkBlockPalette.of("2168");
    }

    @Override
    protected NetworkItemPalette createItemPalette() {
        return NetworkItemPalette.of("2168");
    }

    @Override
    protected PacketEncoder createEncoder(ProtocolData data) {
        return new PacketEncoder_v2168(data);
    }
}
