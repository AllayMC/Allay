package org.allaymc.server.network.protocol.v898;

import io.netty.buffer.ByteBuf;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodec;
import org.cloudburstmc.protocol.bedrock.codec.EntityDataTypeMap;
import org.cloudburstmc.protocol.bedrock.codec.v766_netease.serializer.PlayerAuthInputSerializer_v766_NetEase;
import org.cloudburstmc.protocol.bedrock.codec.v860_netease.serializer.BiomeDefinitionListSerializer_v860_NetEase;
import org.cloudburstmc.protocol.bedrock.codec.v898.Bedrock_v898;
import org.cloudburstmc.protocol.bedrock.codec.v898.BedrockCodecHelper_v898;
import org.cloudburstmc.protocol.bedrock.codec.netease.serializer.ConfirmSkinSerializer;
import org.cloudburstmc.protocol.bedrock.codec.netease.serializer.NetEaseJsonSerializer;
import org.cloudburstmc.protocol.bedrock.codec.netease.serializer.PlayerEnchantOptionsSerializer_v407_NetEase;
import org.cloudburstmc.protocol.bedrock.codec.netease.serializer.PyRpcSerializer;
import org.cloudburstmc.protocol.bedrock.codec.netease.serializer.StoreBuySuccessSerializer;
import org.cloudburstmc.protocol.bedrock.data.Ability;
import org.cloudburstmc.protocol.bedrock.data.PacketRecipient;
import org.cloudburstmc.protocol.bedrock.data.PlayerAuthInputData;
import org.cloudburstmc.protocol.bedrock.data.inventory.ContainerSlotType;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.TextProcessingEventOrigin;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.action.ItemStackRequestActionType;
import org.cloudburstmc.protocol.bedrock.packet.BiomeDefinitionListPacket;
import org.cloudburstmc.protocol.bedrock.packet.ConfirmSkinPacket;
import org.cloudburstmc.protocol.bedrock.packet.ContainerOpenPacket;
import org.cloudburstmc.protocol.bedrock.packet.NetEaseJsonPacket;
import org.cloudburstmc.protocol.bedrock.packet.PlayerAuthInputPacket;
import org.cloudburstmc.protocol.bedrock.packet.PlayerEnchantOptionsPacket;
import org.cloudburstmc.protocol.bedrock.packet.PyRpcPacket;
import org.cloudburstmc.protocol.bedrock.packet.StoreBuySuccessPacket;
import org.cloudburstmc.protocol.bedrock.packet.TextPacket;
import org.cloudburstmc.protocol.bedrock.util.TypeMap;
import org.cloudburstmc.protocol.bedrock.util.VarInts;

import java.math.BigInteger;
import java.util.Set;

/**
 * NetEase flavoured codec for Minecraft 1.21.130 (protocol 898).
 *
 * <p>The bundled protocol library ships NetEase codecs only up to 1.21.124, so the NetEase delta is
 * applied here on top of the vanilla 1.21.130 codec: the flags of {@link PlayerAuthInputData} carry an
 * extra slot, text and enchant-option serialization differ, biome definitions and container open carry
 * extra fields, and four NetEase platform packets are registered above id 200.</p>
 *
 * @author daoge_cmd
 */
public final class NetEaseCodec898 extends Bedrock_v898 {

    /**
     * Position of the NetEase-only player auth input flag, which shifts every later flag by one.
     */
    private static final int PLAYER_AUTH_INPUT_DATA_NETEASE = PlayerAuthInputData.RECEIVED_SERVER_DATA.ordinal() + 1;

    // Inherited protected statics of the vanilla codec hierarchy, captured for the helper.
    private static final EntityDataTypeMap ENTITY_DATA_TYPES = ENTITY_DATA;
    private static final TypeMap<Class<?>> GAME_RULES = GAME_RULE_TYPES;
    private static final TypeMap<ItemStackRequestActionType> ITEM_STACK_ACTIONS = ITEM_STACK_REQUEST_TYPES;
    private static final TypeMap<Ability> ABILITIES = PLAYER_ABILITIES;
    private static final TypeMap<TextProcessingEventOrigin> TEXT_ORIGINS = TEXT_PROCESSING_ORIGINS;

