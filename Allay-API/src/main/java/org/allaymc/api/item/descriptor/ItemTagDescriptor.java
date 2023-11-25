package org.allaymc.api.item.descriptor;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.tag.ItemTag;

/**
 * Allay Project 2023/11/25
 *
 * @author daoge_cmd
 */
@AllArgsConstructor
@Getter
public class ItemTagDescriptor implements ItemDescriptor {
    protected ItemTag itemTag;

    @Override
    public boolean match(ItemStack itemStack) {
        return itemStack.getItemType().hasItemTag(itemTag);
    }
}
