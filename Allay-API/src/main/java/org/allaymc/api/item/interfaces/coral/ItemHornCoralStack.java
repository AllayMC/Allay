package org.allaymc.api.item.interfaces.coral;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemHornCoralStack extends ItemStack {
  ItemType<ItemHornCoralStack> HORN_CORAL_TYPE = ItemTypeBuilder
          .builder(ItemHornCoralStack.class)
          .vanillaItem(VanillaItemId.HORN_CORAL)
          .build();
}