    /**
     * Container slot types with the NetEase recipe slot inserted at its dedicated position.
     */
    private static final TypeMap<ContainerSlotType> NETEASE_CONTAINER_SLOT_TYPES = CONTAINER_SLOT_TYPES.toBuilder()
            .shift(17, 1)
            .insert(17, ContainerSlotType.RECIPE_CUSTOM)
            .build();

    /**
     * The NetEase codec for protocol 898.
     */
    public static final BedrockCodec CODEC = Bedrock_v898.CODEC.toBuilder()
            .raknetProtocolVersion(8)
            .helper(NetEaseCodecHelper898::new)
            .updateSerializer(PlayerAuthInputPacket.class, PlayerAuthInputSerializer_v766_NetEase.INSTANCE)
            .updateSerializer(TextPacket.class, TextSerializer_v898_NetEase.INSTANCE)
            .updateSerializer(PlayerEnchantOptionsPacket.class, PlayerEnchantOptionsSerializer_v407_NetEase.INSTANCE)
            .updateSerializer(BiomeDefinitionListPacket.class, BiomeDefinitionListSerializer_v860_NetEase.INSTANCE)
            .updateSerializer(ContainerOpenPacket.class, ContainerOpenSerializer_v898_NetEase.INSTANCE)
            .registerPacket(PyRpcPacket::new, PyRpcSerializer.INSTANCE, 200, PacketRecipient.BOTH)
            .registerPacket(StoreBuySuccessPacket::new, StoreBuySuccessSerializer.INSTANCE, 202, PacketRecipient.BOTH)
            .registerPacket(NetEaseJsonPacket::new, NetEaseJsonSerializer.INSTANCE, 203, PacketRecipient.BOTH)
            .registerPacket(ConfirmSkinPacket::new, ConfirmSkinSerializer.INSTANCE, 228, PacketRecipient.CLIENT)
            .build();

    private NetEaseCodec898() {
    }

    private static final class NetEaseCodecHelper898 extends BedrockCodecHelper_v898 {

        private NetEaseCodecHelper898() {
            super(ENTITY_DATA_TYPES, GAME_RULES, ITEM_STACK_ACTIONS, NETEASE_CONTAINER_SLOT_TYPES, ABILITIES, TEXT_ORIGINS);
        }

        @Override
        public <T extends Enum<?>> void readLargeVarIntFlags(ByteBuf buf, Set<T> flags, Class<T> clazz) {
            boolean playerAuthInput = clazz == PlayerAuthInputData.class;
            var bits = VarInts.readUnsignedBigVarInt(buf, clazz.getEnumConstants().length);
            for (T constant : clazz.getEnumConstants()) {
                int index = constant.ordinal();
                if (playerAuthInput) {
                    // The NetEase-only flag exists on the wire but not in the packet enum.
                    if (index == PLAYER_AUTH_INPUT_DATA_NETEASE) {
                        continue;
                    }
                    if (index > PLAYER_AUTH_INPUT_DATA_NETEASE) {
                        index--;
                    }
                }
                if (bits.testBit(index)) {
                    flags.add(constant);
                }
            }
        }

        @Override
        public <T extends Enum<?>> void writeLargeVarIntFlags(ByteBuf buf, Set<T> flags, Class<T> clazz) {
            boolean playerAuthInput = clazz == PlayerAuthInputData.class;
            var bits = BigInteger.ZERO;
            for (T constant : flags) {
                int index = constant.ordinal();
                if (playerAuthInput && index >= PLAYER_AUTH_INPUT_DATA_NETEASE) {
                    index++;
                }
                bits = bits.setBit(index);
            }
            VarInts.writeUnsignedBigVarInt(buf, bits);
        }
    }
}
