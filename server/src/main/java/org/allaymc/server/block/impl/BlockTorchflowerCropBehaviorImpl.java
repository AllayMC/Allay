package org.allaymc.server.block.impl;

import org.allaymc.api.block.interfaces.BlockTorchflowerCropBehavior;
import org.allaymc.api.component.Component;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class BlockTorchflowerCropBehaviorImpl extends BlockBehaviorImpl implements BlockTorchflowerCropBehavior {
    public BlockTorchflowerCropBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
