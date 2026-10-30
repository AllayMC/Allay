package org.allaymc.server.entity.effect;

import lombok.Getter;
import org.allaymc.api.entity.effect.EffectInstance;
import org.allaymc.api.entity.effect.EffectType;
import org.allaymc.api.registry.Registries;
import org.allaymc.api.utils.identifier.Identifier;

import java.awt.*;

/**
 * {@code AbstractEffectType} represents effect metadata shared by built-in
 * implementations and creates mutable instances referencing that type. Its
 * constructors register the new type under numeric and namespaced keys;
 * subclasses add callbacks where needed, while inherited callbacks perform no
 * work.
 *
 * @author daoge_cmd
 */
@Getter
public abstract class AbstractEffectType implements EffectType {

    /**
     * Numeric effect registry ID.
     */
    protected final int id;
    /**
     * Namespaced effect registry identifier.
     */
    protected final Identifier identifier;
    /**
     * Effect particle color value.
     */
    protected final Color color;
    /**
     * Harmful effect classification flag.
     */
    protected final boolean isBad;

    /**
     * Creates and registers effect metadata with nonharmful classification. The
     * effect registry must be initialized and mutable; registration associates
     * this instance with both supplied keys and may replace earlier
     * associations.
     */
    public AbstractEffectType(int id, Identifier identifier, Color color) {
        this(id, identifier, color, false);
    }

    /**
     * Creates and registers metadata with the supplied harmful classification.
     * The effect registry must be initialized and mutable; registration
     * associates this instance with both keys and throws {@code
     * IllegalStateException} when frozen.
     */
    public AbstractEffectType(int id, Identifier identifier, Color color, boolean isBad) {
        this.id = id;
        this.identifier = identifier;
        this.color = color;
        this.isBad = isBad;
        Registries.EFFECTS.register(id, identifier, this);
    }

    /**
     * Creates a mutable instance referring to this type with all supplied
     * settings unchanged. This implementation performs no range validation and
     * does not attach the new instance to an entity.
     */
    @Override
    public EffectInstance createInstance(int amplifier, int duration, boolean ambient, boolean visible) {
        return new EffectInstance(this, amplifier, duration, ambient, visible);
    }

    /**
     * Returns diagnostic effect type text containing the configured namespaced
     * identifier.
     */
    @Override 
    public String toString() {
        return "EffectType(" + identifier.toString() + ")";
    }
}
