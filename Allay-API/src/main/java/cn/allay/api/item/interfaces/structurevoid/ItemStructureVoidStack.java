package cn.allay.api.item.interfaces.structurevoid;

import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemStructureVoidStack extends ItemStack {
    ItemType<ItemStructureVoidStack> STRUCTURE_VOID_TYPE = ItemTypeBuilder
            .builder(ItemStructureVoidStack.class)
            .vanillaItem(VanillaItemId.STRUCTURE_VOID)
            .build();
}
