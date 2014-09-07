

import org.bukkit.block.Block;
import org.bukkit.command.BlockCommandSender;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class Plugin extends JavaPlugin
{
	public void onEnable(){
		getLogger().info("onEnable has been invoked!");
	}

	public void onDisable(){
		getLogger().info("onDisable has been invoked!");
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
		if(cmd.getName().equalsIgnoreCase("comWrap")){ // If the player typed /basic then do the following...
			if(!(sender instanceof BlockCommandSender)) // Commands should only be sent from command blocks
				return false;
			Block block = ((BlockCommandSender) sender).getBlock();
			Player dummy = new Proxy(sender.getName(), getServer(), block.getLocation().getWorld(), // proxy will execute command from block
					block.getLocation().getBlockX(), block.getLocation().getBlockY(), block.getLocation().getBlockZ());
			String concatargs="";
			for(int i=0;i<args.length;i++)
				concatargs+=args[i]+" ";
			getLogger().info("Sending command "+concatargs); // So server admin can see command was sent

			getServer().dispatchCommand(dummy, concatargs); // Execute command
			return true;
		} //If this has happened the function will return true. 
		// If this hasn't happened the a value of false will be returned.
		return false; 
	}
}
