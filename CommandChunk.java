package kdx7214.necessities;

import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;

public class CommandChunk extends CommandBaseNecessities {

	public CommandChunk() {
	}

	@Override
    public String getCommandName()
    {
        return "chunk";
    }

	@Override
    public String getCommandUsage(ICommandSender par1ICommandSender)
    {
    	return "/" + getCommandName() ;
    }

	@Override
    public void processCommand(ICommandSender sender, String[] par2ArrayOfStr)
    {
		EntityPlayer player = getCommandSenderAsPlayer(sender) ;
		player.addChatMessage("Your user name is:  " + sender.getCommandSenderName()) ;
    }
	
	@Override
    public boolean canCommandSenderUseCommand(ICommandSender sender) {
		return hasPermission(sender, "necessities.chunk", false, false) ;
    } // public boolean canCommandSenderUseCommand(...)

} // public class CommandChunk
