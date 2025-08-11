package org.allaymc.api.entity.component;

/**
 * @author daoge_cmd
 */
public interface EntityAgeComponent extends EntityComponent {
    /**
     * Get the age of this entity item.
     *
     * @return the age.
     */
    int getAge();

    /**
     * Set the age of this entity item.
     *
     * @param age the age.
     */
    void setAge(int age);
}
