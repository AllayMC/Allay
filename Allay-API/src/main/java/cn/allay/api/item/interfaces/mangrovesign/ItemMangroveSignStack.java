package cn.allay.api.item.interfaces.mangrovesign;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemMangroveSignStack extends ItemStack {
    ItemType<ItemMangroveSignStack> MANGROVE_SIGN_TYPE = ItemTypeBuilder
            .builder(ItemMangroveSignStack.class)
            .vanillaItem(VanillaItemId.MANGROVE_SIGN)
            .build();
}
