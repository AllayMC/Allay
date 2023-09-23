package cn.allay.api.item.interfaces.darkoakdoor;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemDarkOakDoorStack extends ItemStack {
    ItemType<ItemDarkOakDoorStack> DARK_OAK_DOOR_TYPE = ItemTypeBuilder
            .builder(ItemDarkOakDoorStack.class)
            .vanillaItem(VanillaItemId.DARK_OAK_DOOR)
            .build();
}
