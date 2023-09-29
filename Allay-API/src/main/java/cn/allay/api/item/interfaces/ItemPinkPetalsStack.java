package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemPinkPetalsStack extends ItemStack {
  ItemType<ItemPinkPetalsStack> PINK_PETALS_TYPE = ItemTypeBuilder
          .builder(ItemPinkPetalsStack.class)
          .vanillaItem(VanillaItemId.PINK_PETALS)
          .build();
}
