package org.allaymc.api.blockentity.component.event;

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
public class BlockEntitySaveNBTEvent {
    private NbtMapBuilder nbt;
}
