package org.allaymc.server.gui;

import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import org.allaymc.api.eventbus.EventHandler;
import org.allaymc.api.eventbus.event.server.PlayerJoinEvent;
import org.allaymc.api.eventbus.event.server.PlayerQuitEvent;
import org.allaymc.api.message.I18n;
import org.allaymc.api.message.TrKeys;
import org.allaymc.api.plugin.PluginDependency;
import org.allaymc.api.registry.Registries;
import org.allaymc.api.server.Server;
import org.allaymc.api.utils.AllayStringUtils;
import org.allaymc.server.command.AllayCommandSuggester;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Layout;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.appender.AbstractAppender;
import org.apache.logging.log4j.core.config.Property;
import org.apache.logging.log4j.core.layout.PatternLayout;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.Document;
import java.awt.*;
import java.awt.event.*;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.Serializable;
import java.net.URL;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

/**
 * {@code Dashboard} presents the server console, player and plugin tables, command input, and
 * memory or dimension graphs in Swing. Construction creates the window, replaces both system output
 * streams with a shared proxy, and installs a Log4j appender; startup then activates periodic
 * sampling and command input.
 *
 * <p>
 * UI tasks run on Swing, while submitted commands return to the server scheduler. Dimension history
 * maps use integer identifiers, but sampling prunes their keys against the world's
 * {@code DimensionType} keys. This mismatch clears those histories before zero-filled
 * reinitialization on each sample, so the graphs currently retain only the newest nonzero
 * observation. Memory history remains bounded across samples. Command completion suppresses
 * listener refresh during its own text replacement, and callers serialize singleton initialization
 * and invoke startup once.
 *
 * @author daoge_cmd
 */
public final class Dashboard {
    /**
     * Number of retained memory history samples.
     */
    // RAM_VALUE_COUNT, CHUNK_VALUE_COUNT, and ENTITY_VALUE_COUNT bound their
    // graph histories. MEGABYTE converts memory labels; LOG_APPENDER_NAME,
    // CONSOLE_APPENDER_NAME, and LOG_PATTERN govern console log integration,
    // while DIMENSION_COLORS and DIMENSION_NAMES label known graph series.
    public static final int RAM_VALUE_COUNT = 100;
    /**
     * Number of retained chunk history samples.
     */
    public static final int CHUNK_VALUE_COUNT = 100;
    /**
     * Number of retained entity history samples.
     */
    public static final int ENTITY_VALUE_COUNT = 100;
    /**
     * Bytes per displayed mebibyte.
     */
    private static final long MEGABYTE = 1024L * 1024L;
    /**
     * Dashboard Log4j appender registration name.
     */
    private static final String LOG_APPENDER_NAME = "Dashboard";
    /**
     * Existing terminal appender lookup name.
     */
    private static final String CONSOLE_APPENDER_NAME = "TerminalConsole";
    /**
     * Fallback colored console log layout.
     */
    private static final String LOG_PATTERN = "[%cyan{%d{HH:mm:ss} %level}] [%yellow{%t}] [%blue{%logger{0}}] %minecraftFormatting{%msg}%n";
    /**
     * Known dimension identifier color palette.
     */
    private static final Map<Integer, Color> DIMENSION_COLORS = Map.of(
            0, new Color(76, 175, 80, 200),
            1, new Color(244, 67, 54, 200),
            2, new Color(156, 39, 176, 200)
    );
    /**
     * Known dimension identifier display labels.
     */
    private static final Map<Integer, String> DIMENSION_NAMES = Map.of(
            0, "Overworld",
            1, "Nether",
            2, "The End"
    );
    /**
     * Lazily constructed shared dashboard reference.
     */
    private static Dashboard INSTANCE;
    /**
     * Top-level dashboard content panel.
     */
    private JPanel rootPane;
    /**
     * Main dashboard tab container.
     */
    private JTabbedPane rootTabbedPane;
    /**
     * Player table tab panel.
     */
    private JPanel playerTab;
    /**
     * Performance history tab panel.
     */
    private JPanel perfTab;
    /**
     * Current online player table.
     */
    private JTable playerTable;
    /**
     * Current enabled plugin table.
     */
    private JTable pluginTable;
    /**
     * Localized online player count label.
     */
    private JLabel onlinePlayerCount;
    /**
     * Heap usage percentage graph.
     */
    private GraphPanel ramGraph;
    /**
     * Selected-world chunk history graph.
     */
    private GraphPanel chunkGraph;
    /**
     * Selected-world entity history graph.
     */
    private GraphPanel entityGraph;
    /**
     * Styled server console text pane.
     */
    private ConsolePanel consolePane;
    /**
     * Server command input field.
     */
    private JTextField cmdInput;
    /**
     * Entity history tab panel.
     */
    private JPanel entityTab;
    /**
     * Chunk history tab panel.
     */
    private JPanel chunkTab;
    /**
     * Memory history tab panel.
     */
    private JPanel memoryTab;
    /**
     * Console output tab panel.
     */
    private JPanel consoleTab;
    /**
     * Plugin table tab panel.
     */
    private JPanel pluginTab;
    /**
     * Performance graph tab container.
     */
    private JTabbedPane perfTabbedPane;
    /**
     * Retained heap usage percentage samples.
     */
    private List<Integer> ramValues;
    /**
     * Command suggestion popup controller.
     */
    private CommandAutocomplete commandAutocomplete;
    /**
     * Retained per-world dimension chunk histories.
     */
    // chunkCountByWorld and entityCountByWorld retain aligned sampling histories
    // keyed by world display name and dimension ID. selectedWorld chooses both
    // graph views. Dimension-key pruning currently clears each numeric history
    // because the loaded dimension map exposes DimensionType keys instead of integers.
    private final Map<String, Map<Integer, List<Integer>>> chunkCountByWorld = new LinkedHashMap<>();
    /**
     * Retained per-world dimension entity histories.
     */
    private final Map<String, Map<Integer, List<Integer>>> entityCountByWorld = new LinkedHashMap<>();
    /**
     * World display name selected for graphing.
     */
    private String selectedWorld;

