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
     * @param pattern  the {@link TrimPattern} to apply
     * @param material the {@link TrimMaterial} to use
     */
    void trim(TrimPattern pattern, TrimMaterial material);

    /**
     * Gets the current trim pattern.
     *
     * @return the {@link TrimPattern}, or {@code null} if none
     */
    TrimPattern getPattern();

    /**
     * Gets the current trim material.
     *
     * @return the {@link TrimMaterial}, or {@code null} if none
     */
    TrimMaterial getMaterial();
}
