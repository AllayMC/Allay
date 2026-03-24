package org.allaymc.server.blockentity.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.blockentity.BlockEntityInitInfo;
import org.allaymc.api.blockentity.component.BlockEntityChiseledBookshelfContainerHolderComponent;
import org.allaymc.api.blockentity.interfaces.BlockEntityChiseledBookshelf;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

/**
 * @author daoge_cmd
 */
public class BlockEntityChiseledBookshelfImpl extends BlockEntityImpl implements BlockEntityChiseledBookshelf {
    @Delegate
    private BlockEntityChiseledBookshelfContainerHolderComponent containerHolderComponent;

    public BlockEntityChiseledBookshelfImpl(BlockEntityInitInfo initInfo, List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
