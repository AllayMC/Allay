package org.allaymc.api.eventbus.event.container;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.annotation.CallerThread;
import org.allaymc.api.annotation.ThreadType;
import org.allaymc.api.blockentity.interfaces.BlockEntityBrewingStand;
import org.allaymc.api.eventbus.event.CancellableEvent;

/**
 * Called when a brewing stand starts brewing potions.
 *
 * @author IWareQ
 */
@Getter
@AllArgsConstructor
@CallerThread(ThreadType.WORLD)
public class BrewingStandStartBrewEvent extends ContainerEvent implements CancellableEvent {
    protected BlockEntityBrewingStand brewingStand;
    @Setter
    protected int brewingTime;
}
