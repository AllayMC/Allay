package org.allaymc.api.item.interfaces.coral;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

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
