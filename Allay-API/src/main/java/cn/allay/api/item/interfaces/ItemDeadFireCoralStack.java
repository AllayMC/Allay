package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemDeadFireCoralStack extends ItemStack {
    ItemType<ItemDeadFireCoralStack> DEAD_FIRE_CORAL_TYPE = ItemTypeBuilder
            .builder(ItemDeadFireCoralStack.class)
            .vanillaItem(VanillaItemId.DEAD_FIRE_CORAL)
            .build();
}
