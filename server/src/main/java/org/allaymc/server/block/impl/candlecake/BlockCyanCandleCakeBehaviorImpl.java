package org.allaymc.server.block.impl.candlecake;

import java.util.List;
import org.allaymc.api.block.interfaces.candlecake.BlockCyanCandleCakeBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.block.impl.BlockBehaviorImpl;
import org.allaymc.server.component.interfaces.ComponentProvider;

public class BlockCyanCandleCakeBehaviorImpl extends BlockBehaviorImpl implements BlockCyanCandleCakeBehavior {
    public BlockCyanCandleCakeBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);;
    }
}
