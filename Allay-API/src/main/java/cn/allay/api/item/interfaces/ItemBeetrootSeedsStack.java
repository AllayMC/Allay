package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemBeetrootSeedsStack extends ItemStack {
  ItemType<ItemBeetrootSeedsStack> BEETROOT_SEEDS_TYPE = ItemTypeBuilder
          .builder(ItemBeetrootSeedsStack.class)
          .vanillaItem(VanillaItemId.BEETROOT_SEEDS)
          .build();
}
