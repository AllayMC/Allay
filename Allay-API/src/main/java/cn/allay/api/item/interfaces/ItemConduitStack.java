package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemConduitStack extends ItemStack {
  ItemType<ItemConduitStack> CONDUIT_TYPE = ItemTypeBuilder
          .builder(ItemConduitStack.class)
          .vanillaItem(VanillaItemId.CONDUIT)
          .build();
}
