package org.allaymc.api.entity.interfaces;

import org.allaymc.api.entity.Entity;
import org.allaymc.api.entity.component.EntityFireworksRocketBaseComponent;
import org.allaymc.api.entity.component.EntityFireworksRocketPhysicsComponent;

/**
 * {@code EntityFireworksRocket} represents a timed rocket with explosion
 * definitions and configurable free-flight motion. Its base component controls
 * lifetime and optional player attachment, while its physics contract supplies
 * acceleration for rockets that are not attached.
 */
public interface EntityFireworksRocket extends
        Entity,
        EntityFireworksRocketBaseComponent,
        EntityFireworksRocketPhysicsComponent {

}
