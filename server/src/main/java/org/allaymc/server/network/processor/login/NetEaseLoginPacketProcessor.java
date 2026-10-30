package org.allaymc.server.network.processor.login;

import org.allaymc.server.player.AllayLoginData;
import org.cloudburstmc.protocol.bedrock.packet.LoginPacket;

/**
 * {@code NetEaseLoginPacketProcessor} specializes login decoding and name
 * validation for NetEase clients. The inherited login sequence still applies
 * shared access, identity, skin, and encryption checks, while this variant uses
 * NetEase chain decoding and accepts any name with nonempty trimmed content.
 */
public final class NetEaseLoginPacketProcessor extends LoginPacketProcessor {
    /**
     * Decodes login data with the shared decoder's NetEase validation branch.
     * Decoding failures are logged there and return {@code null}, letting the
     * inherited login handler disconnect before policy checks continue.
     */
    @Override
    protected AllayLoginData decodeLoginData(LoginPacket packet) {
        return AllayLoginData.decode(packet, true);
    }

    /**
     * Returns whether the supplied name contains any content after trimming.
     * A {@code null} or trimmed-empty value is invalid; this check does not apply
     * the international ASCII pattern or modify the stored name.
     */
    @Override
    protected boolean isValidName(String name) {
        return name != null && !name.trim().isEmpty();
    }
}
