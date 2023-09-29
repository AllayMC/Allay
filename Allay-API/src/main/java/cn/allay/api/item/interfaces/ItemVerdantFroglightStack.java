package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemVerdantFroglightStack extends ItemStack {
  ItemType<ItemVerdantFroglightStack> VERDANT_FROGLIGHT_TYPE = ItemTypeBuilder
          .builder(ItemVerdantFroglightStack.class)
          .vanillaItem(VanillaItemId.VERDANT_FROGLIGHT)
          .build();
}
