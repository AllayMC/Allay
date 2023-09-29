package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemLightningRodStack extends ItemStack {
  ItemType<ItemLightningRodStack> LIGHTNING_ROD_TYPE = ItemTypeBuilder
          .builder(ItemLightningRodStack.class)
          .vanillaItem(VanillaItemId.LIGHTNING_ROD)
          .build();
}
