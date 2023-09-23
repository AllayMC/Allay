package cn.allay.api.item.interfaces.twistingvines;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemTwistingVinesStack extends ItemStack {
    ItemType<ItemTwistingVinesStack> TWISTING_VINES_TYPE = ItemTypeBuilder
            .builder(ItemTwistingVinesStack.class)
            .vanillaItem(VanillaItemId.TWISTING_VINES)
            .build();
}
