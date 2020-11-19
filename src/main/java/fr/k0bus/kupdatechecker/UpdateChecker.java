package fr.k0bus.kupdatechecker;

import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Scanner;
import java.util.logging.Level;

public class UpdateChecker {

    private final JavaPlugin plugin;
    private final Version version;
    private final int resourceId;

    public UpdateChecker(JavaPlugin plugin, int resourceId) {
        this.plugin = plugin;
        this.version = new Version(this.plugin.getDescription().getVersion());
        this.resourceId = resourceId;
    }

    public Version getVersion() {
        String version = null;
        try (InputStream inputStream = new URL("https://api.spigotmc.org/legacy/update.php?resource=" + this.resourceId).openStream(); Scanner scanner = new Scanner(inputStream)) {
            if (scanner.hasNext()) {
                version = scanner.next();
            }
        } catch (IOException exception) {
            this.plugin.getLogger().info("Cannot look for updates: " + exception.getMessage());
        }
        return new Version(version);
    }
    public boolean isUpToDate()
    {
        return this.version.biggerThan(getVersion());
    }
    public void checkUpdate()
    {
        if(isUpToDate())
            plugin.getLogger().log(Level.INFO, plugin.getDescription().getName() + " is up to date");
        else
            plugin.getLogger().log(Level.WARNING, plugin.getDescription().getName() + " can be updated - Actual version : " + version.toString() + " - New version : " + getVersion().toString());
    }
}
