package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemPotatoesStack extends ItemStack {
  ItemType<ItemPotatoesStack> POTATOES_TYPE = ItemTypeBuilder
          .builder(ItemPotatoesStack.class)
          .vanillaItem(VanillaItemId.POTATOES)
          .build();
}
