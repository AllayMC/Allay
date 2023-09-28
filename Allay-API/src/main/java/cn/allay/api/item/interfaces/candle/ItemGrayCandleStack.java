package cn.allay.api.item.interfaces.candle;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemGrayCandleStack extends ItemStack {
  ItemType<ItemGrayCandleStack> GRAY_CANDLE_TYPE = ItemTypeBuilder
          .builder(ItemGrayCandleStack.class)
          .vanillaItem(VanillaItemId.GRAY_CANDLE)
          .build();
}
