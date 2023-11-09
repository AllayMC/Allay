package org.allaymc.api.item.interfaces;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemRapidFertilizerStack extends ItemStack {
  ItemType<ItemRapidFertilizerStack> RAPID_FERTILIZER_TYPE = ItemTypeBuilder
          .builder(ItemRapidFertilizerStack.class)
          .vanillaItem(VanillaItemId.RAPID_FERTILIZER)
          .build();
}
