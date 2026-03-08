package org.allaymc.server.blockentity.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.blockentity.BlockEntityInitInfo;
import org.allaymc.api.blockentity.component.BlockEntityLecternBaseComponent;
import org.allaymc.api.blockentity.interfaces.BlockEntityLectern;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

/**
 * Lectern block entity implementation.
 *
 * @author daoge_cmd
 */
public class BlockEntityLecternImpl extends BlockEntityImpl implements BlockEntityLectern {

    @Delegate
    private BlockEntityLecternBaseComponent lecternBaseComponent;

    public BlockEntityLecternImpl(BlockEntityInitInfo initInfo, List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
