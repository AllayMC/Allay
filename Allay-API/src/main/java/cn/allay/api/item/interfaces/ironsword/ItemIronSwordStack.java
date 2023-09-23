package cn.allay.api.item.interfaces.ironsword;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemIronSwordStack extends ItemStack {
    ItemType<ItemIronSwordStack> IRON_SWORD_TYPE = ItemTypeBuilder
            .builder(ItemIronSwordStack.class)
            .vanillaItem(VanillaItemId.IRON_SWORD)
            .build();
}
