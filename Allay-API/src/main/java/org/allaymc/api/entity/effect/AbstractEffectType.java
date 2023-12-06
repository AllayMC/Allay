package org.allaymc.api.entity.effect;

import org.allaymc.api.identifier.Identifier;
import lombok.Getter;

import java.awt.*;

/**
 * Allay Project 2023/10/27
 *
 * @author daoge_cmd
 */
@Getter
public abstract class AbstractEffectType implements EffectType {

    protected final int id;
    protected final Identifier identifier;
    protected final Color color;
    protected final boolean isBad;

    public AbstractEffectType(int id, Identifier identifier, Color color) {
        this(id, identifier, color, false);
    }

    public AbstractEffectType(int id, Identifier identifier, Color color, boolean isBad) {
        this.id = id;
        this.identifier = identifier;
        this.color = color;
        this.isBad = isBad;
        EffectRegistry.getRegistry().register(id, identifier, this);
    }

    @Override
    public EffectInstance createInstance(int amplifier, int duration, boolean visible) {
        return new SimpleEffectInstance(this, amplifier, duration, visible);
    }
}
