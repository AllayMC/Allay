package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemBambooButtonStack extends ItemStack {
    ItemType<ItemBambooButtonStack> BAMBOO_BUTTON_TYPE = ItemTypeBuilder
            .builder(ItemBambooButtonStack.class)
            .vanillaItem(VanillaItemId.BAMBOO_BUTTON)
            .build();
}
