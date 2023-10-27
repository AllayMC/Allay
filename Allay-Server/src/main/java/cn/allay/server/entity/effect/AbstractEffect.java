package cn.allay.server.entity.effect;

import cn.allay.api.entity.effect.Effect;

/**
 * Allay Project 2023/10/27
 *
 * @author daoge_cmd
 */
public abstract class AbstractEffect implements Effect {

    protected int amplifier;
    protected boolean visible;
    protected int duration;

    @Override
    public int getAmplifier() {
        return amplifier;
    }

    @Override
    public boolean isVisible() {
        return visible;
    }

    @Override
    public int getDuration() {
        return duration;
    }

    @Override
    public void setAmplifier(int amplifier) {
        this.amplifier = amplifier;
    }

    @Override
    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    @Override
    public void setDuration(int duration) {
        this.duration = duration;
    }
}
