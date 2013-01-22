package kdx7214.necessities;

import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;

public class CommandPos2 extends CommandBaseNecessities {

	public CommandPos2() {
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
    	EntityPlayer player = getCommandSenderAsPlayer(sender) ;
    	NBTTagCompound playerdata = NecessitiesMain.instance.necessities_data.getCompoundTag(player.username) ;
    	NecessitiesMain.instance.necessities_data.setCompoundTag(player.username, playerdata) ;
   		NBTTagCompound we = playerdata.getCompoundTag("[Worldedit]") ;
   		playerdata.setCompoundTag("[Worldedit]",  we) ;
   		we.setDouble("Pos2.X", player.posX) ;
   		we.setDouble("Pos2.Y", player.posY) ;
   		we.setDouble("Pos2.Z", player.posZ) ;
   		sender.sendChatToPlayer("\u00a7dPosition 2 set.") ;
    }
	
	@Override
    public boolean canCommandSenderUseCommand(ICommandSender sender) {
		return hasPermission(sender, "necessities.pos2", false, false) ;
    } // public boolean canCommandSenderUseCommand(...)

} // public class CommandPos2
