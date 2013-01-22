package kdx7214.necessities;

import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;

public class CommandDelSel extends CommandBaseNecessities {

	public CommandDelSel() {
	}

	@Override
    public String getCommandName()
    {
        return "desel";
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
    	NBTTagCompound playerdata = NecessitiesMain.instance.necessities_data.getCompoundTag(player.username) ;
    	NecessitiesMain.instance.necessities_data.setCompoundTag(player.username, playerdata) ;
   		NBTTagCompound we = playerdata.getCompoundTag("[Worldedit]") ;
   		playerdata.setCompoundTag("[Worldedit]",  we) ;
   		we.removeTag("Pos1.X") ;
   		we.removeTag("Pos1.Y") ;
   		we.removeTag("Pos1.Z") ;
   		we.removeTag("Pos2.X") ;
   		we.removeTag("Pos2.Y") ;
   		we.removeTag("Pos2.Z") ;
   		sender.sendChatToPlayer("Region cleared.") ;
    }
	
	@Override
    public boolean canCommandSenderUseCommand(ICommandSender sender) {
		return hasPermission(sender, "necessities.desel", false, false) ;
    } // public boolean canCommandSenderUseCommand(...)

} // public class CommandDelSel
