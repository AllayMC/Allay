package cn.allay.api.item.interfaces.netherbrick;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemNetherBrickStack extends ItemStack {
    ItemType<ItemNetherBrickStack> NETHER_BRICK_TYPE = ItemTypeBuilder
            .builder(ItemNetherBrickStack.class)
            .vanillaItem(VanillaItemId.NETHER_BRICK)
            .build();
}
