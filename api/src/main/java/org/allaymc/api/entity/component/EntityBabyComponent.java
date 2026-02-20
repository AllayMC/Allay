package org.allaymc.api.entity.component;

/**
 * Component for entities that can have a baby state.
 *
 * @author daoge_cmd
 */
public interface EntityBabyComponent extends EntityComponent {

    /**
     * Check if this entity is a baby.
     *
     * @return {@code true} if this entity is a baby
     */
    boolean isBaby();

    /**
     * Set whether this entity is a baby.
     *
     * @param baby {@code true} to make this entity a baby
     */
    void setBaby(boolean baby);
}
