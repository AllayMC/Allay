package cn.allay.api.item.interfaces.slab;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemMudBrickDoubleSlabStack extends ItemStack {
  ItemType<ItemMudBrickDoubleSlabStack> MUD_BRICK_DOUBLE_SLAB_TYPE = ItemTypeBuilder
          .builder(ItemMudBrickDoubleSlabStack.class)
          .vanillaItem(VanillaItemId.MUD_BRICK_DOUBLE_SLAB)
          .build();
}
