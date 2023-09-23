package cn.allay.api.item.interfaces.sprucestairs;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.component.base.ItemBaseComponentImpl;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemSpruceStairsStack extends ItemStack {
    ItemType<ItemSpruceStairsStack> SPRUCE_STAIRS_TYPE = ItemTypeBuilder
            .builder(ItemSpruceStairsStack.class)
            .vanillaItem(VanillaItemId.SPRUCE_STAIRS)
            .addComponent(ItemBaseComponentImpl::new, ItemBaseComponentImpl.class)
            .build();
}
