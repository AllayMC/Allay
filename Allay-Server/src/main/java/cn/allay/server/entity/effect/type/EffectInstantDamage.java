package cn.allay.server.entity.effect.type;

import cn.allay.api.data.VanillaEffectIds;
import cn.allay.api.identifier.Identifier;
import cn.allay.server.entity.effect.AbstractEffect;

import java.awt.*;

/**
 * Allay Project 2023/10/27
 *
 * @author daoge_cmd
 */
public class EffectInstantDamage extends AbstractEffect {
    @Override
    public int getId() {
        return 7;
    }

    @Override
    public Identifier getIdentifier() {
        return VanillaEffectIds.INSTANT_DAMAGE;
    }

    @Override
    public Color getColor() {
        return new Color(67, 10, 9);
    }

    @Override
    public boolean isBad() {
        return true;
    }
}
