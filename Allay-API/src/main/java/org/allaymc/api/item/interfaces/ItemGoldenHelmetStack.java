package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemGoldenHelmetStack extends ItemStack {
  ItemType<ItemGoldenHelmetStack> GOLDEN_HELMET_TYPE = ItemTypeBuilder
          .builder(ItemGoldenHelmetStack.class)
          .vanillaItem(VanillaItemId.GOLDEN_HELMET)
          .build();
}
