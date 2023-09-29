package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemBrushStack extends ItemStack {
  ItemType<ItemBrushStack> BRUSH_TYPE = ItemTypeBuilder
          .builder(ItemBrushStack.class)
          .vanillaItem(VanillaItemId.BRUSH)
          .build();
}
