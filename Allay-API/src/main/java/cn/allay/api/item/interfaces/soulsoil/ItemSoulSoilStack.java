package cn.allay.api.item.interfaces.soulsoil;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemSoulSoilStack extends ItemStack {
    ItemType<ItemSoulSoilStack> SOUL_SOIL_TYPE = ItemTypeBuilder
            .builder(ItemSoulSoilStack.class)
            .vanillaItem(VanillaItemId.SOUL_SOIL)
            .build();
}
