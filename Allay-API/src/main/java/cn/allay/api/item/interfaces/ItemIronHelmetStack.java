package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemIronHelmetStack extends ItemStack {
  ItemType<ItemIronHelmetStack> IRON_HELMET_TYPE = ItemTypeBuilder
          .builder(ItemIronHelmetStack.class)
          .vanillaItem(VanillaItemId.IRON_HELMET)
          .build();
}
