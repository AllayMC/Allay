package cn.allay.api.item.interfaces.netherbrickfence;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemNetherBrickFenceStack extends ItemStack {
    ItemType<ItemNetherBrickFenceStack> NETHER_BRICK_FENCE_TYPE = ItemTypeBuilder
            .builder(ItemNetherBrickFenceStack.class)
            .vanillaItem(VanillaItemId.NETHER_BRICK_FENCE)
            .build();
}
