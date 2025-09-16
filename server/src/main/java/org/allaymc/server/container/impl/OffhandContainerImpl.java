package org.allaymc.server.container.impl;

import org.allaymc.api.container.ContainerType;
import org.allaymc.api.container.interfaces.OffhandContainer;
import org.allaymc.api.entity.interfaces.EntityPlayer;
import org.allaymc.api.item.ItemStack;

import java.util.function.Supplier;

/**
 * @author daoge_cmd
 */
public class OffhandContainerImpl extends AbstractPlayerContainer implements OffhandContainer {

    public OffhandContainerImpl(Supplier<EntityPlayer> playerSupplier) {
        super(ContainerType.OFFHAND, playerSupplier);
        addSlotChangeListener(0, this::onOffhandChange);
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
