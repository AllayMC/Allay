package cn.allay.api.item.interfaces.terracotta;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemYellowTerracottaStack extends ItemStack {
  ItemType<ItemYellowTerracottaStack> YELLOW_TERRACOTTA_TYPE = ItemTypeBuilder
          .builder(ItemYellowTerracottaStack.class)
          .vanillaItem(VanillaItemId.YELLOW_TERRACOTTA)
          .build();
}
