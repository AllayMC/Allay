package org.allaymc.api.entity.effect;

import lombok.Getter;
import org.allaymc.api.registry.Registries;
import org.allaymc.api.utils.Identifier;

/**
 * @author daoge_cmd
 * @see <a href="https://minecraft.wiki/w/Effect#Effect_list">Effect list</a>
 */
@Getter
public abstract class AbstractEffectType implements EffectType {

    protected final int id;
    protected final Identifier identifier;
    protected final boolean isBad;

    public AbstractEffectType(int id, Identifier identifier) {
        this(id, identifier, false);
    }

    public AbstractEffectType(int id, Identifier identifier, boolean isBad) {
        this.id = id;
        this.identifier = identifier;
        this.isBad = isBad;
        Registries.EFFECTS.register(id, identifier, this);
    }

    @Override
    public EffectInstance createInstance(int amplifier, int duration, boolean ambient, boolean visible) {
        return new EffectInstance(this, amplifier, duration, ambient, visible);
    }

    @Override 
    public boolean equals(Object other) {
        if (other instanceof AbstractEffectType effect) {
            return id == effect.id;
        } else {
            return false;
        }
    }
    
    @Override 
    public int hashCode() {
        return id;
    }
}
