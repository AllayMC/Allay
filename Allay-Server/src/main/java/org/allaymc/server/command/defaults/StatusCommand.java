package org.allaymc.server.command.defaults;

import com.sun.jna.platform.win32.COM.WbemcliUtil;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import lombok.extern.slf4j.Slf4j;
import org.allaymc.api.command.SimpleCommand;
import org.allaymc.api.command.tree.CommandContext;
import org.allaymc.api.command.tree.CommandTree;
import org.allaymc.api.i18n.TrKeys;
import org.allaymc.api.server.Server;
import org.allaymc.api.utils.TextFormat;
import org.allaymc.api.world.Dimension;
import oshi.SystemInfo;
import oshi.driver.windows.wmi.Win32ComputerSystem;
import oshi.hardware.NetworkIF;
import oshi.util.platform.windows.WmiQueryHandler;

import java.io.File;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static org.allaymc.api.utils.MathUtils.round;

/**
 * Allay Project 2024/2/13
 *
 * @author daoge_cmd
 */
@Slf4j
public class StatusCommand extends SimpleCommand {

    protected static final String UPTIME_FORMAT = TextFormat.RED + "%d" + TextFormat.GOLD + " days " +
                                                  TextFormat.RED + "%d" + TextFormat.GOLD + " hours " +
                                                  TextFormat.RED + "%d" + TextFormat.GOLD + " minutes " +
                                                  TextFormat.RED + "%d" + TextFormat.GOLD + " seconds";
    protected static final Map<String, String> VM_VENDOR = new HashMap<>(10, 0.99f);
    protected static final Map<String, String> VM_MAC = new HashMap<>(10, 0.99f);
    protected static final SystemInfo SYSTEM_INFO = new SystemInfo();
    protected static final String[] VM_MODEL_ARRAY = new String[]
            {
                    "Linux KVM", "Linux lguest", "OpenVZ", "Qemu",
                    "Microsoft Virtual PC", "VMWare", "linux-vserver",
                    "Xen", "FreeBSD Jail", "VirtualBox", "Parallels",
                    "Linux Containers", "LXC", "Bochs"
            };

    static {
        // VM VENDOR
        VM_VENDOR.put("bhyve", "bhyve");
        VM_VENDOR.put("KVM", "KVM");
        VM_VENDOR.put("TCG", "QEMU");
        VM_VENDOR.put("Microsoft Hv", "Microsoft Hyper-V or Windows Virtual PC");
        VM_VENDOR.put("lrpepyh vr", "Parallels");
        VM_VENDOR.put("VMware", "VMware");
        VM_VENDOR.put("XenVM", "Xen HVM");
        VM_VENDOR.put("ACRN", "Project ACRN");
        VM_VENDOR.put("QNXQVMBSQG", "QNX Hypervisor");
        // VM MAC
        VM_MAC.put("00:50:56", "VMware ESX 3");
        VM_MAC.put("00:0C:29", "VMware ESX 3");
        VM_MAC.put("00:05:69", "VMware ESX 3");
        VM_MAC.put("00:03:FF", "Microsoft Hyper-V");
        VM_MAC.put("00:1C:42", "Parallels Desktop");
        VM_MAC.put("00:0F:4B", "Virtual Iron 4");
        VM_MAC.put("00:16:3E", "Xen or Oracle VM");
        VM_MAC.put("08:00:27", "VirtualBox");
        VM_MAC.put("02:42:AC", "Docker Container");
    }

    public StatusCommand() {
        super("status", TrKeys.A_COMMAND_STATUS_DESCRIPTION);
    }

    @Override
    public void prepareCommandTree(CommandTree tree) {
        tree.getRoot()
                .bool("full", false)
                .optional()
                .exec(context -> {
                    boolean full = context.getFirstResult();
                    context.addOutput(TextFormat.GREEN + "---- " + TextFormat.WHITE + "Server Status" + TextFormat.GREEN + " ----");
                    printUpTimeInfo(context);
                    printMemoryUsageInfo(context);
                    printOnlinePlayerInfo(context);
                    printWorldInfo(context);
                    if (full) {
                        printOperationSystemAndJVMInfo(context);
                        printNetworkInfo(context);
                        printCPUInfo(context);
                        printOperationSystemMemoryInfo(context);
                    }
                    return context.success();
                });
    }

