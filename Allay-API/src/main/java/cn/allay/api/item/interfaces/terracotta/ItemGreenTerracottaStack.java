package cn.allay.api.item.interfaces.terracotta;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemGreenTerracottaStack extends ItemStack {
  ItemType<ItemGreenTerracottaStack> GREEN_TERRACOTTA_TYPE = ItemTypeBuilder
          .builder(ItemGreenTerracottaStack.class)
          .vanillaItem(VanillaItemId.GREEN_TERRACOTTA)
          .build();
}
