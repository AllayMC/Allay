package org.allaymc.server.entity.component;

import lombok.Getter;
import lombok.Setter;
import org.allaymc.api.entity.component.EntitySplashPotionProjectileComponent;
import org.allaymc.api.item.data.PotionType;

/**
 * @author daoge_cmd
 */
public class EntitySplashPotionProjectileComponentImpl extends EntityProjectileComponentImpl implements EntitySplashPotionProjectileComponent {

    @Getter
    @Setter
    protected PotionType potionType;
}
