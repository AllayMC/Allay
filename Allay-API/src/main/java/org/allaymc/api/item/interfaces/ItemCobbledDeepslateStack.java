package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemCobbledDeepslateStack extends ItemStack {
  ItemType<ItemCobbledDeepslateStack> COBBLED_DEEPSLATE_TYPE = ItemTypeBuilder
          .builder(ItemCobbledDeepslateStack.class)
          .vanillaItem(VanillaItemId.COBBLED_DEEPSLATE)
          .build();
}
