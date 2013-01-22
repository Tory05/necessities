package kdx7214.necessities;

import java.util.Collection;
import java.util.List;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.src.ModLoader;
import net.minecraft.util.ChunkCoordinates;

public class CommandSetHome extends CommandBaseNecessities {

	public CommandSetHome() {
	}
		
    public String getCommandName()
    {
        return "sethome";
    }

    public String getCommandUsage(ICommandSender par1ICommandSender)
    {
    	return "/" + getCommandName() + " <homename>" ;
    }

    public void processCommand(ICommandSender sender, String[] par2ArrayOfStr)
    {
    	MinecraftServer server = ModLoader.getMinecraftServerInstance() ;
    	EntityPlayer player = getCommandSenderAsPlayer(sender) ; 

    	// First sanity check the arguments
    	if (par2ArrayOfStr.length != 1) {
    		sender.sendChatToPlayer("Usage:  /" + getCommandName() + " <homename>") ;
    		return ;
    	}
        	
    	// Now deal with the NBT stuff
    	NBTTagCompound playerdata = NecessitiesMain.instance.necessities_data.getCompoundTag(player.username) ;
    	NecessitiesMain.instance.necessities_data.setCompoundTag(player.username, playerdata) ;
    	NBTTagCompound homes = playerdata.getCompoundTag("Homes") ;
    	playerdata.setCompoundTag("Homes", homes) ;
    	
    	Collection c = homes.getTags() ;
    	if ((c.size() > NecessitiesMain.instance.necessities_data.getInteger("[HomeCount]")) || (c.size() == NecessitiesMain.instance.necessities_data.getInteger("[HomeCount]") && !homes.hasKey(par2ArrayOfStr[0]))) {
    		sender.sendChatToPlayer("Too many homes defined.") ;
    		return ;
    	}

    	// There be voodoo here.   If key not found, creates new NBTTagCompound automagically
    	NBTTagCompound house = homes.getCompoundTag(par2ArrayOfStr[0]) ;
		house.setDouble("PosX", player.posX) ;
		house.setDouble("PosY", player.posY) ;
		house.setDouble("PosZ", player.posZ) ;
		house.setInteger("Dim", player.dimension) ;
		house.setFloat("Yaw", player.rotationYaw) ;
		house.setFloat("Pitch", player.rotationPitch); 
		// house.setFloat("Yaw",  player.cameraYaw) ;
		homes.setCompoundTag(par2ArrayOfStr[0], house) ;
		sender.sendChatToPlayer("Home \"" + par2ArrayOfStr[0] + "\" set.") ;
    	
    } // public void processCommand(...)
  	
	@Override
    public boolean canCommandSenderUseCommand(ICommandSender sender) {
		return hasPermission(sender, "necessities.sethome", false, false) ;
    } // public boolean canCommandSenderUseCommand(...)
		

} // public class CommandSetHome

