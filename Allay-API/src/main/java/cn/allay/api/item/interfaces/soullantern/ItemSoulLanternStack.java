package cn.allay.api.item.interfaces.soullantern;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemSoulLanternStack extends ItemStack {
    ItemType<ItemSoulLanternStack> SOUL_LANTERN_TYPE = ItemTypeBuilder
            .builder(ItemSoulLanternStack.class)
            .vanillaItem(VanillaItemId.SOUL_LANTERN)
            .build();
}
