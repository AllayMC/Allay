package cn.allay.item.impl;

/**
 * Author: daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemItem.hopper extends ItemStack{
        ItemType<ItemItem.hopper>TYPE=ItemTypeBuilder
        .builder(ItemItem.hopper.class)
        .vanillaItem(VanillaItemId.HOPPER,true)
        .addBasicComponents()
        .build().register(ItemTypeRegistry.getRegistry());
        }
