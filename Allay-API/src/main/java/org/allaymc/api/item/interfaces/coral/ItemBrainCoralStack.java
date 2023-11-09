package org.allaymc.api.item.interfaces.coral;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemBrainCoralStack extends ItemStack {
  ItemType<ItemBrainCoralStack> BRAIN_CORAL_TYPE = ItemTypeBuilder
          .builder(ItemBrainCoralStack.class)
          .vanillaItem(VanillaItemId.BRAIN_CORAL)
          .build();
}
