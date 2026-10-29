package org.allaymc.server.network.processor.login;

import org.allaymc.server.player.AllayLoginData;
import org.cloudburstmc.protocol.bedrock.packet.LoginPacket;

/**
 * Login processor for NetEase protocol branches.
 */
public final class NetEaseLoginPacketProcessor extends LoginPacketProcessor {
    @Override
    protected AllayLoginData decodeLoginData(LoginPacket packet) {
        return AllayLoginData.decode(packet, true);
    }

    @Override
    protected boolean isValidName(String name) {
        return name != null && !name.trim().isEmpty();
    }
}
