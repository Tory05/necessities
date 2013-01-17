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
    	NecessitiesMain.instance.necessities_data.setString("[MOTD]", colorize(motd)) ;

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


	public String colorize(String n) {
		String r = "" ;
		
		for (int i = 0; i < n.length(); ++i) {
			Character c = n.charAt(i) ;
			if (c != '&') {
				r += c ;
				continue ;
			} // if (c != '&')
			
			if (i >= n.length() - 1) {
				return r ;
			}
			
			i++ ;
			c = n.charAt(i) ;
			switch (c) {
			case '0':
				r += "\u00a70" ;
				break ;
			case '1':
				r += "\u00a71" ;
				break ;
			case '2':
				r += "\u00a72" ;
				break ;
			case '3':
				r += "\u00a73" ;
				break ;
			case '4':
				r += "\u00a74" ;
				break ;
			case '5': 
				r += "\u00a75" ;
				break ;
			case '6':
				r += "\u00a76" ;
				break ;
			case '7':
				r += "\u00a77" ;
				break ;
			case '8':
				r += "\u00a78" ;
				break ;
			case '9':
				r += "\u00a79" ;
				break ;
			case 'a':
				r += "\u00a7a" ;
				break ;
			case 'b':
				r += "\u00a7b" ;
				break ;
			case 'c':
				r += "\u00a7c" ;
				break ;
			case 'd':
				r += "\u00a7d" ;
				break ;
			case 'e':
				r += "\u00a7e" ;
				break ;
			case 'f':
				r += "\u00a7f" ;
				break ;
			case 'l':
				r += "\u00a7l" ;
				break ;
			case 'm':
				r += "\u00a7m" ;
				break ;
			case 'n':
				r += "\u00a7n" ;
				break ;
			case 'o':
				r += "\u00a7o" ;
				break ;
			case 'r':
				r += "\u00a7r" ;
				break ;
			default:
				r += c ;
				break ;
			} // switch(c)
		} // for (...)
		
		return r ;
	} // public String colorize(...)
	
	
	
	
	
} // public class CommandSetMotd

