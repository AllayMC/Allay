package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemSuspiciousStewStack extends ItemStack {
    ItemType<ItemSuspiciousStewStack> SUSPICIOUS_STEW_TYPE = ItemTypeBuilder
            .builder(ItemSuspiciousStewStack.class)
            .vanillaItem(VanillaItemId.SUSPICIOUS_STEW)
            .build();
}
