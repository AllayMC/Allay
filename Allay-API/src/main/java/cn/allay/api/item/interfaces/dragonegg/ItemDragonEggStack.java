package cn.allay.api.item.interfaces.dragonegg;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemDragonEggStack extends ItemStack {
    ItemType<ItemDragonEggStack> DRAGON_EGG_TYPE = ItemTypeBuilder
            .builder(ItemDragonEggStack.class)
            .vanillaItem(VanillaItemId.DRAGON_EGG)
            .build();
}