    protected static void printOperationSystemMemoryInfo(CommandContext context) {
        context.addOutput(TextFormat.YELLOW + ">>> " + TextFormat.WHITE + "Operation System Memory Info" + TextFormat.YELLOW + " <<<" + TextFormat.RESET);
        var globalMemory = SYSTEM_INFO.getHardware().getMemory();
        var virtualMemory = globalMemory.getVirtualMemory();
        // Physical Memory
        long allPhysicalMemory = globalMemory.getTotal() / 1000;
        long usedPhysicalMemory = (globalMemory.getTotal() - globalMemory.getAvailable()) / 1000;
        double usage = (double) usedPhysicalMemory / allPhysicalMemory * 100;
        var usageColor = TextFormat.GREEN;
        if (usage > 85) usageColor = TextFormat.GOLD;
        context.addOutput(TextFormat.GOLD + "OS memory: ");
        context.addOutput(TextFormat.GOLD + "  Physical memory: " + TextFormat.GREEN + usageColor + toMB(usedPhysicalMemory) + " / " + toMB(allPhysicalMemory) + ". (" + round(usage, 2) + "%)");
        // Virtual Memory
        long allVirtualMemory = virtualMemory.getVirtualMax() / 1000;
        long usedVirtualMemory = virtualMemory.getVirtualInUse() / 1000;
        usage = (double) usedVirtualMemory / allVirtualMemory * 100;
        usageColor = TextFormat.GREEN;
        if (usage > 85) usageColor = TextFormat.GOLD;
        context.addOutput(TextFormat.GOLD + "  Virtual memory: " + TextFormat.GREEN + usageColor + toMB(usedVirtualMemory) + " / " + toMB(allVirtualMemory) + ". (" + round(usage, 2) + "%)");
        // Hardware
        var physicalMemories = globalMemory.getPhysicalMemory();
        if (!physicalMemories.isEmpty()) context.addOutput(TextFormat.GOLD + "  Hardware list: ");
        for (var each : physicalMemories) {
            context.addOutput(TextFormat.AQUA + "    " + each.getBankLabel() + " @ " + formatFreq(each.getClockSpeed()) + TextFormat.WHITE + " " + toMB(each.getCapacity() / 1000));
            context.addOutput(TextFormat.GRAY + "      " + each.getMemoryType() + ", " + each.getManufacturer());
        }
        context.addOutput("");
    }

    protected static void printCPUInfo(CommandContext context) {
        var cpu = SYSTEM_INFO.getHardware().getProcessor();
        var processorIdentifier = cpu.getProcessorIdentifier();
        context.addOutput(TextFormat.YELLOW + ">>> " + TextFormat.WHITE + "CPU Info" + TextFormat.YELLOW + " <<<" + TextFormat.RESET);
        context.addOutput(TextFormat.GOLD + "CPU: " + TextFormat.AQUA + processorIdentifier.getName() + TextFormat.GRAY +
                          " (" + formatFreq(cpu.getMaxFreq()) + " baseline; " + cpu.getPhysicalProcessorCount() + " cores, " + cpu.getLogicalProcessorCount() + " logical cores)");
        context.addOutput(TextFormat.GOLD + "Thread count: " + TextFormat.GREEN + Thread.getAllStackTraces().size());
        context.addOutput(TextFormat.GOLD + "CPU Features: " + TextFormat.RESET + (processorIdentifier.isCpu64bit() ? "64bit, " : "32bit, ") +
                          processorIdentifier.getModel() + ", micro-arch: " + processorIdentifier.getMicroarchitecture());
        context.addOutput("");
    }

    protected static void printNetworkInfo(CommandContext context) {
        try {
            var networkIFs = SYSTEM_INFO.getHardware().getNetworkIFs();
            if (networkIFs != null) {
                context.addOutput(TextFormat.YELLOW + ">>> " + TextFormat.WHITE + "Network Info" + TextFormat.YELLOW + " <<<" + TextFormat.RESET);
                context.addOutput(TextFormat.GOLD + "Network hardware list: ");
                ObjectArrayList<String> list;
                for (var networkIF : networkIFs) {
                    list = new ObjectArrayList<>(networkIF.getIPv4addr().length + networkIF.getIPv6addr().length);
                    list.addElements(0, networkIF.getIPv4addr());
                    list.addElements(list.size(), networkIF.getIPv6addr());
                    context.addOutput(TextFormat.AQUA + "  " + networkIF.getDisplayName());
                    context.addOutput(TextFormat.RESET + "    " + toKB(networkIF.getSpeed()) + "/s " + TextFormat.GRAY + String.join(", ", list));
                }
                context.addOutput("");
            }
        } catch (Exception ignored) {
            context.addOutput(TextFormat.RED + "    Failed to get network info.");
        }
    }

