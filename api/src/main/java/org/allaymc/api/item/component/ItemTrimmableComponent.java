package org.allaymc.api.item.component;

import org.allaymc.api.item.data.TrimMaterial;
import org.allaymc.api.item.data.TrimPattern;

/**
 * {@code ItemTrimmableComponent} exposes the armor trim pattern and material held
 * by an item. Each value can be absent, and the pair describes the decoration
 * serialized by the trim component when both parts are available.
 *
 * @author IWareQ | daoge_cmd
 */
public interface ItemTrimmableComponent extends ItemComponent {
    /**
     * Assigns the trim pattern and material supplied by the caller.
     * Either value may be {@code null}; the trim component saves a trim only when
     * both values are present.
     */
    void trim(TrimPattern pattern, TrimMaterial material);

    /**
     * Returns the pattern currently selected for this item's trim.
     * The result is {@code null} when no pattern is available, independently of
     * whether a material has already been assigned.
     */
    TrimPattern getPattern();

    /**
     * Returns the material currently selected for this item's trim.
     * The result is {@code null} when no material is available, independently of
     * whether a pattern has already been assigned.
     */
    TrimMaterial getMaterial();
}
