package cn.allay.api.item.interfaces.deadbubblecoral;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemDeadBubbleCoralStack extends ItemStack {
    ItemType<ItemDeadBubbleCoralStack> DEAD_BUBBLE_CORAL_TYPE = ItemTypeBuilder
            .builder(ItemDeadBubbleCoralStack.class)
            .vanillaItem(VanillaItemId.DEAD_BUBBLE_CORAL)
            .build();
}
