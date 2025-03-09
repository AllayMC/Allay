package org.allaymc.api.item.component;

import org.cloudburstmc.protocol.bedrock.data.TrimMaterial;
import org.cloudburstmc.protocol.bedrock.data.TrimPattern;

/**
 * @author IWareQ
 */
public interface ItemTrimmableComponent extends ItemComponent {
    /**
     * Applies a trim pattern and material to the item.
     *
     * @param pattern  the trim pattern to be applied
     * @param material the trim material to be used
     */
    void trim(TrimPattern pattern, TrimMaterial material);

    /**
     * Retrieves the current trim pattern applied to the item.
     *
     * @return the current {@link TrimPattern}, or {@code null} if no pattern is applied
     */
    TrimPattern getPattern();

    /**
     * Retrieves the current trim material applied to the item.
     *
     * @return the current {@link TrimMaterial}, or {@code null} if no material is applied
     */
    TrimMaterial getMaterial();
}
