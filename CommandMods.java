package kdx7214.necessities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.ModContainer;
import cpw.mods.fml.common.network.FMLNetworkHandler;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.src.ModLoader;

public class CommandMods extends CommandBaseNecessities {

	public CommandMods() {
	}
		
    public String getCommandName()
    {
        return "mods";
    }

    public String getCommandUsage(ICommandSender par1ICommandSender)
    {
    	return "/" + getCommandName() ;
    }

    public List getCommandAliases()
    {
    	ArrayList<String> temp = new ArrayList() ;
    	temp.add("plugins") ;
        return temp ;
    }

    public void processCommand(ICommandSender sender, String[] par2ArrayOfStr)
    {
    	String result = "" ;
    	
    	List<ModContainer> mods = Loader.instance().getModList() ; 
    	for (ModContainer mc : mods) 
    	{
    		if (2 + result.length() + mc.getModId().length() > 100) {
    			sender.sendChatToPlayer(result) ;
    			result = "\u00a72" + mc.getModId() ;
    		} else {
    			if (result.length() > 0)
    				result = result + ", " + mc.getModId() ;
    			else
    				result = "\u00a72" + mc.getModId() ;
    		} // if (...)
            
        } // for (...)
		sender.sendChatToPlayer(result) ;
    } // public void processCommand(...)
  	

	@Override
    public boolean canCommandSenderUseCommand(ICommandSender sender) {
		return hasPermission(sender, "necessities.mods", false, false) ;
    } // public boolean canCommandSenderUseCommand(...)
   
    
    

} // public class CommandMods

