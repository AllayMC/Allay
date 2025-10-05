package org.allaymc.server.command.defaults;

import org.allaymc.api.command.tree.CommandTree;
import org.allaymc.api.message.TrKeys;
import org.allaymc.api.world.data.Weather;

import java.util.Locale;

/**
 * @author daoge_cmd
 */
public class WeatherCommand extends VanillaCommand {

    public WeatherCommand() {
        super("weather", TrKeys.MC_COMMANDS_WEATHER_DESCRIPTION);
    }

    @Override
    public void prepareCommandTree(CommandTree tree) {
        tree.getRoot().enumClass("weather", Weather.class).exec(context -> {
            Weather weather = context.getResult(0);
            context.getSender().getCommandExecuteLocation().dimension().getWorld().setWeather(weather);
            context.addOutput(switch (weather) {
                case CLEAR -> TrKeys.MC_COMMANDS_WEATHER_CLEAR;
                case RAIN -> TrKeys.MC_COMMANDS_WEATHER_RAIN;
                case THUNDER -> TrKeys.MC_COMMANDS_WEATHER_THUNDER;
            });
            return context.success();
        }).root().key("query").exec(context -> {
            var weather = context.getSender().getCommandExecuteLocation().dimension().getWorld().getWeather();
            context.getSender().sendTranslatable(TrKeys.MC_COMMANDS_WEATHER_QUERY, weather.name().toLowerCase(Locale.ROOT));
            return context.success();
        });
    }
}
