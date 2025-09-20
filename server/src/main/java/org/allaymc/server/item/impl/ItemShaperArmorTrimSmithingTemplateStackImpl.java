package org.allaymc.server.item.impl;

import org.allaymc.api.component.Component;
import org.allaymc.api.item.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemShaperArmorTrimSmithingTemplateStack;
import org.allaymc.server.component.ComponentProvider;

import java.util.List;

public class ItemShaperArmorTrimSmithingTemplateStackImpl extends ItemStackImpl implements ItemShaperArmorTrimSmithingTemplateStack {
    public ItemShaperArmorTrimSmithingTemplateStackImpl(ItemStackInitInfo initInfo,
                                                        List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