    protected static void printOperationSystemAndJVMInfo(CommandContext context) {
        var os = SYSTEM_INFO.getOperatingSystem();
        var mxBean = ManagementFactory.getRuntimeMXBean();
        context.addOutput(TextFormat.YELLOW + ">>> " + TextFormat.WHITE + "OS & JVM Info" + TextFormat.YELLOW + " <<<" + TextFormat.RESET);
        context.addOutput(TextFormat.GOLD + "OS: " + TextFormat.AQUA + os.getFamily() + " " + os.getManufacturer() + " " +
                           os.getVersionInfo().getVersion() + " " + os.getVersionInfo().getCodeName() + " " + os.getBitness() + "bit, " +
                           "build " + os.getVersionInfo().getBuildNumber());
        context.addOutput(TextFormat.GOLD + "JVM: " + TextFormat.AQUA + mxBean.getVmName() + " " + mxBean.getVmVendor() + " " + mxBean.getVmVersion());
        try {
            var vm = detectVM();
            if (vm == null) context.addOutput(TextFormat.GOLD + "Virtual environment: " + TextFormat.GREEN + "no");
            else context.addOutput(TextFormat.GOLD + "Virtual environment: " + TextFormat.YELLOW + "yes (" + vm + ")");
        } catch (Exception ignore) {}
        context.addOutput("");
    }

    protected static void printWorldInfo(CommandContext context) {
        for (var world : Server.getInstance().getWorldPool().getWorlds().values()) {
            context.addOutput(TextFormat.GREEN + "---- " + TextFormat.WHITE + "World " + world.getWorldData().getName() + " Status" + TextFormat.GREEN + " ----");
            context.addOutput(TextFormat.GOLD + "TPS: " + TextFormat.WHITE + world.getTps());
            context.addOutput(TextFormat.GOLD + "MSPT: " + TextFormat.WHITE + world.getMSPT());
            context.addOutput(TextFormat.GOLD + "TickUsage: " + TextFormat.WHITE + world.getTickUsage() * 100f + "%");
            world.getDimensions().values().forEach(dimension -> printDimensionInfo(context, dimension));
        }
    }

    protected static void printDimensionInfo(CommandContext context, Dimension dimension) {
        context.addOutput(TextFormat.GOLD + "DimensionId " + TextFormat.WHITE + dimension.getDimensionInfo().dimensionId());
        context.addOutput(TextFormat.GOLD + "Chunk count: " + TextFormat.WHITE + dimension.getChunkService().getLoadedChunks().size());
        context.addOutput(TextFormat.GOLD + "Entity count: " + TextFormat.WHITE + dimension.getEntityCount());
        context.addOutput(TextFormat.GOLD + "BlockEntity count: " + TextFormat.WHITE + dimension.getBlockEntityCount());
    }

    protected static void printUpTimeInfo(CommandContext context) {
        long time = System.currentTimeMillis() - Server.getInstance().getStartTime();
        context.addOutput(TextFormat.GOLD + "Uptime: " + formatUptime(time));
    }

    protected static void printMemoryUsageInfo(CommandContext context) {
        Runtime runtime = Runtime.getRuntime();
        double totalMB = round(((double) runtime.totalMemory()) / 1024 / 1024, 2);
        double usedMB = round((double) (runtime.totalMemory() - runtime.freeMemory()) / 1024 / 1024, 2);
        double maxMB = round(((double) runtime.maxMemory()) / 1024 / 1024, 2);
        double usage = usedMB / maxMB * 100;
        TextFormat usageColor = TextFormat.GREEN;
        if (usage > 85) usageColor = TextFormat.GOLD;
        context.addOutput(TextFormat.GOLD + "Used VM memory: " + usageColor + usedMB + " MB. (" + round(usage, 2) + "%)");
        context.addOutput(TextFormat.GOLD + "Total VM memory: " + TextFormat.RED + totalMB + " MB.");
        context.addOutput(TextFormat.GOLD + "Maximum JVM memory: " + TextFormat.RED + maxMB + " MB.");
    }

