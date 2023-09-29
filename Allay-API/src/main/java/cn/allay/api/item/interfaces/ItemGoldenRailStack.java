package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemGoldenRailStack extends ItemStack {
  ItemType<ItemGoldenRailStack> GOLDEN_RAIL_TYPE = ItemTypeBuilder
          .builder(ItemGoldenRailStack.class)
          .vanillaItem(VanillaItemId.GOLDEN_RAIL)
          .build();
}
