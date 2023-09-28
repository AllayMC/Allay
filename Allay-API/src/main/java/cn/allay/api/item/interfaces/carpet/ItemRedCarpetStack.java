package cn.allay.api.item.interfaces.carpet;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemRedCarpetStack extends ItemStack {
  ItemType<ItemRedCarpetStack> RED_CARPET_TYPE = ItemTypeBuilder
          .builder(ItemRedCarpetStack.class)
          .vanillaItem(VanillaItemId.RED_CARPET)
          .build();
}
