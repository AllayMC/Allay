package org.allaymc.api.blockentity.component;

import org.allaymc.api.blockentity.BlockEntity;

/**
 * @author IWareQ
 */
public interface BlockEntityPairableComponent extends BlockEntityComponent {
    /**
     * Attempts to pair this block entity with another block entity.
     *
     * @param other the {@link BlockEntity} to attempt pairing with
     *
     * @return {@code true} if pairing was successful, {@code false} otherwise
     */
    boolean tryPairWith(BlockEntity other);

    /**
     * Removes the pairing relationship between this block entity and its paired entity, if any.
     */
    void unpair();

    /**
     * Retrieves the block entity paired with this one, if it exists.
     *
     * @return the paired {@link BlockEntity}, or {@code null} if no pairing exists
     */
    BlockEntity getPair();

    /**
     * Checks if this block entity is currently paired with another.
     *
     * @return {@code true} if this block entity is paired, {@code false} otherwise
     */
    boolean isPaired();

    /**
     * Determines if this block entity is the lead in its pairing relationship.
     *
     * @return {@code true} if this block entity is the lead, {@code false} otherwise
     */
    boolean isLead();
}
