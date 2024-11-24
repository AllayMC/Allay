package org.allaymc.server.block.impl.sand;

import java.util.List;
import org.allaymc.api.block.interfaces.sand.BlockSoulSandBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.block.impl.BlockBehaviorImpl;
import org.allaymc.server.component.interfaces.ComponentProvider;

public class BlockSoulSandBehaviorImpl extends BlockBehaviorImpl implements BlockSoulSandBehavior {
    public BlockSoulSandBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);;
    }
}
