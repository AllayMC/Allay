package cn.allay.api.item.interfaces.soulfire;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemSoulFireStack extends ItemStack {
    ItemType<ItemSoulFireStack> SOUL_FIRE_TYPE = ItemTypeBuilder
            .builder(ItemSoulFireStack.class)
            .vanillaItem(VanillaItemId.SOUL_FIRE)
            .build();
}
