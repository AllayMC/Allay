package org.allaymc.server.block.impl.candle;

import java.util.List;
import org.allaymc.api.block.interfaces.candle.BlockGreenCandleBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.block.impl.BlockBehaviorImpl;
import org.allaymc.server.component.interfaces.ComponentProvider;

public class BlockGreenCandleBehaviorImpl extends BlockBehaviorImpl implements BlockGreenCandleBehavior {
    public BlockGreenCandleBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);;
    }
}
