package org.allaymc.server.blockentity.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.blockentity.BlockEntityInitInfo;
import org.allaymc.api.blockentity.component.BlockEntityDecoratedPotBaseComponent;
import org.allaymc.api.blockentity.interfaces.BlockEntityDecoratedPot;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

/**
 * @author IWareQ
 */
public class BlockEntityDecoratedPotImpl extends BlockEntityImpl implements BlockEntityDecoratedPot {
    @Delegate
    private BlockEntityDecoratedPotBaseComponent decoratedPotBaseComponent;

    public BlockEntityDecoratedPotImpl(BlockEntityInitInfo initInfo, List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
