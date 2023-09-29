package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemBambooMosaicStack extends ItemStack {
  ItemType<ItemBambooMosaicStack> BAMBOO_MOSAIC_TYPE = ItemTypeBuilder
          .builder(ItemBambooMosaicStack.class)
          .vanillaItem(VanillaItemId.BAMBOO_MOSAIC)
          .build();
}
