package org.allaymc.api.eventbus.event.block;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.allaymc.api.block.dto.BlockStateWithPos;
import org.allaymc.api.eventbus.event.Event;

/**
 * @author daoge_cmd
 */
@AllArgsConstructor
@Getter
public abstract class BlockEvent extends Event {
    protected BlockStateWithPos block;
}
