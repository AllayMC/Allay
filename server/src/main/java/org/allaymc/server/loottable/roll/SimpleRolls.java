package org.allaymc.server.loottable.roll;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.allaymc.api.loottable.Rolls;

/**
 * @author daoge_cmd
 */
@Getter
@AllArgsConstructor
public class SimpleRolls implements Rolls {
    protected int rolls;
}