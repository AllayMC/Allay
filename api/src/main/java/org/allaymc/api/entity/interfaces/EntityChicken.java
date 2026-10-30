package org.allaymc.api.entity.interfaces;

import org.allaymc.api.entity.component.EntityHeadYawComponent;

/**
 * {@code EntityChicken} represents a chicken with animal behavior and
 * independent head rotation. {@link EntityAnimal} supplies breeding-item
 * recognition, baby state, AI, and physical movement, while
 * {@link EntityHeadYawComponent} lets callers adjust head yaw independently of
 * the chicken's body yaw.
 */
public interface EntityChicken extends EntityAnimal, EntityHeadYawComponent {
}
