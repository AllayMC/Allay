package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockPitcherCropBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockPitcherCropBehaviorImpl extends BlockBehaviorImpl implements BlockPitcherCropBehavior {
    public BlockPitcherCropBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
