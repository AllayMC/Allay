package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemAncientDebrisStack extends ItemStack {
  ItemType<ItemAncientDebrisStack> ANCIENT_DEBRIS_TYPE = ItemTypeBuilder
          .builder(ItemAncientDebrisStack.class)
          .vanillaItem(VanillaItemId.ANCIENT_DEBRIS)
          .build();
}
