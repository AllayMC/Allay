package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemIronHorseArmorStack extends ItemStack {
  ItemType<ItemIronHorseArmorStack> IRON_HORSE_ARMOR_TYPE = ItemTypeBuilder
          .builder(ItemIronHorseArmorStack.class)
          .vanillaItem(VanillaItemId.IRON_HORSE_ARMOR)
          .build();
}
