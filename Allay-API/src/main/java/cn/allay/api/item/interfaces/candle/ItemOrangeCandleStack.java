package cn.allay.api.item.interfaces.candle;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemOrangeCandleStack extends ItemStack {
  ItemType<ItemOrangeCandleStack> ORANGE_CANDLE_TYPE = ItemTypeBuilder
          .builder(ItemOrangeCandleStack.class)
          .vanillaItem(VanillaItemId.ORANGE_CANDLE)
          .build();
}
