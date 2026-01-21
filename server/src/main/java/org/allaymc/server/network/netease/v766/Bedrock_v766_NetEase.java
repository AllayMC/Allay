package org.allaymc.server.network.netease.v766;

import org.cloudburstmc.protocol.bedrock.codec.BedrockCodec;
import org.cloudburstmc.protocol.bedrock.codec.v766.Bedrock_v766;
import org.cloudburstmc.protocol.bedrock.codec.v766.BedrockCodecHelper_v766;
import org.cloudburstmc.protocol.bedrock.data.inventory.ContainerSlotType;
import org.cloudburstmc.protocol.bedrock.packet.PlayerAuthInputPacket;
import org.cloudburstmc.protocol.bedrock.packet.TextPacket;
import org.cloudburstmc.protocol.common.util.TypeMap;

/**
 * NetEase variant of the v766 codec.
 *
 * @author daoge_cmd
 */
public class Bedrock_v766_NetEase extends Bedrock_v766 {

    protected static final TypeMap<ContainerSlotType> CONTAINER_SLOT_TYPES_NETEASE = CONTAINER_SLOT_TYPES
            .toBuilder()
            .shift(21, 1)
            .build();

    public static final BedrockCodec CODEC = Bedrock_v766.CODEC.toBuilder()
            .raknetProtocolVersion(8)
            .helper(() -> new BedrockCodecHelper_v766_NetEase(ENTITY_DATA, GAME_RULE_TYPES, ITEM_STACK_REQUEST_TYPES, CONTAINER_SLOT_TYPES_NETEASE, PLAYER_ABILITIES, TEXT_PROCESSING_ORIGINS))
            .updateSerializer(PlayerAuthInputPacket.class, PlayerAuthInputSerializer_v766_NetEase.INSTANCE)
            .updateSerializer(TextPacket.class, TextSerializer_v766_NetEase.INSTANCE)
            .build();
}
