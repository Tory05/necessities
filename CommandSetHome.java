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
    	return "/" + getCommandName() + " [homename]" ;
    }

    public void processCommand(ICommandSender sender, String[] par2ArrayOfStr)
    {
    	MinecraftServer server = ModLoader.getMinecraftServerInstance() ;
    	EntityPlayer player = getCommandSenderAsPlayer(sender) ; 
    	String h = "" ;
    	

    	if (par2ArrayOfStr.length == 0) {
    		h = "[Default]" ;
    	} else {
    		h= par2ArrayOfStr[0] ;
    	}
        	
    	// Now deal with the NBT stuff
    	NBTTagCompound playerdata = NecessitiesMain.instance.necessities_data.getCompoundTag(player.username) ;
    	NecessitiesMain.instance.necessities_data.setCompoundTag(player.username, playerdata) ;
    	NBTTagCompound homes = playerdata.getCompoundTag("Homes") ;
    	playerdata.setCompoundTag("Homes", homes) ;
    	
    	Collection c = homes.getTags() ;
    	if ((c.size() > NecessitiesMain.instance.necessities_data.getInteger("[HomeCount]")) || (c.size() == NecessitiesMain.instance.necessities_data.getInteger("[HomeCount]") && !homes.hasKey(par2ArrayOfStr[0]))) {
    		player.addChatMessage("Too many homes defined.") ;
    		return ;
    	}

    	// There be voodoo here.   If key not found, creates new NBTTagCompound automagically
    	NBTTagCompound house = homes.getCompoundTag(h) ;
		house.setDouble("PosX", player.posX) ;
		house.setDouble("PosY", player.posY) ;
		house.setDouble("PosZ", player.posZ) ;
		house.setInteger("Dim", player.dimension) ;
		house.setFloat("Yaw", player.rotationYaw) ;
		house.setFloat("Pitch", player.rotationPitch); 
		homes.setCompoundTag(h, house) ;
		player.addChatMessage("Home \"" + h + "\" set.") ;
    	
    } // public void processCommand(...)
  	
	@Override
    public boolean canCommandSenderUseCommand(ICommandSender sender) {
		return hasPermission(sender, "necessities.sethome", false, false) ;
    } // public boolean canCommandSenderUseCommand(...)
		

} // public class CommandSetHome

