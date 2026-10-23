package org.allaymc.api.entity.interfaces;

import org.allaymc.api.entity.component.EntityEnderCrystalBaseComponent;

/**
 * {@code EntityEnderCrystal} represents an end crystal with living state and
 * configurable base visibility. {@link EntityLiving} supplies health and damage
 * operations, while {@link EntityEnderCrystalBaseComponent} lets callers show
 * or hide the base of that same crystal entity.
 */
public interface EntityEnderCrystal extends EntityLiving, EntityEnderCrystalBaseComponent {

}
