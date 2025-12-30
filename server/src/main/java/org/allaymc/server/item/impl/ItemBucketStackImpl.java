package org.allaymc.server.item.impl;

import lombok.experimental.Delegate;
import org.allaymc.api.component.Component;
import org.allaymc.api.item.ItemStackInitInfo;
import org.allaymc.api.item.component.ItemBucketComponent;
import org.allaymc.api.item.interfaces.ItemBucketStack;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class ItemBucketStackImpl extends ItemStackImpl implements ItemBucketStack {
    @Delegate
    private ItemBucketComponent bucketComponent;

    public ItemBucketStackImpl(ItemStackInitInfo initInfo, List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}