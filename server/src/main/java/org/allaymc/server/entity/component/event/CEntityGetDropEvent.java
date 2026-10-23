package org.allaymc.server.entity.component.event;

import lombok.Getter;
import org.allaymc.api.eventbus.event.Event;
import org.allaymc.api.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

/**
 * {@code CEntityGetDropEvent} accumulates item drops from an entity's
 * component handlers for a specified looting level. The event owns one mutable
 * list shared with handlers and the death caller, allowing living loot and
 * container contents to contribute item references to the same collection.
 *
 * @author daoge_cmd
 */
public class CEntityGetDropEvent extends Event {
    /**
     * Looting enchantment level supplied to drop-producing handlers.
     */
    @Getter
    private final int lootingLevel;
    /**
     * {@code drops} collects mutable item references from all participating
     * handlers. The generated getter exposes the same list later consumed by
     * death processing, so handlers append their drops directly.
     */
    @Getter
    private final List<ItemStack> drops = new ArrayList<>();

    /**
     * Creates an empty drop accumulator with the requested looting enchantment
     * level.
     */
    public CEntityGetDropEvent(int lootingLevel) {
        this.lootingLevel = lootingLevel;
    }
}
