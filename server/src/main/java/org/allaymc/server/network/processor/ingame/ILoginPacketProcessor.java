package org.allaymc.server.network.processor.ingame;

import org.allaymc.api.player.Player;
import org.allaymc.server.network.processor.PacketProcessor;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacket;
import org.cloudburstmc.protocol.bedrock.packet.PacketSignal;

/**
 * {@code ILoginPacketProcessor} adapts login handling to the asynchronous packet
 * processor phase. Subclasses implement a single {@code handle} operation, and
 * successful invocation consumes the
 * packet without scheduling synchronous work. Calling the synchronous hook
 * directly is unsupported and always throws, making the login path's execution
 * phase explicit.
 *
 * @author daoge_cmd
 */
public abstract class ILoginPacketProcessor<T extends BedrockPacket> extends PacketProcessor<T> {
    /**
     * Rejects synchronous login handling by always throwing
     * {@code UnsupportedOperationException}. Callers must use the asynchronous hook or
     * the subclass {@code handle} operation, because this adapter does not implement a
     * world-thread login continuation.
     */
    @Override
    public void handleSync(Player player, T packet, long receiveTime) {
        throw new UnsupportedOperationException();
    }

    /**
     * Invokes the subclass login handler and returns {@code HANDLED} after it
     * completes. Any handler exception propagates to the receive path, and
     * successful handling never requests the inherited synchronous processing
     * phase.
     */
    @Override
    public PacketSignal handleAsync(Player player, T packet, long receiveTime) {
        handle(player, packet);
        return PacketSignal.HANDLED;
    }

    /**
     * Handles one login packet on the calling receive thread. Implementations
     * define their login side effects and failures; this adapter does not
     * supply a world-thread handoff or catch their exceptions.
     */
    public abstract void handle(Player player, T packet);
}
