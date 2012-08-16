package tehtros.bukkit.SignSpy;

import java.util.logging.Logger;

import org.bukkit.configuration.Configuration;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class SignSpy extends JavaPlugin {
	public static SignSpy plugin;
	public final Logger log = Logger.getLogger("Minecraft");
	public Configuration config;
	public String name;
	public String text;
	PluginDescriptionFile pdf;
	PluginManager pm;
	public final SignListener signListener = new SignListener(this);

	@Override
	public void onEnable() {
		pdf = getDescription();
		pm = getServer().getPluginManager();
		pm.registerEvents(this.signListener, this);
		config();
		log.info("[" + pdf.getName() + "] " + pdf.getName() + " v" + pdf.getVersion() + " is enabled! :D");
	}

	@Override
	public void onDisable() {
		pdf = getDescription();
		log.info("[" + pdf.getName() + "] " + pdf.getName() + " v" + pdf.getVersion() + " is disabled! D:");
	}
}
