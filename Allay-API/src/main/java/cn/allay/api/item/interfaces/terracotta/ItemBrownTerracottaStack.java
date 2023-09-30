package cn.allay.api.item.interfaces.terracotta;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemBrownTerracottaStack extends ItemStack {
  ItemType<ItemBrownTerracottaStack> BROWN_TERRACOTTA_TYPE = ItemTypeBuilder
          .builder(ItemBrownTerracottaStack.class)
          .vanillaItem(VanillaItemId.BROWN_TERRACOTTA)
          .build();
}
