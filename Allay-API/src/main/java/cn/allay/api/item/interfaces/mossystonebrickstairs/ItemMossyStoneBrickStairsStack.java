package cn.allay.api.item.interfaces.mossystonebrickstairs;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.component.base.ItemBaseComponentImpl;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemMossyStoneBrickStairsStack extends ItemStack {
    ItemType<ItemMossyStoneBrickStairsStack> MOSSY_STONE_BRICK_STAIRS_TYPE = ItemTypeBuilder
            .builder(ItemMossyStoneBrickStairsStack.class)
            .vanillaItem(VanillaItemId.MOSSY_STONE_BRICK_STAIRS)
            .addComponent(ItemBaseComponentImpl::new, ItemBaseComponentImpl.class)
            .build();
}
