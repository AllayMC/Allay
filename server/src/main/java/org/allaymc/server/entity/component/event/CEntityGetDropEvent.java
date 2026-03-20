package org.allaymc.server.entity.component.event;

import lombok.Getter;
import org.allaymc.api.eventbus.event.Event;
import org.allaymc.api.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

/**
 * @author daoge_cmd
 */
public class CEntityGetDropEvent extends Event {
    @Getter
    private final int lootingLevel;
    @Getter
    private final List<ItemStack> drops = new ArrayList<>();

    public CEntityGetDropEvent(int lootingLevel) {
        this.lootingLevel = lootingLevel;
    }
}
