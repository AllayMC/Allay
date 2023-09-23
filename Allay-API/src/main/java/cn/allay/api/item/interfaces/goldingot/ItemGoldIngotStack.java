package cn.allay.api.item.interfaces.goldingot;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemGoldIngotStack extends ItemStack {
    ItemType<ItemGoldIngotStack> GOLD_INGOT_TYPE = ItemTypeBuilder
            .builder(ItemGoldIngotStack.class)
            .vanillaItem(VanillaItemId.GOLD_INGOT)
            .build();
}
