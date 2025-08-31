package org.allaymc.api.entity.component;

/**
 * @author daoge_cmd
 */
public interface EntityAgeComponent extends EntityComponent {
    /**
     * Get the age of this entity.
     *
     * @return the age
     */
    int getAge();

    /**
     * Set the age of this entity.
     *
     * @param age the age
     */
    void setAge(int age);

    /**
     * Get the max age of this entity.
     *
     * @return the max age
     */
    int getMaxAge();
}
