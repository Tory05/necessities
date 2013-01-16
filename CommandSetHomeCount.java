package kdx7214.necessities;

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

public class CommandSetHomeCount extends CommandBaseNecessities {

	public CommandSetHomeCount() {
	}
		
    public String getCommandName()
    {
        return "sethomecount";
    }

    public String getCommandUsage(ICommandSender par1ICommandSender)
    {
    	return "/" + getCommandName() + " #" ;
    }

    public void processCommand(ICommandSender sender, String[] par2ArrayOfStr)
    {

    	if (par2ArrayOfStr.length != 1) {
    		sender.sendChatToPlayer("Usage:  /" + getCommandName() + " #") ;
    		return ;
    	}
    	
    	int hc = new Integer(par2ArrayOfStr[0]) ;
    	if (hc < 1 || hc > 10) {
    		sender.sendChatToPlayer("# homes must be between 1 and 10") ;
    		return ;
    	}
    	
    	NecessitiesMain.instance.necessities_data.setInteger("[HomeCount]", hc) ;
    	
    } // public void processCommand(...)
  	
		
	@Override
    public boolean canCommandSenderUseCommand(ICommandSender sender)
    {
		if (Loader.instance().isModLoaded("MCPermissions")) {
			if (isPlayer(sender) && NecessitiesPermissions.Instance.hasPermission(sender.getCommandSenderName(), "necessities.sethomecount"))	
				return true ;
		} else if (isOP(sender)) {
			return true ;
		}
		
		return false ;
		
    } // public boolean canCommandSenderUseCommand(...)


} // public class CommandSetHomeCount

