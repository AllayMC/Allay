package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemDeadHornCoralStack extends ItemStack {
    ItemType<ItemDeadHornCoralStack> DEAD_HORN_CORAL_TYPE = ItemTypeBuilder
            .builder(ItemDeadHornCoralStack.class)
            .vanillaItem(VanillaItemId.DEAD_HORN_CORAL)
            .build();
}
