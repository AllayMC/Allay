package org.allaymc.server.container.impl;

import org.allaymc.api.container.ContainerTypes;

/**
 * {@code BlastFurnaceContainerImpl} supplies 3-slot item storage for the blast furnace
 * container layout. The inherited furnace accessors and sided rules use the blast
 * furnace container identity. Its constructor selects the shared {@code BLAST_FURNACE}
 * type, and the base implementation manages item assignment and container observers.
 *
 * @author daoge_cmd
 */
public class BlastFurnaceContainerImpl extends FurnaceContainerImpl {
    /**
     * Creates empty blast furnace storage using the shared {@code BLAST_FURNACE}
     * container type.
     */
    public BlastFurnaceContainerImpl() {
        super(ContainerTypes.BLAST_FURNACE);
    }
}
