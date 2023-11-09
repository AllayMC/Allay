package org.allaymc.api.item.interfaces.candle;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemRedCandleStack extends ItemStack {
  ItemType<ItemRedCandleStack> RED_CANDLE_TYPE = ItemTypeBuilder
          .builder(ItemRedCandleStack.class)
          .vanillaItem(VanillaItemId.RED_CANDLE)
          .build();
}
