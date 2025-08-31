package org.allaymc.api.item.creative;

import org.allaymc.api.item.ItemStack;
import org.jetbrains.annotations.UnmodifiableView;

import java.util.Map;

/**
 * Represents a category of creative items. A category is a collection of named and unnamed groups.
 * Currently, there are only four visible categories in the creative inventory, and they are {@code Construction},
 * {@code Nature}, {@code Equipment}, and {@code Items}.
 *
 * @author daoge_cmd
 */
public interface CreativeItemCategory {
    /**
     * Get a specific group by index in this category.
     *
     * @param index the index of the group
     *
     * @return the group with the specified index. {@code null} will be returned if the group does not exist
     */
    CreativeItemGroup getGroup(int index);

    /**
     * Get a specific group by name in this category.
     *
     * @param name the name of the group, empty or blank string is not allowed here. Check
     *             {@link CreativeItemGroups} for all available group names in vanilla.
     *
     * @return the group with the specified name. {@code null} will be returned if the group does not exist
     *
     * @throws IllegalArgumentException if the name is empty or blank
     */
    CreativeItemGroup getNamedGroup(String name);

    /**
     * Register a new named group in the category. See docs in {@link CreativeItemGroup} for more details
     * about the difference between named and unnamed groups.
     *
     * @param name the name of the group, empty string is not allowed here. Translation key is supported
     * @param icon the icon of the group
     *
     * @return the registered group
     */
    CreativeItemGroup registerGroup(String name, ItemStack icon);

    /**
     * Register a new unnamed group in the category. See docs in {@link CreativeItemGroup} for more details
     * about the difference between named and unnamed groups.
     *
     * @return the registered group
     */
    CreativeItemGroup registerUnnamedGroup();

    /**
     * Get all groups in this category.
     *
     * @return a map of all groups in this category
     */
    @UnmodifiableView
    Map<Integer, CreativeItemGroup> getGroups();

    /**
     * Get the type of this category.
     *
     * @return the type of this category
     */
    org.cloudburstmc.protocol.bedrock.data.inventory.CreativeItemCategory getType();
}
