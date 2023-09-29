package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemLeatherHorseArmorStack extends ItemStack {
  ItemType<ItemLeatherHorseArmorStack> LEATHER_HORSE_ARMOR_TYPE = ItemTypeBuilder
          .builder(ItemLeatherHorseArmorStack.class)
          .vanillaItem(VanillaItemId.LEATHER_HORSE_ARMOR)
          .build();
}
