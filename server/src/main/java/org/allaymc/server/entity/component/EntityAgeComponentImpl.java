package org.allaymc.server.entity.component;

import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.component.EntityAgeComponent;
import org.allaymc.api.eventbus.EventHandler;
import org.allaymc.api.utils.identifier.Identifier;
import org.allaymc.server.component.annotation.ComponentObject;
import org.allaymc.server.entity.component.event.CEntityLoadNBTEvent;
import org.allaymc.server.entity.component.event.CEntitySaveNBTEvent;
import org.allaymc.server.entity.component.event.CEntityTickEvent;

/**
 * {@code EntityAgeComponentImpl} tracks a tick-based lifetime and removes its
 * entity when the incremented age reaches a configured threshold. The value
 * {@code -1} pauses aging. The NBT loader accepts a short-valued {@code "Age"}
 * tag, while the saver currently writes the integer counter.
 *
 * @author daoge_cmd
 */
public class EntityAgeComponentImpl implements EntityAgeComponent {

    /**
     * {@code IDENTIFIER} supplies the lookup key that {@code ComponentProvider} uses
     * to identify this lifetime component when entity types assemble their components.
     */
    @Identifier.Component
    public static final Identifier IDENTIFIER = new Identifier("minecraft:entity_age_component");
    /**
     * {@code DEFAULT_MAX_AGE} supplies the default expiry threshold in entity
     * ticks when an entity type constructs this component without a custom
     * maximum.
     */
    public static final int DEFAULT_MAX_AGE = 6000;

    /**
     * {@code TAG_AGE} identifies the NBT lifetime entry used by the load and
     * save handlers for the current entity age counter.
     */
    protected static final String TAG_AGE = "Age";
    /**
     * Lifetime expiry threshold in entity ticks.
     */
    @Getter
    protected final int maxAge;
    /**
     * Entity removed when its lifetime expires.
     */
    @ComponentObject
    protected Entity thisEntity;
    /**
     * {@code age} counts entity ticks and starts at zero. The generated setter
     * accepts values directly; {@code -1} disables incrementing and age-based
     * removal.
     */
    @Getter
    @Setter
    protected int age;

    /**
     * Creates a lifetime counter using the default maximum age. The counter
     * starts at zero and expires after {@link #DEFAULT_MAX_AGE} entity ticks
     * unless a caller or loaded short tag changes its age first.
     */
    public EntityAgeComponentImpl() {
        this(DEFAULT_MAX_AGE);
    }

    /**
     * Creates a lifetime counter with the supplied expiry threshold in ticks.
     * The maximum is retained without validation; ordinary ages are incremented
     * before comparison, while an age of {@code -1} bypasses both increment and
     * expiry.
     */
    public EntityAgeComponentImpl(int maxAge) {
        this.maxAge = maxAge;
    }

    /**
     * Advances a non-paused age and requests removal at its expiry threshold.
     * An age of {@code -1} remains unchanged; every other age is incremented
     * before comparison with {@code maxAge}, including values already beyond
     * that threshold.
     */
    @EventHandler
    protected void onTick(CEntityTickEvent event) {
        if (age != -1) {
            age++;
            if (age >= maxAge) {
                thisEntity.remove();
            }
        }
    }

    /**
     * Restores age when the input contains a short-valued lifetime tag. A
     * missing tag or another NBT value type leaves the current counter
     * unchanged, including the integer-valued tag written by this component's
     * save handler.
     */
    @EventHandler
    protected void onLoadNBT(CEntityLoadNBTEvent event) {
        event.getNbt().listenForShort(TAG_AGE, age -> this.age = age);
    }

    /**
     * Writes the current integer age into the entity's NBT builder. The value
     * preserves the {@code -1} pause sentinel, although the load handler
     * currently accepts only a short-valued lifetime tag and ignores this
     * integer representation.
     */
    @EventHandler
    protected void onSaveNBT(CEntitySaveNBTEvent event) {
        event.getNbt().put(TAG_AGE, this.age);
    }
}
