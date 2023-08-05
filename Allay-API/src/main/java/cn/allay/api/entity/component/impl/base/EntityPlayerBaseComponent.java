package cn.allay.api.entity.component.impl.base;

import cn.allay.api.client.Client;
import cn.allay.api.component.annotation.Impl;
import cn.allay.api.component.annotation.Inject;
import cn.allay.api.entity.component.impl.base.EntityBaseComponent;

public interface EntityPlayerBaseComponent extends EntityBaseComponent {
    @Inject
    Client getClient();

    @Inject
    void setSprinting(boolean sprinting);

    @Inject
    boolean isSprinting();

    @Inject
    void setSneaking(boolean sneaking);

    @Inject
    boolean isSneaking();

    @Inject
    void setSwimming(boolean swimming);

    @Inject
    boolean isSwimming();

    @Inject
    void setGliding(boolean gliding);

    @Inject
    boolean isGliding();

    @Inject
    void setCrawling(boolean crawling);

    @Inject
    boolean isCrawling();

    @Override
    default double getBaseOffset() {
        return 1.62;
    }

    @Override
    default boolean enableHeadYaw() {
        return true;
    }
}
