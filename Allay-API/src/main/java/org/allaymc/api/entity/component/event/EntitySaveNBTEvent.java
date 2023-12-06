package org.allaymc.api.entity.component.event;

import org.allaymc.api.component.interfaces.ComponentEvent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.cloudburstmc.nbt.NbtMapBuilder;

/**
 * Allay Project 2023/9/23
 *
 * @author daoge_cmd
 */
@Getter
@Setter
@AllArgsConstructor
public class EntitySaveNBTEvent implements ComponentEvent {
    private NbtMapBuilder nbt;
}
