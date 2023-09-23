package cn.allay.api.item.interfaces.boneblock;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemBoneBlockStack extends ItemStack {
    ItemType<ItemBoneBlockStack> BONE_BLOCK_TYPE = ItemTypeBuilder
            .builder(ItemBoneBlockStack.class)
            .vanillaItem(VanillaItemId.BONE_BLOCK)
            .build();
}
