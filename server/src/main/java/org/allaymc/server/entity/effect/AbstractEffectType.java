package org.allaymc.server.entity.effect;

import lombok.Getter;
import org.allaymc.api.entity.effect.EffectInstance;
import org.allaymc.api.entity.effect.EffectType;
import org.allaymc.api.registry.Registries;
import org.allaymc.api.utils.identifier.Identifier;

import java.awt.*;

/**
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
        Registries.EFFECTS.register(id, identifier, this);
    }

    @Override
    public EffectInstance createInstance(int amplifier, int duration, boolean ambient, boolean visible) {
        return new EffectInstance(this, amplifier, duration, ambient, visible);
    }
}
