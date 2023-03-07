package cn.allay.entity.component.interfaces;

import cn.allay.inject.annotation.Inject;
import cn.allay.utils.Identifier;

public interface NamespaceId {
    @Inject
    Identifier getNamespaceId();
}
