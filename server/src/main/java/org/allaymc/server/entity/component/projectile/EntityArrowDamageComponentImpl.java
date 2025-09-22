package org.allaymc.server.entity.component.projectile;

import org.allaymc.api.entity.damage.DamageContainer;
import org.allaymc.api.entity.damage.DamageType;
import org.allaymc.server.entity.component.EntityDamageComponentImpl;

/**
 * @author daoge_cmd
 */
public class EntityArrowDamageComponentImpl extends EntityDamageComponentImpl {
    @Override
    public boolean canBeAttacked(DamageContainer damage) {
        return damage.getDamageType() == DamageType.API;
    }
}
