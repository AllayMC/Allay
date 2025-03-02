package org.allaymc.api.item.creative;

import org.allaymc.api.i18n.MayContainTrKey;
import org.allaymc.api.item.ItemStack;
import org.jetbrains.annotations.UnmodifiableView;

import java.util.Map;

/**
 * Represents a category of creative items. A category in a collection of groups and some ungrouped items.
 * Currently, there are only four visible categories in the creative inventory, and they are {@code Construction},
 * {@code Nature}, {@code Equipment}, and {@code Items}.
 *
 * @author daoge_cmd
 */
public interface CreativeItemCategory {
    /**
     * Get a specific group by name in this category.
     *
     * @param name the name of the group. If an empty string is passed, the default group will be returned.
     *             Translation key is supported here.
     *
     * @return the group with the specified name, or the default group if the name is empty. {@code null} will be returned if the group does not exist.
     */
    CreativeItemGroup getGroup(@MayContainTrKey String name);

    /**
     * Register a new group in this category.
     *
     * @param name the name of the group, empty string is not allowed here. Translation key is supported.
     * @param icon the icon of the group.
     *
     * @return the registered group.
     */
    CreativeItemGroup registerGroup(@MayContainTrKey String name, ItemStack icon);

    /**
     * Get the default group in this category.
     *
     * @return the default group.
     */
    default CreativeItemGroup getDefaultGroup() {
        return getGroup("");
    }

    /**
     * Get all groups in this category.
     *
     * @return a map of all groups in this category.
     */
    @UnmodifiableView
    Map<String, CreativeItemGroup> getGroups();

    /**
     * Get the type of this category.
     *
     * @return the type of this category.
     */
    org.cloudburstmc.protocol.bedrock.data.inventory.CreativeItemCategory getType();
}
