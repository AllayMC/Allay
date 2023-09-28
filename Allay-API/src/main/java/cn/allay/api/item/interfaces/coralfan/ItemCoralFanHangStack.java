package cn.allay.api.item.interfaces.coralfan;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemCoralFanHangStack extends ItemStack {
  ItemType<ItemCoralFanHangStack> CORAL_FAN_HANG_TYPE = ItemTypeBuilder
          .builder(ItemCoralFanHangStack.class)
          .vanillaItem(VanillaItemId.CORAL_FAN_HANG)
          .build();
}
