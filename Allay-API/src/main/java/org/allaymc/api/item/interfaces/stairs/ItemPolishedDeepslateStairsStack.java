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
public interface ItemPolishedDeepslateStairsStack extends ItemStack {
    ItemType<ItemPolishedDeepslateStairsStack> POLISHED_DEEPSLATE_STAIRS_TYPE = ItemTypeBuilder
            .builder(ItemPolishedDeepslateStairsStack.class)
            .vanillaItem(VanillaItemId.POLISHED_DEEPSLATE_STAIRS)
            .addComponent(ItemBaseComponentImpl::new, ItemBaseComponentImpl.class)
            .build();
}
