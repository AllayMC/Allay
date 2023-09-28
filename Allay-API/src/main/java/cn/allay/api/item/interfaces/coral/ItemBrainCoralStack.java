package cn.allay.api.item.interfaces.coral;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemBrainCoralStack extends ItemStack {
  ItemType<ItemBrainCoralStack> BRAIN_CORAL_TYPE = ItemTypeBuilder
          .builder(ItemBrainCoralStack.class)
          .vanillaItem(VanillaItemId.BRAIN_CORAL)
          .build();
}
