package org.allaymc.api.eventbus.event.block;

import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.annotation.CallerThread;
import org.allaymc.api.annotation.ThreadType;
import org.allaymc.api.block.dto.Block;
import org.allaymc.api.blockentity.interfaces.BlockEntityCommandBlock;
import org.allaymc.api.eventbus.event.CancellableEvent;

/**
 * {@code CommandBlockExecuteEvent} represents a command block's pending command
 * execution. The command block supplies the command source, and listeners can replace
 * the command text; cancellation stops that execution path before the command
 * registry receives the text.
 *
 * @author daoge_cmd
 */
@Getter
@CallerThread(ThreadType.WORLD)
public class CommandBlockExecuteEvent extends BlockEvent implements CancellableEvent {
    /**
     * Executing command block entity.
     */
    protected BlockEntityCommandBlock commandBlock;
    /**
     * {@code command} holds the text passed to the command registry after dispatch.
     * Listeners can replace it before execution begins.
     */
    @Setter
    protected String command;

    /**
     * Creates an execution event retaining the command block and command text.
     */
    public CommandBlockExecuteEvent(Block block, BlockEntityCommandBlock commandBlock, String command) {
        super(block);
        this.commandBlock = commandBlock;
        this.command = command;
    }
}
