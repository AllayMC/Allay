package cn.allay.api.entity.interfaces.player;

import cn.allay.api.client.Client;
import cn.allay.api.entity.component.base.EntityBaseComponent;
import org.jetbrains.annotations.Range;

public interface EntityPlayerBaseComponent extends EntityBaseComponent {
    Client getClient();

    void setSprinting(boolean sprinting);

    boolean isSprinting();

    void setSneaking(boolean sneaking);

    boolean isSneaking();

    void setSwimming(boolean swimming);

    boolean isSwimming();

    void setGliding(boolean gliding);

    boolean isGliding();

    void setCrawling(boolean crawling);

    boolean isCrawling();

    int getHandSlot();

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
