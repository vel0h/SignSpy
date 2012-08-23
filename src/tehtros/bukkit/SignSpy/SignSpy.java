package tehtros.bukkit.SignSpy;

import java.io.IOException;
import java.util.logging.Logger;

import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import tehtros.bukkit.SignSpy.Metrics.Graph;

public class SignSpy extends JavaPlugin {
	public static SignSpy plugin;
	public final Logger log = Logger.getLogger("Minecraft");
	public String name;
	public String text;
	public int signs = 0;
	PluginDescriptionFile pdf;
	PluginManager pm;
	public final SignListener signListener = new SignListener(this);

	@Override
	public void onEnable() {
		pdf = getDescription();
		pm = getServer().getPluginManager();
		pm.registerEvents(this.signListener, this);
		log.info("[" + pdf.getName() + "] " + pdf.getName() + " v" + pdf.getVersion() + " is enabled! :D");
		
		try {
			startMetrics();
		} catch(IOException e) {
			log.warning("Metrics fails to start! D:");
		}
	}

	@Override
	public void onDisable() {
		pdf = getDescription();
		log.info("[" + pdf.getName() + "] " + pdf.getName() + " v" + pdf.getVersion() + " is disabled! D:");
	}
	
	public void startMetrics() throws IOException {
		Metrics mets = new Metrics(this);
		Graph exGraph = mets.createGraph("Extra Stats");

		exGraph.addPlotter(new Metrics.Plotter("Signs Spyed") {

			@Override
			public int getValue() {
				return signs;
			}

		});

		mets.start();
	}
}
