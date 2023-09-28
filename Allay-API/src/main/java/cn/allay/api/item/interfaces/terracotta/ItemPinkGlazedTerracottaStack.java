package cn.allay.api.item.interfaces.terracotta;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemPinkGlazedTerracottaStack extends ItemStack {
  ItemType<ItemPinkGlazedTerracottaStack> PINK_GLAZED_TERRACOTTA_TYPE = ItemTypeBuilder
          .builder(ItemPinkGlazedTerracottaStack.class)
          .vanillaItem(VanillaItemId.PINK_GLAZED_TERRACOTTA)
          .build();
}
