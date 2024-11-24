package org.allaymc.server.block.impl.candle;

import java.util.List;
import org.allaymc.api.block.interfaces.candle.BlockPurpleCandleBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.block.impl.BlockBehaviorImpl;
import org.allaymc.server.component.interfaces.ComponentProvider;

public class BlockPurpleCandleBehaviorImpl extends BlockBehaviorImpl implements BlockPurpleCandleBehavior {
    public BlockPurpleCandleBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);;
    }
}
