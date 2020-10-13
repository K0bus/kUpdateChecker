package fr.k0bus;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicReference;
import java.util.logging.Level;

public class UpdateChecker {

    private JavaPlugin plugin;
    private int resourceId;

    public UpdateChecker(JavaPlugin plugin, int resourceId) {
        this.plugin = plugin;
        this.resourceId = resourceId;
    }

    public String getVersion() {
        String version = null;
        try (InputStream inputStream = new URL("https://api.spigotmc.org/legacy/update.php?resource=" + this.resourceId).openStream(); Scanner scanner = new Scanner(inputStream)) {
            if (scanner.hasNext()) {
                version = scanner.next();
            }
        } catch (IOException exception) {
            this.plugin.getLogger().info("Cannot look for updates: " + exception.getMessage());
        }
        return version;
    }
    public boolean isUpToDate()
    {
        return this.plugin.getDescription().getVersion().equals(getVersion());
    }
    public void checkUpdate()
    {
        if(isUpToDate())
            plugin.getLogger().log(Level.INFO, plugin.getDescription().getName() + " is up to date");
        else
            plugin.getLogger().log(Level.WARNING, plugin.getDescription().getName() + " can be updated - Actual version : " + plugin.getDescription().getVersion() + " - New version : " + getVersion());
    }
}
