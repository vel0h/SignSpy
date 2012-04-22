package tehtros.bukkit.SignSpy;

import org.bukkit.ChatColor;
import org.bukkit.block.Block;
import org.bukkit.configuration.Configuration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;

public class SignListener implements Listener {
	public static SignSpy plugin;
	public Configuration config;
	public SignListener(SignSpy instance) {
		plugin = instance;
	}
	
	@EventHandler
	public void onSign(SignChangeEvent sign) {
		Player sender = sign.getPlayer();
		String name = sender.getDisplayName();
		Block block = sign.getBlock();
		String[] message = sign.getLines();
		int locx = block.getX();
		int locy = block.getY();
		int locz = block.getZ();

		for(Player p : plugin.getServer().getOnlinePlayers()) {
			if(p.hasPermission("signspy.log") || p.isOp()) {
				p.sendMessage(ChatColor.DARK_AQUA + "[SignSpy] " + ChatColor.DARK_PURPLE + name + ChatColor.GOLD + " has placed a sign!");
				p.sendMessage(ChatColor.DARK_AQUA + "[SignSpy] " + ChatColor.GOLD + "Location: x(" + locx + ") y(" + locy + ") z(" + locz + ")");
				plugin.log.info("[SignSpy] " + name + " has placed a sign!");
				plugin.log.info("[SignSpy] Location: x(" + locx + ") y(" + locy + ") z(" + locz + ")");
				for(int o = 0; o < message.length; o++) {
					int pint = o + 1;
					if(!message[o].isEmpty()) {
						p.sendMessage(ChatColor.DARK_AQUA + "[SignSpy] " + ChatColor.GOLD + "Line " + pint + ": " + ChatColor.YELLOW + message[o]);
						plugin.log.info("[SignSpy] Line " + pint + ": " + message[o]);
					}
				}
			}
		}
	}
}
