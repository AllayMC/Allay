package cn.allay.api.item.interfaces.candlecake;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemYellowCandleCakeStack extends ItemStack {
  ItemType<ItemYellowCandleCakeStack> YELLOW_CANDLE_CAKE_TYPE = ItemTypeBuilder
          .builder(ItemYellowCandleCakeStack.class)
          .vanillaItem(VanillaItemId.YELLOW_CANDLE_CAKE)
          .build();
}
