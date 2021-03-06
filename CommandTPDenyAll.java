package kdx7214.necessities;

import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;

public class CommandTPDenyAll extends CommandBaseNecessities {

	public CommandTPDenyAll() {
	}

	@Override
    public String getCommandName()
    {
        return "tpdenyall";
    }

	@Override
    public String getCommandUsage(ICommandSender par1ICommandSender)
    {
    	return "/" + getCommandName() + " <true/false>" ;
    }

	@Override
    public void processCommand(ICommandSender sender, String[] par2ArrayOfStr)
    {

    	EntityPlayer player = getCommandSenderAsPlayer(sender) ; 

		if (par2ArrayOfStr.length != 1) {
    		player.addChatMessage(getCommandUsage(sender)) ;
    		return ;
    	}

    	if (!par2ArrayOfStr[0].equalsIgnoreCase("true") && !par2ArrayOfStr[0].equalsIgnoreCase("false")) {
    		player.addChatMessage(getCommandUsage(sender)) ;
    		return ;
    	}
    	

    	NBTTagCompound playerdata = NecessitiesMain.instance.necessities_data.getCompoundTag(player.username) ;
    	NecessitiesMain.instance.necessities_data.setCompoundTag(player.username, playerdata) ;
    	NBTTagCompound tpa = playerdata.getCompoundTag("[Tpa]") ;
    	playerdata.setCompoundTag("[Tpa]", tpa) ;

    	if (par2ArrayOfStr[0].equalsIgnoreCase("true")) {
    		tpa.setBoolean("DenyAll",  true) ;
    	} else {
    		if (tpa.hasKey("DenyAll"))
    			tpa.removeTag("DenyAll") ;
    	}
    
    
    } // public void processCommand(...)
	
	@Override
    public boolean canCommandSenderUseCommand(ICommandSender sender) {
		return hasPermission(sender, "necessities.tpdenyall", false, false) ;
    } // public boolean canCommandSenderUseCommand(...)

} // public class CommandTPDenyAll
