package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemFireChargeStack extends ItemStack {
    ItemType<ItemFireChargeStack> FIRE_CHARGE_TYPE = ItemTypeBuilder
            .builder(ItemFireChargeStack.class)
            .vanillaItem(VanillaItemId.FIRE_CHARGE)
            .build();
}
