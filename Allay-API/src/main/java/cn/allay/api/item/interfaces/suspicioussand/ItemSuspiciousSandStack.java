package cn.allay.api.item.interfaces.suspicioussand;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemSuspiciousSandStack extends ItemStack {
    ItemType<ItemSuspiciousSandStack> SUSPICIOUS_SAND_TYPE = ItemTypeBuilder
            .builder(ItemSuspiciousSandStack.class)
            .vanillaItem(VanillaItemId.SUSPICIOUS_SAND)
            .build();
}
