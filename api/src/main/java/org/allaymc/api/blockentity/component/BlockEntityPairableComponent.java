package org.allaymc.api.blockentity.component;

import org.allaymc.api.blockentity.BlockEntity;

/**
 * {@code BlockEntityPairableComponent} exposes a recorded pairing and lead
 * selection for block entities such as chests. The server stores horizontal
 * partner coordinates separately from live partner lookup, and callers
 * establish or remove the relationship on both entities when reciprocity is
 * required.
 *
 * @author IWareQ
 */
public interface BlockEntityPairableComponent extends BlockEntityComponent {
    /**
     * Records a partner when this entity does not already have pair
     * coordinates. The server requires a compatible live partner in the same
     * dimension and height and updates only this side; callers establish the
     * reciprocal link separately.
     */
    boolean tryPairWith(BlockEntity other);

    /**
     * Clears this entity's recorded partner and lead flag and updates viewers.
     * The server does not clear the other entity's relationship automatically,
     * so callers remove that reciprocal state separately when needed.
     */
    void unpair();

    /**
     * Looks up the live entity at this side's recorded partner coordinates.
     * Callers first check {@code isPaired}; the server can fail for absent
     * coordinates and can return {@code null} when the recorded location has no
     * block entity.
     */
    BlockEntity getPair();

    /**
     * Returns whether this entity retains a complete pair-coordinate record.
     * The server checks coordinate presence without resolving the live partner,
     * so a {@code true} result does not establish that the partner still
     * exists.
     */
    boolean isPaired();

    /**
     * Returns whether this entity retains the lead flag for its pairing.
     */
    boolean isLead();
}
