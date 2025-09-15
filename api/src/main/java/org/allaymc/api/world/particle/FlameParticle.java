package org.allaymc.api.world.particle;

import java.awt.*;

/**
 * FlameParticle is a particle shown around torches. It can have any colour specified.
 * If the colour is not specified, it will default to the normal flame particle.
 *
 * @param color the color of this particle
 * @author daoge_cmd
 */
public record FlameParticle(Color color) implements Particle {
}
