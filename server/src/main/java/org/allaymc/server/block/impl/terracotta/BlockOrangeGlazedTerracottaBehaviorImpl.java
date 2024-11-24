package org.allaymc.server.block.impl.terracotta;

import java.util.List;
import org.allaymc.api.block.interfaces.terracotta.BlockOrangeGlazedTerracottaBehavior;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.server.block.impl.BlockBehaviorImpl;
import org.allaymc.server.component.interfaces.ComponentProvider;

public class BlockOrangeGlazedTerracottaBehaviorImpl extends BlockBehaviorImpl implements BlockOrangeGlazedTerracottaBehavior {
    public BlockOrangeGlazedTerracottaBehaviorImpl(
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(componentProviders);;
    }
}
