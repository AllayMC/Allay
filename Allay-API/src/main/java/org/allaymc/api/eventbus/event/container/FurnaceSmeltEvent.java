package org.allaymc.api.eventbus.event.container;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.allaymc.api.blockentity.interfaces.BlockEntityFurnace;
import org.allaymc.api.eventbus.event.CancellableEvent;
import org.allaymc.api.item.ItemStack;

/**
 * Allay Project 2024/8/12
 *
 * @author daoge_cmd
 */
@Getter
@AllArgsConstructor
public class FurnaceSmeltEvent extends ContainerEvent implements CancellableEvent {
    protected BlockEntityFurnace blockEntityFurnace;
    protected ItemStack ingredient;
    protected ItemStack output;
}
