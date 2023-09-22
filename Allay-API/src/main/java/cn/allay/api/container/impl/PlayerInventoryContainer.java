package cn.allay.api.container.impl;

import cn.allay.api.client.Client;
import cn.allay.api.container.BaseContainer;
import cn.allay.api.container.FullContainerType;
import cn.allay.api.item.ItemStack;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.Range;

/**
 * Allay Project 2023/7/22
 *
 * @author daoge_cmd
 */
@Getter
public class PlayerInventoryContainer extends BaseContainer {

    @Setter
    @Range(from = 0, to = 8)
    protected int handSlot = 0;
    protected Client client;

    public PlayerInventoryContainer(Client client) {
        super(FullContainerType.PLAYER_INVENTORY);
        this.client = client;
    }

    public ItemStack getItemInHand() {
        return getItemStack(handSlot);
    }

    public void setItemInHand(ItemStack itemStack) {
        setItemStack(handSlot, itemStack);
    }

    @Override
    public void onSlotChange(int slot) {
        super.onSlotChange(slot);
        //因为尽管客户端没有打开player inventory，但是他始终能看到自己的物品栏。所以说我们需要给客户端也发送库存包
        if (!viewers.containsValue(client.getPlayerEntity())) {
            client.getPlayerEntity().onSlotChange(this, slot);
        }
    }
}
