package org.allaymc.api.eventbus.event.block;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.allaymc.api.block.data.BlockStateWithPos;
import org.allaymc.api.eventbus.event.Event;

/**
 * Allay Project 2024/8/8
 *
 * @author daoge_cmd
 */
@AllArgsConstructor
@Getter
public abstract class BlockEvent extends Event {
    protected BlockStateWithPos block;
}
