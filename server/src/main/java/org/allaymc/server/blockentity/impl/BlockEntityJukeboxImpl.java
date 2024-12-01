package org.allaymc.server.blockentity.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.blockentity.component.BlockEntityJukeboxBaseComponent;
import org.allaymc.api.blockentity.initinfo.BlockEntityInitInfo;
import org.allaymc.api.blockentity.interfaces.BlockEntityJukebox;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

/**
 * @author IWareQ
 */
public class BlockEntityJukeboxImpl extends BlockEntityImpl implements BlockEntityJukebox {
    public BlockEntityJukeboxImpl(BlockEntityInitInfo initInfo, List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }

    @Delegate
    @Override
    public BlockEntityJukeboxBaseComponent getBaseComponent() {
        return (BlockEntityJukeboxBaseComponent) super.getBaseComponent();
    }
}
