---
comments: true
---

# Use Internationalization (I18n)

Internationalization (often abbreviated as "i18n" because there are 18 letters between "i" and "n") is the process
of designing software so it can be adapted to various languages without engineering changes. Allay provides a
powerful i18n system that allows plugins to support multiple languages easily.

## How I18n Works in Allay

Allay's i18n system uses **translation keys** to represent translatable text. A translation key follows the format:

```
namespace:path.to.key
```

For example:
- `minecraft:commands.generic.syntax` - A vanilla Minecraft translation key
- `allay:command.gc.completed` - An Allay server translation key
- `myplugin:greeting.welcome` - A custom plugin translation key

When you use a translation key, the i18n system looks up the corresponding text in the language file
based on the current language setting.

## Using the I18n API

### Basic Translation

To translate a text that contains a translation key, use the `I18n.get().tr()` method:

```java linenums="1"
import org.allaymc.api.message.I18n;

public class MyPlugin extends Plugin {
    public void someMethod() {
        // Translate using the default language
        String translated = I18n.get().tr("myplugin:greeting.welcome");

        // Translate with arguments (using %1, %2, etc. as placeholders)
        String withArgs = I18n.get().tr("myplugin:greeting.hello", playerName);
    }
}
```

### Translate with Specific Language

You can also specify a language code to translate to:

```java linenums="1"
import org.allaymc.api.message.I18n;
import org.allaymc.api.message.LangCode;

// Translate to Chinese (Simplified)
String chinese = I18n.get().tr(LangCode.zh_CN, "myplugin:greeting.welcome");

// Translate to Japanese
String japanese = I18n.get().tr(LangCode.ja_JP, "myplugin:greeting.welcome");
```

### Available Language Codes

Allay supports the following language codes:

| Code         | Language                 |
|--------------|--------------------------|
| en_US        | English (United States)  |
| en_GB        | English (United Kingdom) |
| zh_CN        | Chinese (Simplified)     |
| zh_TW        | Chinese (Traditional)    |
| ja_JP        | Japanese                 |
| ko_KR        | Korean                   |
| de_DE        | German                   |
| fr_FR        | French (France)          |
| fr_CA        | French (Canada)          |
| es_ES        | Spanish (Spain)          |
| es_MX        | Spanish (Mexico)         |
| it_IT        | Italian                  |
| pt_BR        | Portuguese (Brazil)      |
| pt_PT        | Portuguese (Portugal)    |
| ru_RU        | Russian                  |
| pl_PL        | Polish                   |
| nl_NL        | Dutch                    |
| tr_TR        | Turkish                  |
| ... and more |                          |

## Add Language Files to Your Plugin

To add i18n support to your plugin, you need to create language files in the correct location.

### File Structure

Place your language files in the `assets/lang/` directory inside your plugin JAR:

```
your-plugin.jar
├── plugin.json
├── assets/
│   └── lang/
│       ├── en_US.json
│       ├── zh_CN.json
│       ├── ja_JP.json
│       └── ... (other languages)
└── your/plugin/classes/
```

### Language File Format

Each language file is a JSON file with translation keys and their corresponding translations:

```json title="assets/lang/en_US.json"
{
  "myplugin:greeting.welcome": "Welcome to our server!",
  "myplugin:greeting.hello": "Hello, %1!",
  "myplugin:command.mycommand.description": "This is my custom command",
  "myplugin:command.mycommand.success": "Command executed successfully for %1",
  "myplugin:error.notfound": "Could not find %1 in %2"
}
```

```json title="assets/lang/zh_CN.json"
{
  "myplugin:greeting.welcome": "欢迎来到我们的服务器！",
  "myplugin:greeting.hello": "你好，%1！",
  "myplugin:command.mycommand.description": "这是我的自定义命令",
  "myplugin:command.mycommand.success": "成功为 %1 执行命令",
  "myplugin:error.notfound": "无法在 %2 中找到 %1"
}
```

### Parameter Placeholders

You can use placeholders in your translations:

| Placeholder           | Description                      |
|-----------------------|----------------------------------|
| `%1`, `%2`, `%3`, ... | Ordered parameters (recommended) |
| `%s`                  | Unordered string parameter       |
| `%d`                  | Unordered integer parameter      |

