package org.allaymc.api.item.descriptor;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.allaymc.api.identifier.Identifier;
import org.allaymc.api.item.ItemStack;

/**
 * Allay Project 2023/11/25
 *
 * @author daoge_cmd
 */
@AllArgsConstructor
@Getter
public class ItemIdentifierDescriptor implements ItemDescriptor {
    protected Identifier identifier;

    @Override
    public boolean match(ItemStack itemStack) {
        return itemStack.getItemType().getIdentifier().equals(identifier);
    }
}
