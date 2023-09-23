package cn.allay.api.item.interfaces.brickblock;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemBrickBlockStack extends ItemStack {
    ItemType<ItemBrickBlockStack> BRICK_BLOCK_TYPE = ItemTypeBuilder
            .builder(ItemBrickBlockStack.class)
            .vanillaItem(VanillaItemId.BRICK_BLOCK)
            .build();
}
