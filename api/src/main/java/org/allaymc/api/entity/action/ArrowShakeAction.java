package org.allaymc.api.entity.action;

/**
 * ArrowShakeAction makes an arrow entity display a shaking animation for the given times.
 *
 * @param times the times the arrow entity needs to shake
 * @author daoge_cmd
 */
public record ArrowShakeAction(int times) implements EntityAction {
}