    /**
     * Creates the dashboard window, console output hooks, and context-menu listeners. Construction
     * changes process-wide output and logging state before showing the window; closing the window
     * requests server shutdown rather than disposing it immediately.
     *
     * <p>
     * Player and plugin actions resolve current server objects from the selected table row when
     * invoked. World-selection actions switch both dimension graphs using the retained display-name
     * key. Callers construct the singleton in an environment with Swing support and coordinate
     * access to server state; failures do not undo output redirection or already registered
     * listeners.
     */
    private Dashboard() {
        $$$setupUI$$$();
        wrapSystemOutputStreams();
        attachLogAppender();
        JFrame frame = new JFrame(I18n.get().tr(TrKeys.ALLAY_GUI_NAME));
        frame.setContentPane(rootPane);
        frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                Server.getInstance().shutdown();
            }
        });
        frame.setSize(700, 700);
        frame.setLocationRelativeTo(null);
        URL image = Dashboard.class.getClassLoader().getResource("icon.png");
        if (image != null) {
            frame.setIconImage(new ImageIcon(image).getImage());
        }
        applyI18nTexts();
        frame.setVisible(true);
        pluginTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() != MouseEvent.BUTTON3) return;
                JPopupMenu popupMenu = new JPopupMenu();
                JMenuItem infoItem = new JMenuItem(I18n.get().tr(TrKeys.ALLAY_GUI_INFO));
                infoItem.addActionListener($ -> {
                    if (pluginTable.getSelectedRow() == -1) return;
                    String pluginName = (String) pluginTable.getValueAt(pluginTable.getSelectedRow(), 0);
                    var pluginDescriptor = Server.getInstance().getPluginManager().getEnabledPlugin(pluginName).descriptor();
                    JOptionPane.showMessageDialog(null,
                            I18n.get().tr(TrKeys.ALLAY_GUI_PLUGIN_NAME) + ": " + pluginDescriptor.getName() + "\n" +
                            I18n.get().tr(TrKeys.ALLAY_GUI_PLUGIN_ENTRANCE) + ": " + pluginDescriptor.getEntrance() + "\n" +
                            I18n.get().tr(TrKeys.ALLAY_GUI_PLUGIN_DESCRIPTION) + ": " + pluginDescriptor.getDescription() + "\n" +
                            I18n.get().tr(TrKeys.ALLAY_GUI_PLUGIN_VERSION) + ": " + pluginDescriptor.getVersion() + "\n" +
                            I18n.get().tr(TrKeys.ALLAY_GUI_PLUGIN_API_VERSION) + ": " + pluginDescriptor.getAPIVersion() + "\n" +
                            I18n.get().tr(TrKeys.ALLAY_GUI_PLUGIN_AUTHORS) + ": " + String.join(", ", pluginDescriptor.getAuthors()) + "\n" +
                            I18n.get().tr(TrKeys.ALLAY_GUI_PLUGIN_DEPENDENCIES) + ": " + pluginDescriptor.getDependencies().stream().map(PluginDependency::name).collect(Collectors.joining(", ")) + "\n" +
                            I18n.get().tr(TrKeys.ALLAY_GUI_PLUGIN_WEBSITE) + ": " + pluginDescriptor.getWebsite(),
                            I18n.get().tr(TrKeys.ALLAY_GUI_PLUGIN_INFO),
                            JOptionPane.INFORMATION_MESSAGE);
                });
                popupMenu.add(infoItem);
                popupMenu.show(e.getComponent(), e.getX(), e.getY());
            }
        });
        playerTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() != MouseEvent.BUTTON3) return;
                JPopupMenu popupMenu = new JPopupMenu();

                JMenuItem infoItem = new JMenuItem(I18n.get().tr(TrKeys.ALLAY_GUI_INFO));
                infoItem.addActionListener($ -> {
                    if (playerTable.getSelectedRow() == -1) return;
                    String playerName = (String) playerTable.getValueAt(playerTable.getSelectedRow(), 0);
                    var player = Server.getInstance().getPlayerManager().getPlayerByName(playerName);
                    var pos = player.getControlledEntity().getLocation();
                    JOptionPane.showMessageDialog(null,
                            I18n.get().tr(TrKeys.ALLAY_GUI_PLAYER_NAME) + ": " + player.getOriginName() + "\n" +
                            I18n.get().tr(TrKeys.ALLAY_GUI_PLAYER_ADDRESS) + ": " + player.getSocketAddress().toString() + "\n" +
                            I18n.get().tr(TrKeys.ALLAY_GUI_PLAYER_UUID) + ": " + player.getLoginData().getUuid().toString() + "\n" +
                            I18n.get().tr(TrKeys.ALLAY_GUI_PLAYER_POS) + ": (" + pos.x() + ", " + pos.y() + ", " + pos.z() + ")" + "\n" +
                            I18n.get().tr(TrKeys.ALLAY_GUI_PLAYER_WORLD) + ": " + pos.dimension().getWorld().getWorldData().getDisplayName() + "\n" +
                            I18n.get().tr(TrKeys.ALLAY_GUI_PLAYER_DIMENSION) + ": " + pos.dimension().getDimensionType().getIdentifier(),
                            I18n.get().tr(TrKeys.ALLAY_GUI_PLAYER_INFO),
                            JOptionPane.INFORMATION_MESSAGE);
                });
                popupMenu.add(infoItem);

                JMenuItem kickItem = new JMenuItem(I18n.get().tr(TrKeys.ALLAY_GUI_PLAYER_KICK));
                kickItem.addActionListener($ -> {
                    if (playerTable.getSelectedRow() == -1) return;
                    String playerName = (String) playerTable.getValueAt(playerTable.getSelectedRow(), 0);
                    var player = Server.getInstance().getPlayerManager().getPlayerByName(playerName);
                    player.disconnect(TrKeys.MC_DISCONNECT_DISCONNECTED);
                });
                popupMenu.add(kickItem);

                JMenuItem banItem = new JMenuItem(I18n.get().tr(TrKeys.ALLAY_GUI_PLAYER_BAN));
                banItem.addActionListener($ -> {
                    if (playerTable.getSelectedRow() == -1) return;
                    String playerName = (String) playerTable.getValueAt(playerTable.getSelectedRow(), 0);
                    var player = Server.getInstance().getPlayerManager().getPlayerByName(playerName);
                    Server.getInstance().getPlayerManager().ban(player.getLoginData().getUuid().toString());
                });
                popupMenu.add(banItem);

                JMenuItem banIpItem = new JMenuItem(I18n.get().tr(TrKeys.ALLAY_GUI_PLAYER_BANIP));
                banIpItem.addActionListener($ -> {
                    if (playerTable.getSelectedRow() == -1) return;
                    String playerName = (String) playerTable.getValueAt(playerTable.getSelectedRow(), 0);
                    var player = Server.getInstance().getPlayerManager().getPlayerByName(playerName);
                    Server.getInstance().getPlayerManager().banIP(AllayStringUtils.fastTwoPartSplit(player.getSocketAddress().toString().substring(1), ":", "")[0]);
                });
                popupMenu.add(banIpItem);

                var manager = Server.getInstance().getPlayerManager();
                int selectedRow = playerTable.getSelectedRow();
                if (selectedRow != -1) {
                    String selectedPlayerName = (String) playerTable.getValueAt(selectedRow, 0);
                    var selectedPlayer = manager.getPlayerByName(selectedPlayerName);
                    if (selectedPlayer != null) {
                        boolean isOp = manager.isOperator(selectedPlayer);
                        JMenuItem opItem = new JMenuItem(I18n.get().tr(isOp ? TrKeys.ALLAY_GUI_PLAYER_DEOP : TrKeys.ALLAY_GUI_PLAYER_OP));
                        opItem.addActionListener($ -> {
                            manager.setOperator(selectedPlayer.getOriginName(), !isOp);
                            selectedPlayer.sendTranslatable(isOp ? TrKeys.MC_COMMANDS_DEOP_MESSAGE : TrKeys.MC_COMMANDS_OP_MESSAGE);
                        });
                        popupMenu.add(opItem);
                    }
                }

                popupMenu.show(e.getComponent(), e.getX(), e.getY());
            }
        });
        consolePane.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() != MouseEvent.BUTTON3) return;
                JPopupMenu popupMenu = new JPopupMenu();

                JMenuItem changeFontSizeItem = new JMenuItem(I18n.get().tr(TrKeys.ALLAY_GUI_CONSOLE_CHANGEFONTSIZE));
                changeFontSizeItem.addActionListener($ -> {
                    String input = JOptionPane.showInputDialog(I18n.get().tr(TrKeys.ALLAY_GUI_CONSOLE_CHANGEFONTSIZE_DIALOG), consolePane.getFont().getSize());
                    if (input == null) return;
                    try {
                        int fontSize = Integer.parseInt(input);
                        consolePane.setFont(consolePane.getFont().deriveFont((float) fontSize));
                    } catch (NumberFormatException ex) {
                        // Invalid font text leaves the existing font unchanged;
                        // report the input problem through the dashboard error dialog.
                        JOptionPane.showMessageDialog(null, I18n.get().tr(TrKeys.ALLAY_GUI_CONSOLE_CHANGEFONTSIZE_ERROR_DIALOG), I18n.get().tr(TrKeys.ALLAY_GUI_ERROR), JOptionPane.ERROR_MESSAGE);
                    }
                });
                popupMenu.add(changeFontSizeItem);

                JMenuItem clearItem = new JMenuItem(I18n.get().tr(TrKeys.ALLAY_GUI_CONSOLE_CLEAR));
                clearItem.addActionListener($ -> consolePane.setText(""));
                popupMenu.add(clearItem);

                popupMenu.show(e.getComponent(), e.getX(), e.getY());
            }
        });
        var worldSelectListener = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() != MouseEvent.BUTTON3) return;
                var worldNames = new ArrayList<>(chunkCountByWorld.keySet());
                if (worldNames.isEmpty()) return;
                JPopupMenu popupMenu = new JPopupMenu();
                for (var worldName : worldNames) {
                    var item = new JCheckBoxMenuItem(worldName, worldName.equals(selectedWorld));
                    item.addActionListener($ -> {
                        selectedWorld = worldName;
                        refreshDimensionGraphs();
                    });
                    popupMenu.add(item);
                }
                popupMenu.show(e.getComponent(), e.getX(), e.getY());
            }
        };
        chunkGraph.addMouseListener(worldSelectListener);
        entityGraph.addMouseListener(worldSelectListener);
    }

    /**
     * Returns the shared dashboard, installing the dark look and feel and constructing the window
     * on first access. Initialization is unsynchronized, so callers serialize first access to avoid
     * constructing multiple windows and output hooks.
     */
    public static Dashboard getInstance() {
        if (INSTANCE == null) {
            FlatMacDarkLaf.setup();
            INSTANCE = new Dashboard();
        }
        return INSTANCE;
    }

    /**
     * Queues localized tab titles and the initial online-count label on the Swing event thread.
     * Translation uses the configured default language when the queued task executes rather than
     * capturing translated strings at scheduling time.
     */
    private void applyI18nTexts() {
        // Tab labels belong to Swing state; defer their translation and mutation
        // to the event thread after the generated components have been constructed.
        SwingUtilities.invokeLater(() -> {
            rootTabbedPane.setTitleAt(0, I18n.get().tr(TrKeys.ALLAY_GUI_CONSOLE));
            rootTabbedPane.setTitleAt(1, I18n.get().tr(TrKeys.ALLAY_GUI_PERFORMANCE));
            rootTabbedPane.setTitleAt(2, I18n.get().tr(TrKeys.ALLAY_GUI_PLAYER));
            rootTabbedPane.setTitleAt(3, I18n.get().tr(TrKeys.ALLAY_GUI_PLUGIN));

            perfTabbedPane.setTitleAt(0, (I18n.get().tr(TrKeys.ALLAY_GUI_MEMORY)));
            perfTabbedPane.setTitleAt(1, (I18n.get().tr(TrKeys.ALLAY_GUI_CHUNK)));
            perfTabbedPane.setTitleAt(2, (I18n.get().tr(TrKeys.ALLAY_GUI_ENTITY)));

            onlinePlayerCount.setText(I18n.get().tr(TrKeys.ALLAY_GUI_PLAYER_ONLINE, 0));
        });
    }

    /**
     * Replaces standard output and standard error with flushing print streams backed by one
     * dashboard proxy. Both streams forward to the original standard output reference, and this
     * method does not retain a restoration handle.
     */
    private void wrapSystemOutputStreams() {
        var proxyOutputStream = createProxyOutputStream();
        System.setOut(new PrintStream(proxyOutputStream, true));
        System.setErr(new PrintStream(proxyOutputStream, true));
    }

    /**
     * Installs and starts the dashboard log appender unless its registration name already exists.
     * The terminal appender's layout is reused when available; otherwise a new pattern layout
     * supplies colored console output.
     */
    private void attachLogAppender() {
        var ctx = (LoggerContext) LogManager.getContext(false);
        var log4jConfig = ctx.getConfiguration();
        if (log4jConfig.getAppender(LOG_APPENDER_NAME) != null) return;

        Layout<? extends Serializable> layout = null;
        var consoleAppender = log4jConfig.getAppender(CONSOLE_APPENDER_NAME);
        if (consoleAppender != null) {
            layout = consoleAppender.getLayout();
        }
        // Logger configurations can omit the terminal appender or its layout;
        // construct the dashboard pattern so log events still have console formatting.
        if (layout == null) {
            layout = PatternLayout.newBuilder()
                    .withConfiguration(log4jConfig)
                    .withPattern(LOG_PATTERN)
                    .build();
        }

        var appender = new DashboardLogAppender(LOG_APPENDER_NAME, layout, this);
        appender.start();
        log4jConfig.addAppender(appender);
        log4jConfig.getLoggerConfig(LogManager.ROOT_LOGGER_NAME).addAppender(appender, null, null);
        ctx.updateLoggers();
    }

    /**
     * Creates an output proxy forwarding bytes to the current standard output and text to the
     * dashboard queue. The captured output reference prevents recursion after stream replacement.
     * Byte slices are decoded independently with the default charset, so a multibyte character
     * split across writes is not reconstructed by a streaming decoder. The proxy does not close or
     * flush the captured stream through overridden lifecycle methods.
     */
    private @NotNull OutputStream createProxyOutputStream() {
        var originalOutputStream = System.out;
        return new OutputStream() {
            @Override
            public void write(int i) {
                originalOutputStream.write(i);
                appendTextToConsole(String.valueOf((char) i));
            }

            @Override
            public void write(byte @NotNull [] b) {
                write(b, 0, b.length);
            }

            @Override
            public void write(byte @NotNull [] b, int off, int len) {
                originalOutputStream.write(b, off, len);
                appendTextToConsole(new String(b, off, len));
            }
        };
    }

    /**
     * Activates periodic graph sampling, lifecycle table listeners, command submission, and
     * completion after server startup. Callers invoke this operation once because repeated calls
     * install additional tasks and listeners.
     *
     * <p>
     * The server scheduler queues sampling onto Swing every twenty server ticks. Memory history is
     * trimmed normally, but dimension histories are cleared by comparing integer keys with the
     * world's {@code DimensionType} keys, then recreated with zero-filled windows before appending
     * the newest sample. Missing selected worlds cause another selection. Command input is enabled
     * later on Swing after installing completion.
     */
    public void afterServerStarted() {
        Runnable guiUpdateTask = () -> {
            final long freeMemory = Runtime.getRuntime().freeMemory();
            final long totalMemory = Runtime.getRuntime().totalMemory();
            // Round free memory to the nearest whole heap percentage.
            final int freePercent = (int) (freeMemory * 100.0 / totalMemory + 0.5);
            ramValues.add(100 - freePercent);
            ramGraph.setXLabel(I18n.get().tr(TrKeys.ALLAY_GUI_RAM_LABEL, String.format("%,d", (totalMemory - freeMemory) / MEGABYTE), freePercent));
            // Keep the newest memory samples within the retained history window.
            int k = ramValues.size();
            if (k > RAM_VALUE_COUNT)
                ramValues.subList(0, k - RAM_VALUE_COUNT).clear();
            ramGraph.setValues(ramValues);

            var loadedWorldNames = new HashSet<String>();
            Server.getInstance().getWorldPool().getWorlds().values().forEach(world -> {
                var worldName = world.getWorldData().getDisplayName();
                loadedWorldNames.add(worldName);
                var chunkDimMap = chunkCountByWorld.computeIfAbsent(worldName, n -> new LinkedHashMap<>());
                var entityDimMap = entityCountByWorld.computeIfAbsent(worldName, n -> new LinkedHashMap<>());
                var loadedDimIds = world.getDimensions().keySet();
                // loadedDimIds contains DimensionType objects, but these histories use
                // Integer keys. Both retainAll calls therefore clear existing dimension
                // histories before the sampling loop recreates zero-filled windows below.
                chunkDimMap.keySet().retainAll(loadedDimIds);
                entityDimMap.keySet().retainAll(loadedDimIds);
                world.getDimensions().values().forEach(dimension -> {
                    int dimId = dimension.getDimensionType().getId();
                    appendAndTrim(
                            chunkDimMap.computeIfAbsent(dimId, id -> new ArrayList<>(Collections.nCopies(CHUNK_VALUE_COUNT, 0))),
                            dimension.getChunkManager().getLoadedChunks().size(), CHUNK_VALUE_COUNT);
                    appendAndTrim(
                            entityDimMap.computeIfAbsent(dimId, id -> new ArrayList<>(Collections.nCopies(ENTITY_VALUE_COUNT, 0))),
                            dimension.getEntityCount(), ENTITY_VALUE_COUNT);
                });
            });

            // Drop histories for unloaded display-name keys from both maps before
            // selecting a replacement world and rebuilding its dimension graph series.
            chunkCountByWorld.keySet().retainAll(loadedWorldNames);
            entityCountByWorld.keySet().retainAll(loadedWorldNames);

            // A removed selection cannot resolve graph histories. Choose the first
            // remaining display-name key, or leave selection absent when all worlds unload.
            if (selectedWorld == null || !chunkCountByWorld.containsKey(selectedWorld)) {
                selectedWorld = chunkCountByWorld.keySet().stream().findFirst().orElse(null);
            }

            refreshDimensionGraphs();
        };

        // The server scheduler does not own Swing components. Queue sampling and
        // graph mutation on the event thread so painting shares that execution context.
        Server.getInstance().getScheduler().scheduleRepeating(
                Server.getInstance(),
                () -> {
                    SwingUtilities.invokeLater(guiUpdateTask);
                    return true;
                },
                // Server ticks determine sampling frequency, including under server lag.
                20);

        var server = Server.getInstance();
        server.getEventBus().registerListener(this);
        updatePluginTable();

        cmdInput.addActionListener(new CommandListener());
        commandAutocomplete = new CommandAutocomplete(this.cmdInput, new AllayCommandSuggester(server));
        // Install completion before enabling input, then request focus on Swing
        // so user edits reach an initialized controller through normal UI dispatch.
        SwingUtilities.invokeLater(() -> {
            cmdInput.setEnabled(true);
            cmdInput.requestFocusInWindow();
        });
    }

    /**
     * Queues online-count and player-table refreshes after a player joins the server.
     */
    @EventHandler
    private void onPlayerJoin(PlayerJoinEvent event) {
        updateOnlinePlayerCount();
        updateOnlinePlayerTable();
    }

    /**
     * Queues online-count and player-table refreshes after a player leaves the server.
     */
    @EventHandler
    private void onPlayerQuit(PlayerQuitEvent event) {
        updateOnlinePlayerCount();
        updateOnlinePlayerTable();
    }

    /**
     * Queues the localized player-count label update on the Swing event thread. The count is read
     * when that task executes, allowing a later lifecycle change to be reflected in the displayed
     * value.
     */
    private void updateOnlinePlayerCount() {
        // Read the current count in the deferred UI task so queued lifecycle
        // events can display newer membership instead of a captured obsolete count.
        SwingUtilities.invokeLater(() -> onlinePlayerCount.setText(I18n.get().tr(TrKeys.ALLAY_GUI_PLAYER_ONLINE, Server.getInstance().getPlayerManager().getPlayerCount())));
    }

    /**
     * Builds a table model from current player details and queues its installation on the Swing
     * event thread. Data collection occurs before queuing, so the displayed snapshot can lag later
     * membership changes.
     */
    private void updateOnlinePlayerTable() {
        var title = new String[]{I18n.get().tr(TrKeys.ALLAY_GUI_PLAYER_NAME), I18n.get().tr(TrKeys.ALLAY_GUI_PLAYER_ADDRESS), I18n.get().tr(TrKeys.ALLAY_GUI_PLAYER_UUID)};
        var players = Server.getInstance().getPlayerManager().getPlayers().values();
        String[][] data = new String[players.size()][3];
        int row = 0;
        for (var player : players) {
            data[row] = new String[]{
                    player.getOriginName(),
                    player.getSocketAddress().toString(),
                    player.getLoginData().getUuid().toString()
            };
            row++;
        }
        var model = new UneditableDefaultTableModel(data, title);
        // Build the player snapshot before scheduling so Swing only installs its
        // prepared table model instead of traversing live player data during installation.
        SwingUtilities.invokeLater(() -> {
            playerTable.setModel(model);
            playerTable.getTableHeader().setReorderingAllowed(false);
        });
    }

    /**
     * Appends a sample and removes the oldest prefix beyond the supplied history size. The list is
     * mutated in place, preserving the newest samples and their order for later graph snapshots.
     */
    private static void appendAndTrim(List<Integer> list, int value, int maxSize) {
        list.add(value);
        int sz = list.size();
        // Preserve the newest history window when appending exceeds its bound.
        if (sz > maxSize) list.subList(0, sz - maxSize).clear();
    }

    /**
     * Refreshes chunk and entity graph series for the selected world display name. Missing
     * selection or either history map leaves existing graphs unchanged.
     *
     * <p>
     * Each graph label sums the newest retained sample across that world's dimension histories,
     * while the plotted series copy the full histories in dimension order. The operation uses both
     * maps from the same selected key so chunk and entity views follow one world selection; callers
     * run this mutation on the Swing event thread.
     */
    private void refreshDimensionGraphs() {
        // Missing selection or either history map leaves the previous plot visible;
        // sampling will refresh both views when a resolvable world key becomes available.
        if (selectedWorld == null) return;
        var chunkDimMap = chunkCountByWorld.get(selectedWorld);
        var entityDimMap = entityCountByWorld.get(selectedWorld);
        if (chunkDimMap == null || entityDimMap == null) return;

        var chunkSeries = buildDimSeriesList(chunkDimMap);
        var totalChunks = chunkDimMap.values().stream()
                .mapToInt(list -> list.isEmpty() ? 0 : list.getLast())
                .sum();
        chunkGraph.setXLabel(I18n.get().tr(TrKeys.ALLAY_GUI_CHUNK_LABEL, totalChunks));
        chunkGraph.setSeries(chunkSeries);

        var entitySeries = buildDimSeriesList(entityDimMap);
        var totalEntities = entityDimMap.values().stream()
                .mapToInt(list -> list.isEmpty() ? 0 : list.getLast())
                .sum();
        entityGraph.setXLabel(I18n.get().tr(TrKeys.ALLAY_GUI_ENTITY_LABEL, totalEntities));
        entityGraph.setSeries(entitySeries);
    }

    /**
     * Copies dimension histories into graph series ordered by numeric dimension identifier. Known
     * identifiers use the configured label and color; other identifiers use a numeric label and
     * white line without changing sample order.
     */
    private static List<GraphPanel.Series> buildDimSeriesList(Map<Integer, List<Integer>> dimMap) {
        var series = new ArrayList<GraphPanel.Series>();
        // Custom dimension IDs can lack palette entries; numeric labels and white
        // lines preserve their histories instead of dropping unrecognized dimensions.
        dimMap.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .forEach(entry -> series.add(new GraphPanel.Series(
                        DIMENSION_NAMES.getOrDefault(entry.getKey(), "Dim " + entry.getKey()),
                        DIMENSION_COLORS.getOrDefault(entry.getKey(), Color.WHITE),
                        new ArrayList<>(entry.getValue())
                )));
        return series;
    }

    /**
     * Builds an enabled-plugin table snapshot and queues installation on the Swing event thread.
     * Blank descriptions use {@code "N/A"}, and plugin changes after collection do not alter the
     * already built model.
     */
    private void updatePluginTable() {
        var title = new String[]{I18n.get().tr(TrKeys.ALLAY_GUI_PLUGIN_NAME), I18n.get().tr(TrKeys.ALLAY_GUI_PLUGIN_DESCRIPTION), I18n.get().tr(TrKeys.ALLAY_GUI_PLUGIN_VERSION), I18n.get().tr(TrKeys.ALLAY_GUI_PLUGIN_AUTHORS)};
        var plugins = Server.getInstance().getPluginManager().getEnabledPlugins().values();
        var data = new String[plugins.size()][4];
        int row = 0;
        for (var plugin : plugins) {
            var descriptor = plugin.descriptor();
            data[row] = new String[]{
                    descriptor.getName(),
                    // Blank descriptions have no visible table content; use the
                    // unavailable label while retaining the plugin's other descriptor values.
                    descriptor.getDescription().isBlank() ? "N/A" : descriptor.getDescription(),
                    descriptor.getVersion(),
                    String.join(", ", descriptor.getAuthors())
            };
            row++;
        }
        var model = new UneditableDefaultTableModel(data, title);
        // The descriptor snapshot is ready before UI dispatch; install it on
        // Swing so table rendering never observes a partially assigned model.
        SwingUtilities.invokeLater(() -> {
            pluginTable.setModel(model);
            pluginTable.getTableHeader().setReorderingAllowed(false);
        });
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     */
    private void $$$setupUI$$$() {
        createUIComponents();
        rootPane = new JPanel();
        rootPane.setLayout(new GridLayoutManager(1, 1, new Insets(8, 8, 8, 8), -1, -1));
        rootTabbedPane = new JTabbedPane();
        rootPane.add(rootTabbedPane, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, new Dimension(200, 200), null, 0, false));
        consoleTab = new JPanel();
        consoleTab.setLayout(new GridLayoutManager(2, 1, new Insets(0, 0, 0, 0), -1, -1));
        rootTabbedPane.addTab("Console", consoleTab);
        final JScrollPane scrollPane1 = new JScrollPane();
        consoleTab.add(scrollPane1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        scrollPane1.setViewportView(consolePane);
        cmdInput = new JTextField();
        cmdInput.setEnabled(false);
        consoleTab.add(cmdInput, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        perfTab = new JPanel();
        perfTab.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        rootTabbedPane.addTab("Performance", perfTab);
        perfTabbedPane = new JTabbedPane();
        perfTabbedPane.setAlignmentY(0.5f);
        perfTab.add(perfTabbedPane, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, new Dimension(200, 200), null, 0, false));
        memoryTab = new JPanel();
        memoryTab.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        perfTabbedPane.addTab("Memory", memoryTab);
        memoryTab.add(ramGraph, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        chunkTab = new JPanel();
        chunkTab.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        perfTabbedPane.addTab("Chunk", chunkTab);
        chunkTab.add(chunkGraph, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        entityTab = new JPanel();
        entityTab.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        perfTabbedPane.addTab("Entity", entityTab);
        entityTab.add(entityGraph, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        playerTab = new JPanel();
        playerTab.setLayout(new GridLayoutManager(2, 1, new Insets(0, 0, 0, 0), -1, -1));
        rootTabbedPane.addTab("Players", playerTab);
        onlinePlayerCount = new JLabel();
        onlinePlayerCount.setEnabled(true);
        onlinePlayerCount.setHorizontalAlignment(2);
        onlinePlayerCount.setText("Online: 0");
        playerTab.add(onlinePlayerCount, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_NORTH, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JScrollPane scrollPane2 = new JScrollPane();
        playerTab.add(scrollPane2, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        playerTable = new JTable();
        playerTable.setAutoCreateRowSorter(true);
        scrollPane2.setViewportView(playerTable);
        pluginTab = new JPanel();
        pluginTab.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        rootTabbedPane.addTab("Plugins", pluginTab);
        final JScrollPane scrollPane3 = new JScrollPane();
        pluginTab.add(scrollPane3, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        pluginTable = new JTable();
        pluginTable.setCellSelectionEnabled(false);
        scrollPane3.setViewportView(pluginTable);
    }

    /**
     * Returns the dashboard root component created by the generated layout setup.
     */
    public JComponent $$$getRootComponent$$$() {
        return rootPane;
    }

    /**
     * Creates the handwritten graph and console components consumed by generated layout setup.
     * Memory starts with a fixed percentage scale and zero history, while chunk and entity graphs
     * use zero-filled histories with dynamic extrema enabled.
     */
    private void createUIComponents() {
        ramGraph = new GraphPanel();
        ramGraph.setVariableExtreme(false);
        ramValues = new ArrayList<>();
        for (int i = 0; i < RAM_VALUE_COUNT; i++) {
            ramValues.add(0);
        }
        ramGraph.setValues(ramValues);

        chunkGraph = new GraphPanel();
        chunkGraph.setValues(Collections.nCopies(CHUNK_VALUE_COUNT, 0));

        entityGraph = new GraphPanel();
        entityGraph.setValues(Collections.nCopies(ENTITY_VALUE_COUNT, 0));

        consolePane = new ConsolePanel();
        consolePane.setBackground(new Color(0x131313));
        consolePane.setEditable(false);
    }

    /**
     * Queues ANSI text insertion and moves the caret to the document end on the Swing event thread.
     * The method returns before insertion occurs, and earlier queued fragments retain their
     * event-queue order.
     */
    public void appendTextToConsole(final String text) {
        // Output producers can run on logging or server threads; serialize parser
        // state and document changes through Swing before moving the visible caret.
        SwingUtilities.invokeLater(() -> {
            consolePane.appendANSI(text);
            Document doc = consolePane.getDocument();
            consolePane.setCaretPosition(doc.getLength());
        });
    }

    /**
     * {@code UneditableDefaultTableModel} represents a dashboard table snapshot whose cells reject
     * direct user editing.
     */
    private static class UneditableDefaultTableModel extends DefaultTableModel {
        /**
         * Creates a read-only table model from the supplied rows and column titles.
         */
        public UneditableDefaultTableModel(String[][] data, String[] title) {
            super(data, title);
        }

        /**
         * Returns {@code false} for every dashboard table row and column position.
         */
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    }

    /**
     * {@code CommandAutocomplete} manages a command-field suggestion popup and guarded text
     * replacement on the Swing event thread.
     */
    private static final class CommandAutocomplete {
        /**
         * Command field receiving completed text.
         */
        private final JTextField input;
        /**
         * Server-backed command suggestion source.
         */
        private final AllayCommandSuggester suggester;
        /**
         * Suggestion popup displayed below input.
         */
        private final JPopupMenu popup = new JPopupMenu();
        /**
         * Visible command candidate list model.
         */
        private final DefaultListModel<String> suggestionModel = new DefaultListModel<>();
        /**
         * Single-selection command candidate list.
         */
        private final JList<String> suggestionList = new JList<>(suggestionModel);
        /**
         * {@code completing} suppresses refresh scheduling during programmatic text and caret
         * changes. {@code applyCandidate} resets the flag even when replacement fails.
         */
        private boolean completing;

        /**
         * Creates the suggestion controller and installs popup contents, key bindings, and edit
         * listeners. The controller retains the supplied field and suggester, and its operations
         * run on the Swing event thread.
         */
        private CommandAutocomplete(JTextField input, AllayCommandSuggester suggester) {
            this.input = input;
            this.suggester = suggester;
            this.setupPopup();
            this.setupBindings();
        }

        /**
         * Configures a single-selection scrollable suggestion list without making the popup
         * focusable. Input focus remains with the command field so typing and completion key
         * bindings continue targeting that field.
         */
        private void setupPopup() {
            this.suggestionList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            this.suggestionList.setVisibleRowCount(8);
            this.popup.setFocusable(false);
            this.popup.add(new JScrollPane(suggestionList));
        }

        /**
         * Installs Tab completion, Escape hiding, and document or caret refresh listeners on the
         * command field. Focus traversal is disabled so Tab reaches completion, while
         * edit-triggered refresh is deferred until Swing can finish the current change.
         */
        private void setupBindings() {
            this.input.setFocusTraversalKeysEnabled(false);
            this.input.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_TAB, 0), "complete-command");
            this.input.getActionMap().put("complete-command", new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    completeNext();
                }
            });
            this.input.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "hide-command-completions");
            this.input.getActionMap().put("hide-command-completions", new AbstractAction() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    hide();
                }
            });
            this.input.getDocument().addDocumentListener(new DocumentListener() {
                @Override
                public void insertUpdate(DocumentEvent e) {
                    refreshSuggestionsIfUserEdited();
                }

                @Override
                public void removeUpdate(DocumentEvent e) {
                    refreshSuggestionsIfUserEdited();
                }

                @Override
                public void changedUpdate(DocumentEvent e) {
                    refreshSuggestionsIfUserEdited();
                }
            });
            this.input.addCaretListener(e -> refreshSuggestionsIfUserEdited());
        }

        /**
         * Computes candidates at the current caret, cycles the selected word, and refreshes the
         * popup after replacement. No candidates hide the popup without changing input.
         *
         * <p>
         * Candidate selection compares the current replacement span case-insensitively; an
         * unmatched span selects the first candidate. Spacing depends on whether another candidate
         * extends the chosen text. Programmatic replacement suppresses edit-triggered refresh
         * scheduling, preserving one controlled completion cycle while document and caret listeners
         * fire synchronously.
         */
        private void completeNext() {
            var completion = this.suggester.complete(this.input.getText(), this.input.getCaretPosition());
            var candidates = completion.candidates();
            if (candidates.isEmpty()) {
                hide();
                return;
            }

            var current = this.input.getText().substring(completion.replaceStart(), completion.replaceEnd());
            var candidate = this.chooseCandidate(candidates, current);
            this.applyCandidate(completion, candidate, this.shouldAppendSpace(candidates, candidate));
            this.refreshSuggestions();
        }

        /**
         * Chooses the next case-insensitive candidate after the current replacement text, wrapping
         * at the list end. Callers supply a nonempty list; a single entry or unmatched current text
         * selects its first entry.
         */
        private String chooseCandidate(List<String> candidates, String current) {
            if (candidates.size() == 1) {
                return candidates.getFirst();
            }

            var currentIndex = this.indexOfIgnoreCase(candidates, current);
            // An incomplete word need not equal any candidate; start its cycle
            // at the first suggestion instead of treating it as a selected entry.
            if (currentIndex == -1) {
                return candidates.getFirst();
            }

            return candidates.get((currentIndex + 1) % candidates.size());
        }

        /**
         * Replaces the suggester-designated input span with a candidate and optional trailing
         * space, preserving text outside that span. The caret moves to the replacement end after
         * the text change.
         *
         * <p>
         * {@code completing} covers both text and caret mutation so synchronous listeners do not
         * schedule competing refresh tasks for intermediate state. A {@code finally} block restores
         * normal edit handling even if either mutation fails. Earlier text changes are not rolled
         * back when later caret placement fails.
         */
        private void applyCandidate(AllayCommandSuggester.Completion completion, String candidate, boolean appendSpace) {
            var text = this.input.getText();
            var replacement = appendSpace ? candidate + " " : candidate;
            // Text replacement and caret movement both trigger listeners. Suppress
            // their queued refreshes until this coordinated completion edit finishes.
            this.completing = true;
            try {
                this.input.setText(text.substring(0, completion.replaceStart()) + replacement + text.substring(completion.replaceEnd()));
                this.input.setCaretPosition(completion.replaceStart() + replacement.length());
            } finally {
                this.completing = false;
            }
        }

        /**
         * Returns whether no other candidate extends the selected text case-insensitively. Equal
         * candidates are excluded from that check, allowing completion to append a separator only
         * when the chosen text is not a shorter prefix alternative.
         */
        private boolean shouldAppendSpace(List<String> candidates, String candidate) {
            return candidates.stream()
                    .filter(value -> !value.equalsIgnoreCase(candidate))
                    .noneMatch(value -> startsWithIgnoreCase(value, candidate));
        }

        /**
         * Returns the first case-insensitive matching index, or {@code -1} when no value matches.
         * The supplied list values must be non-{@code null} because matching invokes
         * {@code equalsIgnoreCase} on each entry.
         */
        private int indexOfIgnoreCase(List<String> values, String value) {
            for (int i = 0; i < values.size(); i++) {
                if (values.get(i).equalsIgnoreCase(value)) {
                    return i;
                }
            }

            return -1;
        }

        /**
         * Queues a later suggestion refresh unless a programmatic completion edit is active. The
         * queued operation reads current text and caret state when executed rather than capturing
         * an intermediate document-listener state.
         */
        private void refreshSuggestionsIfUserEdited() {
            if (!this.completing) {
                // Document listeners can run inside a document mutation. Defer
                // suggestion work until the current edit and its caret updates complete.
                SwingUtilities.invokeLater(this::refreshSuggestions);
            }
        }

        /**
         * Recomputes suggestions from the visible command field's current text and caret. Hidden
         * fields, blank text, and empty candidate results hide the popup; available results display
         * with the first candidate selected.
         */
        private void refreshSuggestions() {
            if (!this.input.isShowing() || this.input.getText().isBlank()) {
                hide();
                return;
            }

            var completion = this.suggester.complete(this.input.getText(), this.input.getCaretPosition());
            var candidates = completion.candidates();
            if (candidates.isEmpty()) {
                this.hide();
                return;
            }

            this.showSuggestions(candidates, candidates.getFirst());
        }

        /**
         * Replaces popup contents with the supplied candidates and selects the requested value.
         * Callers supply a nonempty list; popup dimensions keep a minimum width and bounded height
         * so larger results can scroll.
         */
        private void showSuggestions(List<String> candidates, String selected) {
            this.suggestionModel.clear();
            candidates.forEach(this.suggestionModel::addElement);
            this.suggestionList.setSelectedValue(selected, true);
            this.suggestionList.setPrototypeCellValue(candidates.stream().max(Comparator.comparingInt(String::length)).orElse(""));
            // Bound popup height so large result sets scroll instead of covering
            // the window; a minimum width keeps short suggestions readable below input.
            this.popup.setPopupSize(Math.max(this.input.getWidth(), 220), Math.min(180, 24 * Math.max(1, candidates.size()) + 6));
            if (!this.popup.isVisible()) {
                this.popup.show(this.input, 0, this.input.getHeight());
            }
        }

        /**
         * Hides the suggestion popup without changing the command field or candidate model.
         */
        private void hide() {
            this.popup.setVisible(false);
        }

        /**
         * Returns whether a value begins with the supplied prefix using case-insensitive matching.
         * A {@code null} or empty prefix matches every value without requiring a region comparison.
         */
        private boolean startsWithIgnoreCase(String value, String prefix) {
            if (prefix == null || prefix.isEmpty()) {
                return true;
            }

            return value.regionMatches(true, 0, prefix, 0, prefix.length());
        }
    }

    /**
     * {@code DashboardLogAppender} forwards formatted Log4j events to the dashboard's queued
     * console insertion operation.
     */
    private static final class DashboardLogAppender extends AbstractAppender {
        /**
         * Dashboard receiving formatted log text.
         */
        private final Dashboard dashboard;

        /**
         * Creates a named dashboard appender using the supplied layout and destination reference.
         */
        private DashboardLogAppender(String name, Layout<? extends Serializable> layout, Dashboard dashboard) {
            super(name, null, layout, true, Property.EMPTY_ARRAY);
            this.dashboard = dashboard;
        }

        /**
         * Formats the log event and queues its text in the dashboard console.
         */
        @Override
        public void append(LogEvent event) {
            dashboard.appendTextToConsole(getLayout().toSerializable(event).toString());
        }
    }

    /**
     * {@code CommandListener} submits command-field actions to server execution after clearing the
     * Swing input field.
     */
    private class CommandListener implements ActionListener {
        /**
         * Clears the command field, normalizes one optional leading slash, and schedules nonempty
         * input for server execution. The entered command is queued for console display before
         * execution is queued on the server scheduler.
         *
         * <p>
         * This listener runs on the Swing event thread, so command registry work is deferred to the
         * server rather than executed within UI dispatch. Input remains cleared if scheduling or
         * later execution fails, and the copied command string preserves the submitted text
         * independently of later edits to the field.
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            if (commandAutocomplete != null) {
                commandAutocomplete.hide();
            }
            var cmd = cmdInput.getText().trim();
            cmdInput.setText(""); // clear the input
            if (cmd.isEmpty()) return;
            if (cmd.startsWith("/")) {
                cmd = cmd.substring(1);
            }
            if (cmd.isEmpty()) return;

            appendTextToConsole(cmd + "\n"); // show what was run in the console
            var server = Server.getInstance();
            var command = cmd;
            // Command execution belongs to the server scheduler rather than Swing;
            // capture the submitted text before later user edits change the input field.
            server.getScheduler().runLater(server, () -> Registries.COMMANDS.execute(Server.getInstance(), command));
        }
    }
}
