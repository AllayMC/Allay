package org.allaymc.api.eventbus.event.container;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.annotation.CallerThread;
import org.allaymc.api.annotation.ThreadType;
import org.allaymc.api.blockentity.interfaces.BlockEntityBrewingStand;
import org.allaymc.api.eventbus.event.CancellableEvent;

/**
 * {@code BrewingStandStartBrewEvent} represents the start of a brew with matching bottle
 * recipes. Listeners may change the remaining tick count or cancel this start attempt;
 * the brewing component reads the adjusted time only after successful dispatch.
 *
 * @author IWareQ
 */
@Getter
@AllArgsConstructor
@CallerThread(ThreadType.DIMENSION)
public class BrewingStandStartBrewEvent extends ContainerEvent implements CancellableEvent {
    /**
     * Brewing stand starting the potion batch.
     */
    protected BlockEntityBrewingStand brewingStand;
    /**
     * {@code brewingTime} is the remaining duration in ticks read after dispatch. A
     * nonpositive value lets the component proceed to completion without another
     * countdown step.
     */
    @Setter
    protected int brewingTime;
}
