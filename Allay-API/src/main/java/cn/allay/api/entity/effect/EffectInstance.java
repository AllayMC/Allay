package cn.allay.api.entity.effect;

/**
 * Allay Project 2023/10/27
 *
 * @author daoge_cmd
 */
public interface EffectInstance {

    EffectType getType();

    int getAmplifier();

    void setAmplifier(int amplifier);

    boolean isVisible();

    void setVisible(boolean visible);

    int getDuration();

    void setDuration(int duration);
}
