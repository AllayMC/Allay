package cn.allay.api.entity.effect;

import cn.allay.api.entity.Entity;
import cn.allay.api.identifier.Identifier;

import java.awt.*;

/**
 * Allay Project 2023/10/27
 *
 * @author daoge_cmd
 */
public interface Effect {
    int getId();

    Identifier getIdentifier();

    Color getColor();

    int getAmplifier();

    void setAmplifier(int amplifier);

    boolean isVisible();

    void setVisible(boolean visible);

    int getDuration();

    void setDuration(int duration);

    default boolean isBad() {
        return false;
    }

    default void onApply(Entity entity) {}

    default void onRemove(Entity entity) {}

    default void onTick(Entity entity) {}
}
