package org.allaymc.api.item.component;

import org.allaymc.api.block.type.BlockType;

/**
 * {@code ItemSignBaseComponent} identifies the standing and wall block variants
 * for a sign item. Placement code selects a variant according to the clicked face,
 * while these accessors keep the material-specific block mapping attached to the
 * item's component.
 *
 * @author daoge_cmd
 */
public interface ItemSignBaseComponent extends ItemBaseComponent {
    /**
     * Returns the wall-mounted sign block variant for this item.
     */
    BlockType<?> getWallSignType();

    /**
     * Returns the standing sign block variant for this item.
     */
    BlockType<?> getStandingSignType();
}
