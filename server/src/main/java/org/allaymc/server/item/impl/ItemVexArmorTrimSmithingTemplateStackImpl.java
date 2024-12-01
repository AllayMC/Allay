package org.allaymc.server.item.impl;

import java.util.List;
import org.allaymc.api.component.interfaces.Component;
import org.allaymc.api.item.initinfo.ItemStackInitInfo;
import org.allaymc.api.item.interfaces.ItemVexArmorTrimSmithingTemplateStack;
import org.allaymc.server.component.interfaces.ComponentProvider;

public class ItemVexArmorTrimSmithingTemplateStackImpl extends ItemStackImpl implements ItemVexArmorTrimSmithingTemplateStack {
    public ItemVexArmorTrimSmithingTemplateStackImpl(ItemStackInitInfo initInfo,
            List<ComponentProvider<? extends Component>> componentProviders) {
        super(initInfo, componentProviders);
    }
}
