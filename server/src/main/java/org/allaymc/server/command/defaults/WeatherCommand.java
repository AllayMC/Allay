package org.allaymc.server.command.defaults;

import org.allaymc.api.command.Command;
import org.allaymc.api.command.tree.CommandTree;
import org.allaymc.api.message.TrKeys;
import org.allaymc.api.permission.Permissions;
import org.allaymc.api.world.data.Weather;

import java.util.Locale;

/**
 * {@code WeatherCommand} queries or requests weather for the sender's execution
 * world. Setting feedback reports the requested weather after calling the world
 * API, while query feedback reads the current weather; a cancelled weather
 * event therefore need not match the preceding setting message.
 *
 * @author daoge_cmd
 */
public class WeatherCommand extends Command {

    /**
     * Creates the {@code /weather} command with its built-in authorization permission.
     */
    public WeatherCommand() {
        super("weather", TrKeys.MC_COMMANDS_WEATHER_DESCRIPTION, Permissions.COMMAND_WEATHER);
    }

    /**
     * Installs parsed weather selection and a separate current-weather query.
     * Setting requests report success without checking event cancellation;
     * queries send the current enum name in lowercase directly to the sender.
     */
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
