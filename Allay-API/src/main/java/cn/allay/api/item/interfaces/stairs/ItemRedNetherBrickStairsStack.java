package cn.allay.api.item.interfaces.stairs;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.component.base.ItemBaseComponentImpl;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemRedNetherBrickStairsStack extends ItemStack {
    ItemType<ItemRedNetherBrickStairsStack> RED_NETHER_BRICK_STAIRS_TYPE = ItemTypeBuilder
            .builder(ItemRedNetherBrickStairsStack.class)
            .vanillaItem(VanillaItemId.RED_NETHER_BRICK_STAIRS)
            .addComponent(ItemBaseComponentImpl::new, ItemBaseComponentImpl.class)
            .build();
}
