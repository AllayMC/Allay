package cn.allay.server.entity.effect.type;

import cn.allay.api.data.VanillaEffectIds;
import cn.allay.api.identifier.Identifier;
import cn.allay.server.entity.effect.AbstractEffectType;

import java.awt.*;

/**
 * Allay Project 2023/10/27
 *
 * @author daoge_cmd
 */
public class EffectFatalPoisonType extends AbstractEffectType {
    @Override
    public int getId() {
        return 25;
    }

    @Override
    public Identifier getIdentifier() {
        return VanillaEffectIds.FATAL_POISON;
    }

    @Override
    public Color getColor() {
        return new Color(78, 147, 49);
    }

    @Override
    public boolean isBad() {
        return true;
    }
}
