package cn.allay.item.impl;

/**
 * Author: daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemItem.flowerPot extends ItemStack{
        ItemType<ItemItem.flowerPot>TYPE=ItemTypeBuilder
        .builder(ItemItem.flowerPot.class)
        .vanillaItem(VanillaItemId.FLOWER_POT,true)
        .addBasicComponents()
        .build().register(ItemTypeRegistry.getRegistry());
        }
