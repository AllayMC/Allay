package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemHardStainedGlassStack extends ItemStack {
  ItemType<ItemHardStainedGlassStack> HARD_STAINED_GLASS_TYPE = ItemTypeBuilder
          .builder(ItemHardStainedGlassStack.class)
          .vanillaItem(VanillaItemId.HARD_STAINED_GLASS)
          .build();
}
