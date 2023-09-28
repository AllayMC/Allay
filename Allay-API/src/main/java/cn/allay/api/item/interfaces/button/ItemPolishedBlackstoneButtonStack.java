package cn.allay.api.item.interfaces.button;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemPolishedBlackstoneButtonStack extends ItemStack {
  ItemType<ItemPolishedBlackstoneButtonStack> POLISHED_BLACKSTONE_BUTTON_TYPE = ItemTypeBuilder
          .builder(ItemPolishedBlackstoneButtonStack.class)
          .vanillaItem(VanillaItemId.POLISHED_BLACKSTONE_BUTTON)
          .build();
}
