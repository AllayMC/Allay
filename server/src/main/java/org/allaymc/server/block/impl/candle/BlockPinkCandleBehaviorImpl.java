package org.allaymc.server.block.impl.candle;

import java.util.List;
import org.allaymc.api.block.interfaces.candle.BlockPinkCandleBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.block.impl.BlockBehaviorImpl;
import org.allaymc.server.component.interfaces.ComponentProvider;

public class BlockPinkCandleBehaviorImpl extends BlockBehaviorImpl implements BlockPinkCandleBehavior {
    public BlockPinkCandleBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);;
    }
}
