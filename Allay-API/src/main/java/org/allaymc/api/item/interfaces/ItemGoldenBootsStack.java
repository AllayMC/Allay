package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemGoldenBootsStack extends ItemStack {
  ItemType<ItemGoldenBootsStack> GOLDEN_BOOTS_TYPE = ItemTypeBuilder
          .builder(ItemGoldenBootsStack.class)
          .vanillaItem(VanillaItemId.GOLDEN_BOOTS)
          .build();
}
