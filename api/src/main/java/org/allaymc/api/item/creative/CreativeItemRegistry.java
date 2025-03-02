package org.allaymc.api.item.creative;

import org.allaymc.api.i18n.LangCode;
import org.cloudburstmc.protocol.bedrock.packet.CreativeContentPacket;
import org.jetbrains.annotations.UnmodifiableView;

import java.util.List;

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
    default CreativeItemCategory getConstructionCategory() {
        return getCategory(org.cloudburstmc.protocol.bedrock.data.inventory.CreativeItemCategory.CONSTRUCTION);
    }

    /**
     * Get the decoration category.
     *
     * @return the decoration category.
     */
    default CreativeItemCategory getNatureCategory() {
        return getCategory(org.cloudburstmc.protocol.bedrock.data.inventory.CreativeItemCategory.NATURE);
    }

    /**
     * Get the equipment category.
     *
     * @return the equipment category.
     */
    default CreativeItemCategory getEquipmentCategory() {
        return getCategory(org.cloudburstmc.protocol.bedrock.data.inventory.CreativeItemCategory.EQUIPMENT);
    }

    /**
     * Get the items category.
     *
     * @return the items category.
     */
    default CreativeItemCategory getItemsCategory() {
        return getCategory(org.cloudburstmc.protocol.bedrock.data.inventory.CreativeItemCategory.ITEMS);
    }

    /**
     * Get the specified category.
     *
     * @param type the type of the category.
     *
     * @return the specified category.
     */
    CreativeItemCategory getCategory(org.cloudburstmc.protocol.bedrock.data.inventory.CreativeItemCategory type);

    /**
     * Get the creative item by index.
     *
     * @param index the index of the creative item.
     *
     * @return the creative item, or {@code null} if not found.
     */
    CreativeItemEntry getEntryByIndex(int index);

    /**
     * Get the creative item group by index.
     *
     * @param index the index of the creative item group.
     *
     * @return the creative item group, or {@code null} if not found.
     */
    CreativeItemGroup getGroupByIndex(int index);

    /**
     * Get all registered creative items.
     *
     * @return the registered creative items.
     */
    @UnmodifiableView
    List<CreativeItemEntry> getEntries();

    /**
     * Encode the creative content packet in the specified language code.
     *
     * @param langCode the language code used to encode the creative content packet.
     *
     * @return the encoded creative content packet.
     */
    CreativeContentPacket getCreativeContentPacketFor(LangCode langCode);
}
