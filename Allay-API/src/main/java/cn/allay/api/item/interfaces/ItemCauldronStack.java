package cn.allay.api.item.interfaces;

import cn.allay.api.component.interfaces.ComponentProvider;
import cn.allay.api.data.VanillaBlockId;
import cn.allay.api.data.VanillaItemId;
import cn.allay.api.item.CommonUseItemFunctions;
import cn.allay.api.item.ItemStack;
import cn.allay.api.item.component.base.ItemBaseComponentImpl;
import cn.allay.api.item.init.ItemStackInitInfo;
import cn.allay.api.item.type.ItemType;
import cn.allay.api.item.type.ItemTypeBuilder;

/**
 * @author daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemCauldronStack extends ItemStack {
    ItemType<ItemCauldronStack> CAULDRON_TYPE = ItemTypeBuilder
            .builder(ItemCauldronStack.class)
            .vanillaItem(VanillaItemId.CAULDRON)
            .blockIdentifier(VanillaBlockId.CAULDRON.getIdentifier())
            .addComponent(ComponentProvider.of(
                    initInfo -> new ItemBaseComponentImpl<>((ItemStackInitInfo<?>) initInfo, CommonUseItemFunctions.createPlaceBlockUseOn()),
                    ItemBaseComponentImpl.class))
            .build();
}
