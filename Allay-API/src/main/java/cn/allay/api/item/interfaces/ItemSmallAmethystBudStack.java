package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemSmallAmethystBudStack extends ItemStack {
  ItemType<ItemSmallAmethystBudStack> SMALL_AMETHYST_BUD_TYPE = ItemTypeBuilder
          .builder(ItemSmallAmethystBudStack.class)
          .vanillaItem(VanillaItemId.SMALL_AMETHYST_BUD)
          .build();
}
