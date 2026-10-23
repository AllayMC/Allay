package org.allaymc.api.world.particle;

/**
 * {@code Particle} marks particle descriptors accepted by dimension effect
 * broadcasting. Implementations carry the parameters needed by the runtime
 * packet encoder; defining another implementation also requires encoder
 * support before clients can receive that new effect through the standard
 * path.
 *
 * @author daoge_cmd
 */
public interface Particle {
}
