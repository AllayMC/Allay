package org.allaymc.server.entity.effect;

import org.allaymc.api.entity.component.EntityUndeadComponent;
import org.allaymc.api.entity.damage.DamageContainer;
import org.allaymc.api.entity.effect.EffectInstance;
import org.allaymc.api.entity.interfaces.EntityLiving;
import org.allaymc.api.utils.identifier.Identifier;

import java.awt.*;

/**
 * {@code EffectInstantDamageType} represents instant damage metadata and
 * applies its health change on addition. Undead-component entities receive the
 * opposite operation from other living entities, and the supplied effect level
 * scales the amount exponentially; no repeated tick action is implemented here.
 *
 * @author daoge_cmd
 */
public class EffectInstantDamageType extends AbstractEffectType {
    /**
     * Creates and registers the built-in harmful instant damage effect
     * metadata. Registration requires an initialized mutable effect registry
     * and can replace existing associations under this type's fixed numeric or
     * namespaced key.
     */
    public EffectInstantDamageType() {
        super(7, new Identifier("minecraft:instant_damage"), new Color(0xa9656a), true);
    }

    /**
     * Applies healing to undead-component entities and magic damage to other
     * living entities. Healing requests {@code 2 * Math.pow(2, level)} points,
     * damage requests {@code 3 * Math.pow(2, level)} points, and the living
     * component invokes this only for a previously absent type.
     */
    @Override
    public void onAdd(EntityLiving entity, EffectInstance effectInstance) {
        // One-based levels make level one supply four healing or six damage points.
        var level = effectInstance.getLevel();

        if (entity instanceof EntityUndeadComponent) {
            entity.setHealth(entity.getHealth() + (float) (2 * Math.pow(2, level)));
            return;
        }

        entity.attack(DamageContainer.magicEffect((float) (3 * Math.pow(2, level))));
    }
}
