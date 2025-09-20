package org.allaymc.api.world.particle;

import java.awt.*;

/**
 * DustParticle is a particle shown for redstone. It can have any colour specified.
 * If the colour is not specified, it will default to black.
 *
 * @param color the colour of the dust particle.
 * @author daoge_cmd
 */
public record DustParticle(Color color) implements Particle {
}
