package org.allaymc.api.entity.component;

/**
 * {@code EntityAgeComponent} exposes an entity's elapsed lifetime in ticks and
 * the age threshold used for expiry. The server advances ordinary ages on
 * entity ticks and removes an entity when its age reaches the configured
 * maximum; {@code -1} pauses aging.
 *
 * @author daoge_cmd
 */
public interface EntityAgeComponent extends EntityComponent {
    /**
     * Returns the entity's current age measured in entity ticks. An age of
     * {@code -1} pauses automatic aging, so callers must preserve that sentinel
     * when they intend the entity to remain exempt from age expiry.
     */
    int getAge();

    /**
     * Sets the age from which subsequent entity ticks continue aging. The value
     * {@code -1} pauses the age counter; other values can reach the maximum age
     * and cause removal on a later tick.
     */
    void setAge(int age);

    /**
     * Returns the maximum lifetime threshold measured in entity ticks. The
     * server compares the incremented age against this threshold when aging is
     * enabled; reaching or exceeding the threshold requests removal of the
     * entity.
     */
    int getMaxAge();
}
