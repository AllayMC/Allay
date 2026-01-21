package org.allaymc.server.network.netease.v766;

import io.netty.buffer.ByteBuf;
import org.cloudburstmc.protocol.bedrock.codec.EntityDataTypeMap;
import org.cloudburstmc.protocol.bedrock.codec.v766.BedrockCodecHelper_v766;
import org.cloudburstmc.protocol.bedrock.data.Ability;
import org.cloudburstmc.protocol.bedrock.data.PlayerAuthInputData;
import org.cloudburstmc.protocol.bedrock.data.inventory.ContainerSlotType;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.TextProcessingEventOrigin;
import org.cloudburstmc.protocol.bedrock.data.inventory.itemstack.request.action.ItemStackRequestActionType;
import org.cloudburstmc.protocol.common.util.TypeMap;
import org.cloudburstmc.protocol.common.util.VarInts;

import java.math.BigInteger;
import java.util.Set;

/**
 * @author daoge_cmd
 */
public class BedrockCodecHelper_v766_NetEase extends BedrockCodecHelper_v766 {

    private static final int PLAYER_AUTH_INPUT_DATA_NETEASE = PlayerAuthInputData.RECEIVED_SERVER_DATA.ordinal() + 1;

    public BedrockCodecHelper_v766_NetEase(EntityDataTypeMap entityData, TypeMap<Class<?>> gameRulesTypes, TypeMap<ItemStackRequestActionType> stackRequestActionTypes, TypeMap<ContainerSlotType> containerSlotTypes, TypeMap<Ability> abilities, TypeMap<TextProcessingEventOrigin> textProcessingEventOrigins) {
        super(entityData, gameRulesTypes, stackRequestActionTypes, containerSlotTypes, abilities, textProcessingEventOrigins);
    }

    @Override
    public <T extends Enum<?>> void readLargeVarIntFlags(ByteBuf buffer, Set<T> flags, Class<T> clazz) {
        var needNetEaseAdaptation = clazz == PlayerAuthInputData.class;
        BigInteger flagsInt = VarInts.readUnsignedBigVarInt(buffer, clazz.getEnumConstants().length);
        for (T flag : clazz.getEnumConstants()) {
            var ordinal = flag.ordinal();
            if (needNetEaseAdaptation) {
                if (ordinal == PLAYER_AUTH_INPUT_DATA_NETEASE) {
                    // Ignore PlayerAuthInputData.NETEASE
                    continue;
                }

                if (ordinal > PLAYER_AUTH_INPUT_DATA_NETEASE) {
                    ordinal -= 1;
                }
            }
            if (flagsInt.testBit(ordinal)) {
                flags.add(flag);
            }
        }
    }

    @Override
    public <T extends Enum<?>> void writeLargeVarIntFlags(ByteBuf buffer, Set<T> flags, Class<T> clazz) {
        var needNetEaseAdaptation = clazz == PlayerAuthInputData.class;
        BigInteger flagsInt = BigInteger.ZERO;
        for (T flag : flags) {
            var ordinal = flag.ordinal();
            if (needNetEaseAdaptation && ordinal >= PLAYER_AUTH_INPUT_DATA_NETEASE) {
                ordinal += 1;
            }
            flagsInt = flagsInt.setBit(ordinal);
        }
        VarInts.writeUnsignedBigVarInt(buffer, flagsInt);
    }
}
