package org.allaymc.server.blockentity.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.blockentity.BlockEntityInitInfo;
import org.allaymc.api.blockentity.component.BlockEntityBellBaseComponent;
import org.allaymc.api.blockentity.interfaces.BlockEntityBell;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

/**
 * Bell block entity implementation.
 *
 * @author daoge_cmd
 */
public class BlockEntityBellImpl extends BlockEntityImpl implements BlockEntityBell {

    @Delegate
    private BlockEntityBellBaseComponent bellBaseComponent;

    public BlockEntityBellImpl(BlockEntityInitInfo initInfo, List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
