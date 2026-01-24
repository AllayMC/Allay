package org.allaymc.server.network.netease.v766;

import io.netty.buffer.ByteBuf;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import org.cloudburstmc.protocol.bedrock.codec.BedrockCodecHelper;
import org.cloudburstmc.protocol.bedrock.codec.v407.serializer.PlayerEnchantOptionsSerializer_v407;
import org.cloudburstmc.protocol.bedrock.data.inventory.EnchantData;
import org.cloudburstmc.protocol.bedrock.data.inventory.EnchantOptionData;
import org.cloudburstmc.protocol.common.util.VarInts;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author daoge_cmd
 */
public class PlayerEnchantOptionsSerializer_v766_NetEase extends PlayerEnchantOptionsSerializer_v407 {

    public static final PlayerEnchantOptionsSerializer_v766_NetEase INSTANCE = new PlayerEnchantOptionsSerializer_v766_NetEase();

    protected void writeOption(ByteBuf buffer, BedrockCodecHelper helper, EnchantOptionData option) {
        VarInts.writeUnsignedInt(buffer, option.getCost());
        buffer.writeIntLE(option.getPrimarySlot());
        helper.writeArray(buffer, option.getEnchants0(), (b, enchant) -> serializeEnchant(b, enchant, helper));
        helper.writeArray(buffer, option.getEnchants1(), (b, enchant) -> serializeEnchant(b, enchant, helper));
        helper.writeArray(buffer, option.getEnchants2(), (b, enchant) -> serializeEnchant(b, enchant, helper));
        // NetEase: neteaseEnchantments
        helper.writeArray(buffer, Collections.<EnchantData>emptyList(), (b, enchant) -> serializeEnchant(b, enchant, helper));
        helper.writeString(buffer, option.getEnchantName());
        VarInts.writeUnsignedInt(buffer, option.getEnchantNetId());
    }

    protected EnchantOptionData readOption(ByteBuf buffer, BedrockCodecHelper helper) {
        int cost = VarInts.readUnsignedInt(buffer);
        int primarySlot = buffer.readIntLE();
        List<EnchantData> enchants1 = new ObjectArrayList<>();
        helper.readArray(buffer, enchants1, b -> deserializeEnchant(b, helper));
        List<EnchantData> enchants2 = new ObjectArrayList<>();
        helper.readArray(buffer, enchants2, b -> deserializeEnchant(b, helper));
        List<EnchantData> enchants3 = new ObjectArrayList<>();
        helper.readArray(buffer, enchants3, b -> deserializeEnchant(b, helper));
        // NetEase: neteaseEnchantments
        helper.readArray(buffer, new ArrayList<>(), b -> deserializeEnchant(b, helper));
        String enchantName = helper.readString(buffer);
        int enchantNetId = VarInts.readUnsignedInt(buffer);
        return new EnchantOptionData(cost, primarySlot, enchants1, enchants2, enchants3, enchantName, enchantNetId);
    }

    protected void serializeEnchant(ByteBuf buffer, EnchantData enchant, BedrockCodecHelper helper) {
        buffer.writeByte(enchant.getType());
        buffer.writeByte(enchant.getLevel());
        // NetEase: modEnchantIdentifier
        helper.writeString(buffer, "");
    }

    protected EnchantData deserializeEnchant(ByteBuf buffer, BedrockCodecHelper helper) {
        int type = buffer.readUnsignedByte();
        int level = buffer.readUnsignedByte();
        // NetEase: modEnchantIdentifier
        helper.readString(buffer);
        return new EnchantData(type, level);
    }
}
