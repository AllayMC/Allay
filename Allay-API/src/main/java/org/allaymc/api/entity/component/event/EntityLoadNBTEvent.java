package org.allaymc.api.entity.component.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.component.interfaces.ComponentEvent;
import org.cloudburstmc.nbt.NbtMap;

/**
 * Allay Project 2023/9/23
 *
 * @author daoge_cmd
 */
@Getter
@Setter
@AllArgsConstructor
public class EntityLoadNBTEvent implements ComponentEvent {
    private NbtMap nbt;
}
