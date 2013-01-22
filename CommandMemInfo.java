package kdx7214.necessities;

import java.util.List;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.src.ModLoader;
import net.minecraft.command.* ;


public class CommandMemInfo extends CommandBaseNecessities {

	public CommandMemInfo() {
	}
		
    public String getCommandName()
    {
        return "meminfo";
    }

    public String getCommandUsage(ICommandSender par1ICommandSender)
    {
    	return "/" + getCommandName() ;
    }

    public void processCommand(ICommandSender sender, String[] par2ArrayOfStr)
    {
    	MinecraftServer server = ModLoader.getMinecraftServerInstance() ;
    	long maxmem = Runtime.getRuntime().maxMemory() / 1024L / 1024L ;
    	long usedmem = (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / 1024L / 1024L ;
    	long percent = Runtime.getRuntime().freeMemory() * 100L / Runtime.getRuntime().maxMemory() ;
    	int loadedChunks = NecessitiesMain.instance.necessities_data.getInteger("[Chunks]") ;
    	
    	String msg = "Memory:  " + usedmem + "MB used out of " + maxmem + "MB (" +  percent + "% free)" ;
    	sender.sendChatToPlayer(msg) ;
    	msg = "Loaded Chunks:  " + loadedChunks ;
    	sender.sendChatToPlayer(msg) ;
    	
    } // public void processCommand(...)
  	
	@Override
    public boolean canCommandSenderUseCommand(ICommandSender sender) {
		return hasPermission(sender, "necessities.meminfo", false, false) ;
    } // public boolean canCommandSenderUseCommand(...)

    
    
} // public class CommandMemInfo

