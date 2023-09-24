package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemLeatherChestplateStack extends ItemStack {
    ItemType<ItemLeatherChestplateStack> LEATHER_CHESTPLATE_TYPE = ItemTypeBuilder
            .builder(ItemLeatherChestplateStack.class)
            .vanillaItem(VanillaItemId.LEATHER_CHESTPLATE)
            .build();
}
