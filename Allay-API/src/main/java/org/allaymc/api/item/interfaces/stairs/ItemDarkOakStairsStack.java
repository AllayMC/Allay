package org.allaymc.api.item.interfaces.stairs;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.component.base.ItemBaseComponentImpl;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemDarkOakStairsStack extends ItemStack {
    ItemType<ItemDarkOakStairsStack> DARK_OAK_STAIRS_TYPE = ItemTypeBuilder
            .builder(ItemDarkOakStairsStack.class)
            .vanillaItem(VanillaItemId.DARK_OAK_STAIRS)
            .addComponent(ItemBaseComponentImpl::new, ItemBaseComponentImpl.class)
            .build();
}
