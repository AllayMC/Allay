package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemElement79Stack extends ItemStack {
    ItemType<ItemElement79Stack> ELEMENT_79_TYPE = ItemTypeBuilder
            .builder(ItemElement79Stack.class)
            .vanillaItem(VanillaItemId.ELEMENT_79)
            .build();
}
