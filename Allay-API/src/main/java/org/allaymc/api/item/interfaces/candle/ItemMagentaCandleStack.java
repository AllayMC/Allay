package org.allaymc.api.item.interfaces.candle;

import org.allaymc.api.data.VanillaItemId;
import org.allaymc.api.item.ItemStack;
import org.allaymc.api.item.type.ItemType;
import org.allaymc.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemMagentaCandleStack extends ItemStack {
  ItemType<ItemMagentaCandleStack> MAGENTA_CANDLE_TYPE = ItemTypeBuilder
          .builder(ItemMagentaCandleStack.class)
          .vanillaItem(VanillaItemId.MAGENTA_CANDLE)
          .build();
}
