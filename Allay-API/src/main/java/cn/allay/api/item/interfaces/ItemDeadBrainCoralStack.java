package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemDeadBrainCoralStack extends ItemStack {
    ItemType<ItemDeadBrainCoralStack> DEAD_BRAIN_CORAL_TYPE = ItemTypeBuilder
            .builder(ItemDeadBrainCoralStack.class)
            .vanillaItem(VanillaItemId.DEAD_BRAIN_CORAL)
            .build();
}
