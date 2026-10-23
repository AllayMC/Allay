package org.allaymc.api.world.particle;

/**
 * {@code CustomParticle} describes a named particle effect with optional
 * MoLang variables. The runtime sends the name and JSON text to the client
 * without parsing the variables here; installed client resources determine
 * whether that named effect is available.
 *
 * @param particleName built-in or resource-pack particle identifier
 *
 * @param moLangVariables {@code moLangVariables} supplies the optional JSON
 * variable map. {@code null} omits this payload from the outgoing effect.
 *
 * @author daoge_cmd
 */
public record CustomParticle(String particleName, String moLangVariables) implements Particle {
    /**
     * Creates a named particle with no MoLang variable payload. The runtime
     * receives {@code null} variables from this overload, leaving the
     * particle's resource-defined defaults to the client.
     */
    public CustomParticle(String particleName) {
        this(particleName, null);
    }
}
