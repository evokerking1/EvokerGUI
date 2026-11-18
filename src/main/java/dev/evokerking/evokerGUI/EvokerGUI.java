package dev.evokerking.evokerGUI;

import dev.evokerking.evokerGUI.listeners.InventoryListener;
import org.bukkit.plugin.Plugin;

import java.util.logging.Level;

public final class EvokerGUI {
    private static boolean initialized = false;
    private static boolean bedrockSupport = false;

    private EvokerGUI() {
        throw new UnsupportedOperationException("EvokerGUI is a utility class and cannot be instantiated.");
    }

    /**
     * Initializes EvokerGUI and registers its listeners.
     * <p>
     * This method will also attempt to see if Geyser-Spigot is setup on the server for enhanced bedrock support
     * <p>
     * Call this once from your plugin's onEnable method:
     * <p>
     * EvokerGUI.init(this);
     *
     * @param plugin the plugin using EvokerGUI
     */
    public static void init(Plugin plugin) {
        plugin.getLogger().log(Level.INFO, "[EvokerGUI] Initializing EvokerGUI");
        if (initialized) {
            plugin.getLogger().log(Level.WARNING, "[EvokerGUI] Detected a double init for EvokerGUI");
            return;
        }

        plugin.getServer().getPluginManager().registerEvents(new InventoryListener(), plugin);
        initialized = true;

        plugin.getLogger().log(Level.INFO, "[EvokerGUI] EvokerGUI Initialized, now trying to enable bedrock support");


        if(plugin.getServer().getPluginManager().isPluginEnabled("Geyser-Spigot")) {
            bedrockSupport = true;
            plugin.getLogger().log(Level.INFO, "[EvokerGUI] Bedrock support initialized successfully");
        } else  {
            bedrockSupport = false;
            plugin.getLogger().log(Level.WARNING, "[EvokerGUI] Bedrock support failed to initialize, this is not crucial UNLESS you want to add bedrock support, then please check that Geyser is installed");
        }


    }


    /**
     * Returns whether EvokerGUI has already been initialized.
     *
     * @return true if initialized, otherwise false
     */
    public static boolean isInitialized() {
        return initialized;
    }
}
