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
public class EffectWeakness extends AbstractEffect {
    @Override
    public int getId() {
        return 18;
    }

    @Override
    public Identifier getIdentifier() {
        return VanillaEffectIds.WEAKNESS;
    }

    @Override
    public Color getColor() {
        return new Color(72, 77, 72);
    }

    @Override
    public boolean isBad() {
        return true;
    }
}
