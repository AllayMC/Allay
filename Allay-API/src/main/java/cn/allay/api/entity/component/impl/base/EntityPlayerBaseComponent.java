package cn.allay.api.entity.component.impl.base;

import cn.allay.api.client.Client;
import cn.allay.api.component.annotation.Inject;
import org.jetbrains.annotations.Range;

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

    @Inject
    int getHandSlot();

    @Inject
    void setHandSlot(@Range(from = 0, to = 8) int handSlot);

    @Override
    default float getBaseOffset() {
        return 1.62f;
    }

    @Override
    default boolean enableHeadYaw() {
        return true;
    }

    @Override
    default boolean computeMovementServerSide() {
        return getClient().computeMovementServerSide();
    }
}
