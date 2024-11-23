package org.allaymc.server.item.impl.bucket;

import lombok.experimental.Delegate;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.api.item.component.ItemBucketComponent;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.bucket.*;
import org.allaymc.server.component.interfaces.ComponentProvider;
import org.allaymc.server.item.impl.ItemStackImpl;

import java.util.List;

public class ItemBucketStackImpl extends ItemStackImpl implements
        ItemBucketStack, ItemAxolotlBucketStack, ItemCodBucketStack, ItemLavaBucketStack, ItemPowderSnowBucketStack,
        ItemPufferfishBucketStack, ItemSalmonBucketStack, ItemTadpoleBucketStack, ItemTropicalFishBucketStack, ItemWaterBucketStack {
    @Delegate
    protected ItemBucketComponent bucketComponent;

    public ItemBucketStackImpl(ItemStackInitInfo initInfo,
                               List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
