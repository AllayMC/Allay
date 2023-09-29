package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemOchreFroglightStack extends ItemStack {
  ItemType<ItemOchreFroglightStack> OCHRE_FROGLIGHT_TYPE = ItemTypeBuilder
          .builder(ItemOchreFroglightStack.class)
          .vanillaItem(VanillaItemId.OCHRE_FROGLIGHT)
          .build();
}
