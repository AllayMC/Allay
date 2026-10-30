package org.allaymc.server.network.processor.ingame;

import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.player.Player;
import org.allaymc.server.network.processor.PacketProcessor;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacketType;
import org.cloudburstmc.protocol.bedrock.packet.EmotePacket;
import org.cloudburstmc.protocol.bedrock.packet.PacketSignal;

import java.util.UUID;

/**
 * {@code EmotePacketProcessor} broadcasts accepted player emotes to the
 * controlled entity's viewers with a
 * per-processor tick cooldown. The incoming runtime ID must match that entity,
 * and the emote string must parse as a {@code UUID} before viewers are notified. The
 * processor records no queue of suppressed emotes.
 *
 * <p>
 * Cooldown admission updates the last-emote tick before validating identity or
 * parsing the {@code UUID}, so a malformed admitted request still consumes its
 * interval. The timestamp begins at zero, making early receive ticks subject to
 * the same limit. A broad catch surrounds both parsing and viewer notification;
 * it logs and consumes failures without reversing any notifications already
 * delivered. The timestamp is not synchronized, so this state relies on the
 * caller's receive scheduling rather than providing independent concurrent
 * admission.
 *
 * @author daoge_cmd
 */
@Slf4j
public class EmotePacketProcessor extends PacketProcessor<EmotePacket> {

    /**
     * {@code EMOTE_TIME_LIMIT} defines the minimum interval of twenty receive
     * ticks between admitted
     * emote requests handled by this processor.
     */
    public static final int EMOTE_TIME_LIMIT = 20;

    /**
     * {@code lastEmoteTime} records the last cooldown-admitted receive tick,
     * initially zero. Identity and {@code UUID} failures after admission still
     * advance this unsynchronized
     * field.
     */
    protected long lastEmoteTime;

    /**
     * Broadcasts an admitted emote and always returns {@code HANDLED} after normal
     * processing. Requests arriving less than twenty receive ticks after the
     * stored timestamp are silently consumed, and the timestamp is advanced
     * before identity and {@code UUID} validation.
     *
     * <p>
     * Wrong entity IDs are logged without broadcasting. {@code UUID} parsing and the
     * viewer loop share a {@code Throwable} handler, so either parsing or viewer
     * failures are logged and consumed; viewers already notified are not rolled
     * back. The method does not synchronize its cooldown state or normalize
     * differences between receive-tick sources.
     */
    @Override
    public PacketSignal handleAsync(Player player, EmotePacket packet, long receiveTime) {
        // Cooldown admission precedes payload validation, so even a
        // subsequently rejected request occupies its interval and cannot
        // immediately bypass the rate check.
        if (receiveTime - this.lastEmoteTime < EMOTE_TIME_LIMIT) {
            return PacketSignal.HANDLED;
        }
        this.lastEmoteTime = receiveTime;

        var entity = player.getControlledEntity();
        if (packet.getRuntimeEntityId() != entity.getRuntimeId()) {
            log.warn("Player {} tried to send emote packet with wrong runtime entity id", player.getOriginName());
            return PacketSignal.HANDLED;
        }

        try {
            var emoteId = UUID.fromString(packet.getEmoteId());
            entity.forEachViewers(viewer -> viewer.viewPlayerEmote(entity, emoteId));
        } catch (Throwable t) {
            // The catch also covers viewer callbacks, so earlier viewers may
            // already have received the emote even though this failure is
            // consumed.
            log.warn("Player {} tried to send emote packet with invalid emote id {}", player.getOriginName(), packet.getEmoteId());
        }

        return PacketSignal.HANDLED;
    }

    /**
     * Returns the emote packet type handled by this processor.
     */
    @Override
    public BedrockPacketType getPacketType() {
        return BedrockPacketType.EMOTE;
    }
}