    protected static void printOnlinePlayerInfo(CommandContext context) {
        Server server = Server.getInstance();
        TextFormat playerColor = TextFormat.GREEN;
        if (((float) server.getOnlinePlayerCount() / (float) server.getNetworkServer().getMaxPlayerCount()) > 0.85) {
            playerColor = TextFormat.GOLD;
        }
        context.addOutput(TextFormat.GOLD + "Players: " + playerColor + server.getOnlinePlayers().size() + TextFormat.GREEN + " online, " +
                          TextFormat.RED + server.getNetworkServer().getMaxPlayerCount() + TextFormat.GREEN + " max. ");
    }

    protected static String toKB(long bytes) {
        return round((bytes / 1024d * 1000), 2) + " KB";
    }

    protected static String toMB(long bytes) {
        return round((bytes / 1024d / 1024 * 1000), 2) + " MB";
    }

    protected static String formatFreq(long hz) {
        if (hz >= 1000000000) {
            return String.format("%.2fGHz", hz / 1000000000.0);
        } else if (hz >= 1000 * 1000) {
            return String.format("%.2fMHz", hz / 1000000.0);
        } else if (hz >= 1000) {
            return String.format("%.2fKHz", hz / 1000.0);
        } else {
            return String.format("%dHz", hz);
        }
    }

    protected static String formatUptime(long uptime) {
        long days = TimeUnit.MILLISECONDS.toDays(uptime);
        uptime -= TimeUnit.DAYS.toMillis(days);
        long hours = TimeUnit.MILLISECONDS.toHours(uptime);
        uptime -= TimeUnit.HOURS.toMillis(hours);
        long minutes = TimeUnit.MILLISECONDS.toMinutes(uptime);
        uptime -= TimeUnit.MINUTES.toMillis(minutes);
        long seconds = TimeUnit.MILLISECONDS.toSeconds(uptime);
        return String.format(UPTIME_FORMAT, days, hours, minutes, seconds);
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    protected static String detectVM() {
        var hardware = SYSTEM_INFO.getHardware();

        // CPU型号检测
        var vmVendor = VM_VENDOR.get(hardware.getProcessor().getProcessorIdentifier().getVendor().trim());
        if (vmVendor != null) {
            return vmVendor;
        }

        // MAC地址检测
        List<NetworkIF> networkIFs = hardware.getNetworkIFs();
        for (NetworkIF nif : networkIFs) {
            String mac = nif.getMacaddr().toUpperCase();
            String oui = mac.length() > 7 ? mac.substring(0, 8) : mac;
            var vmMac = VM_MAC.get(oui);
            if (vmMac != null) return vmMac;
        }

        // 模型检测
        String model = hardware.getComputerSystem().getModel();
        for (String vm : VM_MODEL_ARRAY) {
            if (model.contains(vm)) {
                return vm;
            }
        }
        String manufacturer = hardware.getComputerSystem().getManufacturer();
        if ("Microsoft Corporation".equals(manufacturer) && "Virtual Machine".equals(model)) {
            return "Microsoft Hyper-V";
        }

        //内存型号检测
        if (hardware.getMemory().getPhysicalMemory().getFirst().getManufacturer().equals("QEMU")) {
            return "QEMU";
        }

        //检查Windows系统参数
        //Wmi虚拟机查询只能在Windows上使用，Linux上不执行这个部分即可
        if (System.getProperties().getProperty("os.name").toUpperCase().contains("WINDOWS")) {
            WbemcliUtil.WmiQuery<Win32ComputerSystem.ComputerSystemProperty> computerSystemQuery = new WbemcliUtil.WmiQuery("Win32_ComputerSystem", ComputerSystemEntry.class);
            WbemcliUtil.WmiResult result = WmiQueryHandler.createInstance().queryWMI(computerSystemQuery);
            var tmp = result.getValue(ComputerSystemEntry.HYPERVISORPRESENT, 0);
            if (tmp != null && tmp.toString().equals("true")) {
                return "Hyper-V";
            }
        } else {
            //检查是否在Docker容器中
            //Docker检查只在非Windows上执行
            var file = new File("/.dockerenv");
            if (file.exists()) {
                return "Docker Container";
            }
            var cgroupFile = new File("/proc/1/cgroup");
            if (cgroupFile.exists()) {
                try (var lineStream = Files.lines(cgroupFile.toPath())) {
                    var searchResult = lineStream.filter(line -> line.contains("docker") || line.contains("lxc"));
                    if (searchResult.findAny().isPresent()) {
                        return "Docker Container";
                    }
                } catch (IOException e) {
                    log.error("Error while checking docker", e);
                }
            }
        }

        return null;
    }

    protected enum ComputerSystemEntry {
        HYPERVISORPRESENT
    }
}