**Example:**

```json
{
  "myplugin:message.trade": "%1 traded %2 items with %3 for %4 emeralds"
}
```

```java
// Result: "Steve traded 5 items with Alex for 10 emeralds"
I18n.get().tr("myplugin:message.trade", "Steve", 5, "Alex", 10);
```

## Using I18n in Commands

You can use translation keys in command descriptions and output messages:

```java linenums="1"
import org.allaymc.api.command.Command;
import org.allaymc.api.command.tree.CommandTree;
import org.allaymc.api.message.TrKeys;

public class MyCommand extends Command {
    public MyCommand() {
        // Use a translation key for the description
        super("mycommand", "myplugin:command.mycommand.description", "myplugin.command.mycommand");
    }

    @Override
    public void prepareCommandTree(CommandTree tree) {
        tree.getRoot()
                .playerTarget("player")
                .exec(context -> {
                    var players = context.getResult(0);
                    for (var player : players) {
                        // Use translation key in output (will be translated server-side)
                        context.addOutput("myplugin:command.mycommand.success", player.getDisplayName());
                    }
                    return context.success();
                });
    }
}
```

## Send Translatable Messages to Players

When sending messages to players, you can use `sendTranslatable()` to send translatable messages:

```java linenums="1"
// Send a translatable message to the player
// The message will be translated based on the player's language setting
player.sendTranslatable("myplugin:greeting.welcome");

// With arguments
player.sendTranslatable("myplugin:greeting.hello", player.getDisplayName());
```

## Fallback Language

If a translation key is not found in the specified language, the system will fall back to `en_US` (English).
This means you should always provide at least an `en_US.json` file with all your translation keys.

## Best Practices

1. **Use a consistent namespace**: Always prefix your translation keys with your plugin name
   (e.g., `myplugin:category.key`)

2. **Organize keys hierarchically**: Use dots to organize keys into logical groups
   (e.g., `myplugin:command.mycommand.description`)

3. **Always provide English translations**: Include `en_US.json` as the fallback language

4. **Use ordered parameters**: Prefer `%1`, `%2` over `%s`, `%d` for better readability and
   flexibility across languages (word order may differ)

5. **Keep translations concise**: Especially for in-game messages that appear in limited space

## Example: Complete Plugin with I18n

Here's a complete example of a plugin with i18n support:

**plugin.json:**
```json
{
  "name": "MyPlugin",
  "entrance": "com.example.myplugin.MyPlugin",
  "version": "1.0.0"
}
```

**assets/lang/en_US.json:**
```json
{
  "myplugin:greeting.welcome": "Welcome to the server, %1!",
  "myplugin:greeting.goodbye": "Goodbye, %1! See you next time!",
  "myplugin:command.greet.description": "Greet a player"
}
```

**assets/lang/zh_CN.json:**
```json
{
  "myplugin:greeting.welcome": "欢迎来到服务器，%1！",
  "myplugin:greeting.goodbye": "再见，%1！下次再见！",
  "myplugin:command.greet.description": "向玩家问好"
}
```

**MyPlugin.java:**
```java linenums="1"
import org.allaymc.api.eventbus.EventHandler;
import org.allaymc.api.eventbus.event.player.PlayerJoinEvent;
import org.allaymc.api.eventbus.event.player.PlayerQuitEvent;
import org.allaymc.api.message.I18n;
import org.allaymc.api.plugin.Plugin;
import org.allaymc.api.registry.Registries;

public class MyPlugin extends Plugin {
    @Override
    public void onEnable() {
        Registries.COMMANDS.register(new GreetCommand());
        getPluginManager().registerListener(this, new MyListener());
    }
}

class MyListener {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        var player = event.getPlayer();
        // Translate server-side and send to player
        String message = I18n.get().tr("myplugin:greeting.welcome", player.getDisplayName());
        player.sendText(message);
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        var player = event.getPlayer();
        String message = I18n.get().tr("myplugin:greeting.goodbye", player.getDisplayName());
        // Log to console
        System.out.println(message);
    }
}
```
