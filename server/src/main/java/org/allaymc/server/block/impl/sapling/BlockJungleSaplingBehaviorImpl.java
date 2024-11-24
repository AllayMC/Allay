package org.allaymc.server.block.impl.sapling;

import java.util.List;
import org.allaymc.api.block.interfaces.sapling.BlockJungleSaplingBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.block.impl.BlockBehaviorImpl;
import org.allaymc.server.component.interfaces.ComponentProvider;

public class BlockJungleSaplingBehaviorImpl extends BlockBehaviorImpl implements BlockJungleSaplingBehavior {
    public BlockJungleSaplingBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);;
    }
}
