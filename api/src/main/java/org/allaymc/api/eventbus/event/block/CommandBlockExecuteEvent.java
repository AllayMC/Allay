package org.allaymc.api.eventbus.event.block;

import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.annotation.CallerThread;
import org.allaymc.api.annotation.ThreadType;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.blockentity.interfaces.BlockEntityCommandBlock;
import org.allaymc.api.eventbus.event.CancellableEvent;

/**
 * Called when a command block is about to execute a command.
 *
 * @author daoge_cmd
 */
@Getter
@CallerThread(ThreadType.WORLD)
public class CommandBlockExecuteEvent extends BlockEvent implements CancellableEvent {
    protected BlockEntityCommandBlock commandBlock;
    @Setter
    protected String command;

    public CommandBlockExecuteEvent(Block block, BlockEntityCommandBlock commandBlock, String command) {
        super(block);
        this.commandBlock = commandBlock;
        this.command = command;
    }
}
