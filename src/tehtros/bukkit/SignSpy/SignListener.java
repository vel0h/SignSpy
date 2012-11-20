package tehtros.bukkit.SignSpy;

import org.bukkit.ChatColor;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;

public class SignListener implements Listener {
	public static SignSpy plugin;

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

		if(!sender.hasPermission("signspy.ignore")) {
			if(!message[0].isEmpty() || !message[1].isEmpty() || !message[2].isEmpty() || !message[3].isEmpty()) {
				plugin.signs++;

				plugin.log.info("[SignSpy] " + name + " has placed a sign!");
				plugin.log.info("[SignSpy] Location: x(" + locx + ") y(" + locy + ") z(" + locz + ")");

				for(int i = 0; i < message.length; i++) {
					int o = i + 1;
					if(!message[i].isEmpty()) {
						plugin.log.info("[SignSpy] Line " + o + ": " + message[i]);
					}
				}

				for(Player p : plugin.getServer().getOnlinePlayers()) {
					if(p.hasPermission("signspy.spy") || p.isOp()) {
						p.sendMessage(ChatColor.DARK_AQUA + "[SignSpy] " + ChatColor.DARK_PURPLE + name + ChatColor.GOLD + " has placed a sign!");
						p.sendMessage(ChatColor.DARK_AQUA + "[SignSpy] " + ChatColor.GOLD + "Location: x(" + locx + ") y(" + locy + ") z(" + locz + ")");
						for(int i = 0; i < message.length; i++) {
							int o = i + 1;
							if(!message[i].isEmpty()) {
								p.sendMessage(ChatColor.DARK_AQUA + "[SignSpy] " + ChatColor.GOLD + "Line " + o + ": " + ChatColor.YELLOW + message[i]);
							}
						}
					}
				}
			}
		}
	}
}
