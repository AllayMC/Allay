package cn.allay.api.item.interfaces.candlecake;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemBrownCandleCakeStack extends ItemStack {
  ItemType<ItemBrownCandleCakeStack> BROWN_CANDLE_CAKE_TYPE = ItemTypeBuilder
          .builder(ItemBrownCandleCakeStack.class)
          .vanillaItem(VanillaItemId.BROWN_CANDLE_CAKE)
          .build();
}
