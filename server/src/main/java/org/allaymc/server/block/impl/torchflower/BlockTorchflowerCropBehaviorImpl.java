package org.allaymc.server.block.impl.torchflower;

import org.allaymc.api.block.interfaces.torchflower.BlockTorchflowerCropBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.block.impl.BlockBehaviorImpl;
import org.allaymc.server.component.interfaces.ComponentProvider;

import java.util.List;

public class BlockTorchflowerCropBehaviorImpl extends BlockBehaviorImpl implements BlockTorchflowerCropBehavior {
    public BlockTorchflowerCropBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);
    }
}
