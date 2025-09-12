package org.allaymc.api.container.impl;

import org.allaymc.api.container.FullContainerType;
import org.allaymc.api.container.UnopenedContainerId;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.item.ItemStack;

import java.util.function.Supplier;

/**
 * @author daoge_cmd
 */
public class PlayerOffhandContainer extends PlayerContainer {

    public static final int OFFHAND_SLOT = 0;

    public PlayerOffhandContainer(Supplier<EntityPlayer> playerSupplier) {
        super(FullContainerType.OFFHAND, playerSupplier);
        addSlotChangeListener(0, this::onOffhandChange);
    }

    public ItemStack getOffhand() {
        return getItemStack(OFFHAND_SLOT);
    }

    public void setOffhand(ItemStack itemStack) {
        setItemStack(OFFHAND_SLOT, itemStack);
    }

    @Override
    public int getUnopenedContainerId() {
        return UnopenedContainerId.OFFHAND;
    }

    protected void onOffhandChange(ItemStack newItemStack) {
        var player = playerSupplier.get();
        player.forEachViewers(viewer -> viewer.viewEntityOffhand(player));
    }
}
