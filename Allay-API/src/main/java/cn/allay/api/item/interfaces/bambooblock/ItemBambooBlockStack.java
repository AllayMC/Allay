package cn.allay.api.item.interfaces.bambooblock;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemBambooBlockStack extends ItemStack {
    ItemType<ItemBambooBlockStack> BAMBOO_BLOCK_TYPE = ItemTypeBuilder
            .builder(ItemBambooBlockStack.class)
            .vanillaItem(VanillaItemId.BAMBOO_BLOCK)
            .build();
}
