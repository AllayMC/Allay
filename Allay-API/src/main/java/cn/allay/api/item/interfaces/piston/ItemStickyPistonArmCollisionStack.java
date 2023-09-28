package cn.allay.api.item.interfaces.piston;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemStickyPistonArmCollisionStack extends ItemStack {
  ItemType<ItemStickyPistonArmCollisionStack> STICKY_PISTON_ARM_COLLISION_TYPE = ItemTypeBuilder
          .builder(ItemStickyPistonArmCollisionStack.class)
          .vanillaItem(VanillaItemId.STICKY_PISTON_ARM_COLLISION)
          .build();
}
