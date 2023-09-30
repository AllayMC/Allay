package cn.allay.api.item.interfaces.terracotta;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemLightGrayTerracottaStack extends ItemStack {
  ItemType<ItemLightGrayTerracottaStack> LIGHT_GRAY_TERRACOTTA_TYPE = ItemTypeBuilder
          .builder(ItemLightGrayTerracottaStack.class)
          .vanillaItem(VanillaItemId.LIGHT_GRAY_TERRACOTTA)
          .build();
}
