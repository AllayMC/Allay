package org.allaymc.server.network.processor.ingame;

import org.allaymc.server.container.processor.CraftRecipeOptionalActionProcessor;
import org.allaymc.server.network.processor.PacketProcessor;
import org.cloudburstmc.protocol.bedrock.packet.AnvilDamagePacket;
import org.cloudburstmc.protocol.bedrock.packet.BedrockPacketType;

/**
 * For security reasons, we do not process this packet.
 * Anvil damage handled in {@link CraftRecipeOptionalActionProcessor}
 *
 * @author IWareQ
 */
public class AnvilDamagePacketProcessor extends PacketProcessor<AnvilDamagePacket> {
    @Override
    public BedrockPacketType getPacketType() {
        return BedrockPacketType.ANVIL_DAMAGE;
    }
}
