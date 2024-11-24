package org.allaymc.server.block.impl.candlecake;

import java.util.List;
import org.allaymc.api.block.interfaces.candlecake.BlockWhiteCandleCakeBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.block.impl.BlockBehaviorImpl;
import org.allaymc.server.component.interfaces.ComponentProvider;

public class BlockWhiteCandleCakeBehaviorImpl extends BlockBehaviorImpl implements BlockWhiteCandleCakeBehavior {
    public BlockWhiteCandleCakeBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);;
    }
}
