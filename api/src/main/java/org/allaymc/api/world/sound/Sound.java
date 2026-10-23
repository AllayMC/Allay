package org.allaymc.api.world.sound;

/**
 * {@code Sound} marks sound descriptors accepted by dimension effect
 * broadcasting. The runtime packet encoder interprets each supported
 * descriptor and may omit effects unavailable in a client protocol version;
 * adding an implementation also requires appropriate encoder support to
 * deliver it.
 *
 * @author daoge_cmd
 */
public interface Sound {
}
