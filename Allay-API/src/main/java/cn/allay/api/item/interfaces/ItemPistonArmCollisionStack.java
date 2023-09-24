package cn.allay.api.item.interfaces;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemPistonArmCollisionStack extends ItemStack {
    ItemType<ItemPistonArmCollisionStack> PISTON_ARM_COLLISION_TYPE = ItemTypeBuilder
            .builder(ItemPistonArmCollisionStack.class)
            .vanillaItem(VanillaItemId.PISTON_ARM_COLLISION)
            .build();
}
