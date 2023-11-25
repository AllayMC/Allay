package org.allaymc.api.item.descriptor;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.allaymc.api.item.ItemStack;

/**
 * Allay Project 2023/11/25
 *
 * @author daoge_cmd
 */
@AllArgsConstructor
@Getter
public class ItemDescriptorWithCount implements ItemDescriptor {
    protected ItemDescriptor itemDescriptor;
    protected int count;

    @Override
    public boolean match(ItemStack itemStack) {
        return itemDescriptor.match(itemStack) && itemStack.getCount() == count;
    }
}
