package kdx7214.necessities;

import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;

public class CommandPos1 extends CommandBaseNecessities {

	public CommandPos1() {
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
   		we.setDouble("Pos1.X", player.posX) ;
   		we.setDouble("Pos1.Y", player.posY) ;
   		we.setDouble("Pos1.Z", player.posZ) ;
   		player.addChatMessage("\u00a7dPosition 1 set.") ;
    }
	
	@Override
    public boolean canCommandSenderUseCommand(ICommandSender sender) {
		return hasPermission(sender, "necessities.pos1", false, false) ;
    } // public boolean canCommandSenderUseCommand(...)

} // public class CommandPos1
