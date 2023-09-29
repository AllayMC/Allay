package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemNetheriteSwordStack extends ItemStack {
  ItemType<ItemNetheriteSwordStack> NETHERITE_SWORD_TYPE = ItemTypeBuilder
          .builder(ItemNetheriteSwordStack.class)
          .vanillaItem(VanillaItemId.NETHERITE_SWORD)
          .build();
}
