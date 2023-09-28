package cn.allay.api.item.interfaces.candle;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemPinkCandleStack extends ItemStack {
  ItemType<ItemPinkCandleStack> PINK_CANDLE_TYPE = ItemTypeBuilder
          .builder(ItemPinkCandleStack.class)
          .vanillaItem(VanillaItemId.PINK_CANDLE)
          .build();
}
