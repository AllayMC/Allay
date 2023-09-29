package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemPumpkinSeedsStack extends ItemStack {
  ItemType<ItemPumpkinSeedsStack> PUMPKIN_SEEDS_TYPE = ItemTypeBuilder
          .builder(ItemPumpkinSeedsStack.class)
          .vanillaItem(VanillaItemId.PUMPKIN_SEEDS)
          .build();
}
