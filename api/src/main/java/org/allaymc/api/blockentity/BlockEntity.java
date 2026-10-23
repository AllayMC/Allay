package org.allaymc.api.blockentity;

import org.allaymc.api.blockentity.component.BlockEntityBaseComponent;

/**
 * {@code BlockEntity} represents persistent block-associated state through the
 * common base component contract. Specialized block-entity interfaces add
 * capabilities such as inventories, text, or processing state. Constructing an
 * instance supplies its state and dimension but does not itself attach it to a
 * chunk.
 *
 * @author daoge_cmd
 */
public interface BlockEntity extends BlockEntityBaseComponent {
}
