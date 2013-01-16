package kdx7214.necessities;

import java.io.File;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import cpw.mods.fml.common.Loader;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.src.ModLoader;

public class CommandSetMotd extends CommandBaseNecessities {

	public CommandSetMotd() {
	}
		
    public String getCommandName()
    {
        return "setmotd";
    }

    public String getCommandUsage(ICommandSender par1ICommandSender)
    {
    	return "/" + getCommandName() + " Message";
    }

    public void processCommand(ICommandSender sender, String[] par2ArrayOfStr)
    {
    	String motd = new String() ;
    	
    	if (par2ArrayOfStr.length == 0) {  // Delete the current MOTD, if any
    		motd = "" ;
    	} else {
    		for (String c : par2ArrayOfStr) {
    			motd = motd + c + " " ;
    		}
    	}
    	NecessitiesMain.instance.necessities_data.setString("[MOTD]", motd) ;

    } // public void processCommand(...)
  	
		
	@Override
    public boolean canCommandSenderUseCommand(ICommandSender sender)
    {
		if (Loader.instance().isModLoaded("MCPermissions")) {
			if (isPlayer(sender) && NecessitiesPermissions.Instance.hasPermission(sender.getCommandSenderName(), "necessities.setmotd"))	
				return true ;
		} else if (isOP(sender)) {
			return true ;
		}
		
		return false ;
		
    } // public boolean canCommandSenderUseCommand(...)

    
} // public class CommandSetMotd

