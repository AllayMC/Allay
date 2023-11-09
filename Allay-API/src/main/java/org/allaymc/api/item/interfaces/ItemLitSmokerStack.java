package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemLitSmokerStack extends ItemStack {
  ItemType<ItemLitSmokerStack> LIT_SMOKER_TYPE = ItemTypeBuilder
          .builder(ItemLitSmokerStack.class)
          .vanillaItem(VanillaItemId.LIT_SMOKER)
          .build();
}
