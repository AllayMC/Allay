package org.allaymc.server.network.processor.ingame;

import org.allaymc.server.container.processor.CraftRecipeOptionalActionProcessor;
import org.allaymc.server.network.processor.PacketProcessor;
import org.cloudburstmc.protocol.bedrock.packet.AnvilDamagePacket;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacketType;

/**
 * {@code AnvilDamagePacketProcessor} ignores client anvil-damage packets through
 * the inherited empty synchronous handler. Anvil wear is decided while
 * processing the optional crafting action
 * in {@link CraftRecipeOptionalActionProcessor}, keeping it tied to the
 * server's anvil operation rather than accepting a separate client damage
 * notification.
 *
 * @author IWareQ
 */
public class AnvilDamagePacketProcessor extends PacketProcessor<AnvilDamagePacket> {
    /**
     * Returns the anvil damage packet type handled by this processor.
     */
    @Override
    public BedrockPacketType getPacketType() {
        return BedrockPacketType.ANVIL_DAMAGE;
    }
}
