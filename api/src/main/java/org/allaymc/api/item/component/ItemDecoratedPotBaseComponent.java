package org.allaymc.api.item.component;

import org.allaymc.api.block.data.PotSherds;

/**
 * {@code ItemDecoratedPotBaseComponent} exposes the four face decorations carried
 * by a decorated-pot item. The sherd layout participates in item persistence and
 * provides the decorations used when the item becomes a placed decorated pot.
 * An absent layout uses the default brick faces.
 *
 * @author IWareQ
 */
public interface ItemDecoratedPotBaseComponent extends ItemBaseComponent {
    /**
     * Returns the four face decorations carried by this pot item.
     */
    PotSherds getSherds();

    /**
     * Replaces the four face decorations carried by this pot item.
     * Supplying {@code null} selects {@code PotSherds.DEFAULT}, so subsequent reads
     * and serialization use the four default brick faces.
     */
    void setSherds(PotSherds sherds);
}
