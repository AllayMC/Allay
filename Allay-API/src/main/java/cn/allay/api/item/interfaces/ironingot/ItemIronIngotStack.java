package cn.allay.api.item.interfaces.ironingot;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemIronIngotStack extends ItemStack {
    ItemType<ItemIronIngotStack> IRON_INGOT_TYPE = ItemTypeBuilder
            .builder(ItemIronIngotStack.class)
            .vanillaItem(VanillaItemId.IRON_INGOT)
            .build();
}
