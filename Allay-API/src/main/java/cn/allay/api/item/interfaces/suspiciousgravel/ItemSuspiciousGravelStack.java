package cn.allay.api.item.interfaces.suspiciousgravel;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemSuspiciousGravelStack extends ItemStack {
    ItemType<ItemSuspiciousGravelStack> SUSPICIOUS_GRAVEL_TYPE = ItemTypeBuilder
            .builder(ItemSuspiciousGravelStack.class)
            .vanillaItem(VanillaItemId.SUSPICIOUS_GRAVEL)
            .build();
}
