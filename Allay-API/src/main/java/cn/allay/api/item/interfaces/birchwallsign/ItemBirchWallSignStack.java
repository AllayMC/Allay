package cn.allay.api.item.interfaces.birchwallsign;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemBirchWallSignStack extends ItemStack {
    ItemType<ItemBirchWallSignStack> BIRCH_WALL_SIGN_TYPE = ItemTypeBuilder
            .builder(ItemBirchWallSignStack.class)
            .vanillaItem(VanillaItemId.BIRCH_WALL_SIGN)
            .build();
}
