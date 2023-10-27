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
public class EffectLevitation extends AbstractEffect {
    @Override
    public int getId() {
        return 24;
    }

    @Override
    public Identifier getIdentifier() {
        return VanillaEffectIds.LEVITATION;
    }

    @Override
    public Color getColor() {
        return new Color(206, 255, 255);
    }

    @Override
    public boolean isBad() {
        return true;
    }
}
