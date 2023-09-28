package cn.allay.api.item.interfaces.coral;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemBubbleCoralStack extends ItemStack {
  ItemType<ItemBubbleCoralStack> BUBBLE_CORAL_TYPE = ItemTypeBuilder
          .builder(ItemBubbleCoralStack.class)
          .vanillaItem(VanillaItemId.BUBBLE_CORAL)
          .build();
}
