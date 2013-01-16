package kdx7214.necessities;

import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;

public class CommandWhoAmI extends CommandBaseNecessities {

	public CommandWhoAmI() {
	}

	@Override
    public String getCommandName()
    {
        return "whoami";
    }

	@Override
    public String getCommandUsage(ICommandSender par1ICommandSender)
    {
    	return "/" + getCommandName() ;
    }

	@Override
    public void processCommand(ICommandSender sender, String[] par2ArrayOfStr)
    {
		sender.sendChatToPlayer("Your user name is:  " + sender.getCommandSenderName()) ;
    }
	
	@Override
    public boolean canCommandSenderUseCommand(ICommandSender sender)
    {
		if (isPlayer(sender) && NecessitiesPermissions.Instance.hasPermission(sender.getCommandSenderName(), "necessities.whoami"))
			return true ;
		else
			return false ;
    } // public boolean canCommandSenderUseCommand(...)

} // public class CommandWhoAmI
