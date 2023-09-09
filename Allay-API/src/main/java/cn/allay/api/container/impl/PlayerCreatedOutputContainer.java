package cn.allay.api.container.impl;

import cn.allay.api.container.BaseContainer;
import cn.allay.api.item.ItemStack;

import static cn.allay.api.container.FullContainerType.CREATED_OUTPUT;

/**
 * Allay Project 2023/7/28
 *
 * @author daoge_cmd
 */
public class PlayerCreatedOutputContainer extends BaseContainer {

    public PlayerCreatedOutputContainer() {
        super(CREATED_OUTPUT);
    }

    @Override
    public ItemStack getItemStack(int slot) {
        //TODO: HACK: 别问我为什么mj会把CREATED_OUTPUT的输出槽位定成50，我也不知道
        return super.getItemStack(0);
    }

    @Override
    public void setItemStack(int slot, ItemStack itemStack) {
        super.setItemStack(0, itemStack);
    }
}
