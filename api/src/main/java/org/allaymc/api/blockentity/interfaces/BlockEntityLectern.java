package org.allaymc.api.blockentity.interfaces;

import org.allaymc.api.blockentity.BlockEntity;
import org.allaymc.api.blockentity.component.BlockEntityLecternBaseComponent;

/**
 * {@code BlockEntityLectern} exposes lectern book and page state together with
 * the block-entity contract. The lectern component provides book replacement,
 * page selection, and dropping while the inherited base associates that state
 * with a persistent block entity at a dimension position.
 *
 * @author daoge_cmd
 */
public interface BlockEntityLectern extends BlockEntity, BlockEntityLecternBaseComponent {
}
