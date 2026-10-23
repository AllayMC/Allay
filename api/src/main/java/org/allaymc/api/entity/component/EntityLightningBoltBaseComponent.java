package org.allaymc.api.entity.component;

/**
 * {@code EntityLightningBoltBaseComponent} exposes whether a lightning bolt
 * applies damaging and ignition effects. Disabling the flag leaves the bolt's
 * visual lifetime and sounds available while the server implementation skips
 * nearby entity damage and block ignition attempts.
 *
 * @author daoge_cmd
 */
public interface EntityLightningBoltBaseComponent extends EntityBaseComponent {
    /**
     * Returns whether the bolt can damage entities and ignite blocks.
     */
    boolean isEffect();

    /**
     * Sets whether this bolt applies damage and block ignition effects.
     */
    void setEffect(boolean effect);
}
