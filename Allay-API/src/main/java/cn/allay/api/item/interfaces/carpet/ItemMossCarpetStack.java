package cn.allay.api.item.interfaces.carpet;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemMossCarpetStack extends ItemStack {
  ItemType<ItemMossCarpetStack> MOSS_CARPET_TYPE = ItemTypeBuilder
          .builder(ItemMossCarpetStack.class)
          .vanillaItem(VanillaItemId.MOSS_CARPET)
          .build();
}
