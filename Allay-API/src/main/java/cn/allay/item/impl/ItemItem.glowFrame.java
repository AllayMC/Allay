package cn.allay.item.impl;

/**
 * Author: daoge_cmd <br>
 * Allay Project <br>
 */
public interface ItemItem.glowFrame extends ItemStack{
        ItemType<ItemItem.glowFrame>TYPE=ItemTypeBuilder
        .builder(ItemItem.glowFrame.class)
        .vanillaItem(VanillaItemId.GLOW_FRAME,true)
        .addBasicComponents()
        .build().register(ItemTypeRegistry.getRegistry());
        }
