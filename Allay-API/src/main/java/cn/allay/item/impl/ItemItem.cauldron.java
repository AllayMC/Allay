package cn.allay.item.impl;

/**
 * Author: daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemItem.cauldron extends ItemStack{
        ItemType<ItemItem.cauldron>TYPE=ItemTypeBuilder
        .builder(ItemItem.cauldron.class)
        .vanillaItem(VanillaItemId.CAULDRON,true)
        .addBasicComponents()
        .build().register(ItemTypeRegistry.getRegistry());
        }
