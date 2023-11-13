package org.allaymc.server.block.registry;

import org.cloudburstmc.nbt.NbtMap;

public class BlockStateMissingException extends RuntimeException {
    public BlockStateMissingException(NbtMap blockNBT) {
        super(blockNBT.toSNBT());
    }

    @Override
    public synchronized Throwable fillInStackTrace() {
        return null;
    }
}
