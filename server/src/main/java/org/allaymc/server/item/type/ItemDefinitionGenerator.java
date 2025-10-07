package org.allaymc.server.item.type;

import org.allaymc.api.item.type.ItemType;

/**
 * ItemDefinitionGenerator is used to generate an {@link ItemDefinition} based on an {@link ItemType}. It
 * accepts an item type instance and returns an encoded item definition for it.
 *
 * @author daoge_cmd
 */
@FunctionalInterface
public interface ItemDefinitionGenerator {
    /**
     * Generates an {@link ItemDefinition} based on the specified {@link ItemType}.
     *
     * @param itemType the {@link ItemType} to generate the {@link ItemDefinition} for
     * @return the generated {@link ItemDefinition} representing the given {@code itemType}
     */
    ItemDefinition generate(ItemType<?> itemType);
}
