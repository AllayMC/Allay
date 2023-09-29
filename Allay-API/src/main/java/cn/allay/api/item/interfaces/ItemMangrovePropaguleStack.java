package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemMangrovePropaguleStack extends ItemStack {
  ItemType<ItemMangrovePropaguleStack> MANGROVE_PROPAGULE_TYPE = ItemTypeBuilder
          .builder(ItemMangrovePropaguleStack.class)
          .vanillaItem(VanillaItemId.MANGROVE_PROPAGULE)
          .build();
}
