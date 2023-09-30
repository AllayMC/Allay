package cn.allay.api.item.interfaces.glass;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemGlassStack extends ItemStack {
  ItemType<ItemGlassStack> GLASS_TYPE = ItemTypeBuilder
          .builder(ItemGlassStack.class)
          .vanillaItem(VanillaItemId.GLASS)
          .build();
}
