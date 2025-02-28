package org.allaymc.api.item.creative;

import org.allaymc.api.i18n.LangCode;
import org.allaymc.api.item.ItemStack;
import org.cloudburstmc.protocol.bedrock.packet.CreativeContentPacket;

/**
 * Represents the registry for creative items. Plugin can modify the creative items through the methods inside.
 *
 * @author daoge_cmd
 */
public interface CreativeItemRegistry {
    /**
     * Get the construction category.
     *
     * @return the construction category.
     */
    CreativeItemCategory getConstructionCategory();

    /**
     * Get the decoration category.
     *
     * @return the decoration category.
     */
    CreativeItemCategory getNatureCategory();

    /**
     * Get the equipment category.
     *
     * @return the equipment category.
     */
    CreativeItemCategory getEquipmentCategory();

    /**
     * Get the items category.
     *
     * @return the items category.
     */
    CreativeItemCategory getItemsCategory();

    /**
     * Get the creative item by index.
     *
     * @param index the index of the creative item.
     *
     * @return the creative item, or {@code null} if not found.
     */
    ItemStack getItemStackByIndex(int index);

    /**
     * Encode the creative content packet in the specified language code.
     *
     * @param langCode the language code used to encode the creative content packet.
     *
     * @return the encoded creative content packet.
     */
    CreativeContentPacket encodeCreativeContentPacketFor(LangCode langCode);
}
