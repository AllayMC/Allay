package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemShroomlightStack extends ItemStack {
  ItemType<ItemShroomlightStack> SHROOMLIGHT_TYPE = ItemTypeBuilder
          .builder(ItemShroomlightStack.class)
          .vanillaItem(VanillaItemId.SHROOMLIGHT)
          .build();
}
