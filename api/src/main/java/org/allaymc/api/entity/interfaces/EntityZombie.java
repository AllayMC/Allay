package org.allaymc.api.entity.interfaces;

import org.allaymc.api.entity.component.EntityBabyComponent;
import org.allaymc.api.entity.component.EntityContainerHolderComponent;
import org.allaymc.api.entity.component.EntityHeadYawComponent;
import org.allaymc.api.entity.component.EntityUndeadComponent;

/**
 * {@code EntityZombie} represents a zombie with intelligent living behavior,
 * undead rules, equipment containers, baby state, and independent head
 * rotation. The inherited components let callers manage movement and combat
 * alongside held equipment and the zombie's current baby form.
 */
public interface EntityZombie extends EntityIntelligent, EntityHeadYawComponent, EntityUndeadComponent, EntityContainerHolderComponent, EntityBabyComponent {
}
