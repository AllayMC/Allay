package cn.allay.api.item.interfaces.terracotta;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemSilverGlazedTerracottaStack extends ItemStack {
  ItemType<ItemSilverGlazedTerracottaStack> SILVER_GLAZED_TERRACOTTA_TYPE = ItemTypeBuilder
          .builder(ItemSilverGlazedTerracottaStack.class)
          .vanillaItem(VanillaItemId.SILVER_GLAZED_TERRACOTTA)
          .build();
}
