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
public class EffectBadOmen extends AbstractEffect {
    @Override
    public int getId() {
        return 28;
    }

    @Override
    public Identifier getIdentifier() {
        return VanillaEffectIds.BAD_OMEN;
    }

    @Override
    public Color getColor() {
        return new Color(11, 97, 56);
    }

    @Override
    public boolean isBad() {
        return true;
    }
}
