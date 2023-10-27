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
public class EffectNausea extends AbstractEffect {
    @Override
    public int getId() {
        return 9;
    }

    @Override
    public Identifier getIdentifier() {
        return VanillaEffectIds.NAUSEA;
    }

    @Override
    public Color getColor() {
        return new Color(85, 29, 74);
    }

    @Override
    public boolean isBad() {
        return true;
    }
}
