package org.allaymc.api.world.particle;

import java.awt.*;

/**
 * {@code DustParticle} describes a falling-dust effect with an explicit color.
 * The runtime reads the supplied RGB value directly during encoding; callers
 * must supply a color because this descriptor provides no default tint for
 * {@code null}.
 *
 * @param color required particle RGB tint
 *
 * @author daoge_cmd
 */
public record DustParticle(Color color) implements Particle {
}
