package kdx7214.necessities;

import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.src.ModLoader;

public class CommandLimit extends CommandBaseNecessities {

	public CommandLimit() {
	}

	@Override
    public String getCommandName()
    {
        return "limit";
    }

	@Override
    public String getCommandUsage(ICommandSender par1ICommandSender)
    {
    	return "/" + getCommandName() + " [n]" ;
    }

	@Override
    public void processCommand(ICommandSender sender, String[] var2)
    {
		EntityPlayer player = getCommandSenderAsPlayer(sender) ;
		
		if (var2.length != 1) {
			player.addChatMessage(getCommandUsage(sender)) ;
			return ;
		}
		
    	NBTTagCompound playerdata = NecessitiesMain.instance.necessities_data.getCompoundTag(player.username) ;
    	NecessitiesMain.instance.necessities_data.setCompoundTag(player.username, playerdata) ;
   		NBTTagCompound we = playerdata.getCompoundTag("[Worldedit]") ;
   		playerdata.setCompoundTag("[Worldedit]",  we) ;
   		int x = Integer.parseInt(var2[0]) ;
   		we.setInteger("Limit",  x) ;
   		player.addChatMessage("\u00a7dOnly " + x + " blocks will be changed.") ;
    }
	
	@Override
    public boolean canCommandSenderUseCommand(ICommandSender sender) {
		return hasPermission(sender, "necessities.limit", false, false) ;
    } // public boolean canCommandSenderUseCommand(...)

} // public class CommandWhoAmI
