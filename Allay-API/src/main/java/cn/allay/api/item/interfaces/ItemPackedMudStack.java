package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemPackedMudStack extends ItemStack {
  ItemType<ItemPackedMudStack> PACKED_MUD_TYPE = ItemTypeBuilder
          .builder(ItemPackedMudStack.class)
          .vanillaItem(VanillaItemId.PACKED_MUD)
          .build();
}
