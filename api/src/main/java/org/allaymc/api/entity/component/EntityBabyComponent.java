package org.allaymc.api.entity.component;

/**
 * {@code EntityBabyComponent} exposes whether an entity uses its baby form. The
 * server stores this flag independently of elapsed entity age, and explicit
 * updates change the entity's scale and broadcast its new state to viewers.
 *
 * @author daoge_cmd
 */
public interface EntityBabyComponent extends EntityComponent {

    /**
     * Returns whether the entity currently uses its baby form.
     */
    boolean isBaby();

    /**
     * Sets whether the entity uses its baby form. The server sets scale to
     * {@code 0.5} for babies or {@code 1.0} for adults and broadcasts the
     * resulting entity state to viewers.
     */
    void setBaby(boolean baby);
}
