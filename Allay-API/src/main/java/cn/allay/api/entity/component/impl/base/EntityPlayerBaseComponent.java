package cn.allay.api.entity.component.impl.base;

import cn.allay.api.client.Client;
import cn.allay.api.component.annotation.Inject;
import cn.allay.api.entity.component.impl.base.EntityBaseComponent;

public interface EntityPlayerBaseComponent extends EntityBaseComponent {
    @Inject
    Client getClient();
}
