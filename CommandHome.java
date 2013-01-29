package kdx7214.necessities;

import java.util.Collection;
import java.util.List;

import cpw.mods.fml.common.Loader;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.src.ModLoader;
import net.minecraft.util.ChunkCoordinates;

public class CommandHome extends CommandBaseNecessities {

	public CommandHome() {
	}
	
	@Override
    public String getCommandName()
    {
        return "home";
    }

	@Override
    public String getCommandUsage(ICommandSender par1ICommandSender)
    {
    	return "/" + getCommandName() + " [homename]" ;
    }

	@Override
    public void processCommand(ICommandSender sender, String[] par2ArrayOfStr)
    {
    	MinecraftServer server = ModLoader.getMinecraftServerInstance() ;
    	EntityPlayer player = getCommandSenderAsPlayer(sender) ; 
    	EntityPlayerMP playerMP = (EntityPlayerMP) sender ;
    	String ho = "" ;
    	
    	if (par2ArrayOfStr.length == 0) {
    		ho = "[Default]" ;
    	} else {
    		ho = par2ArrayOfStr[0] ;
    	}

    	setBackLocation(player) ;
    	
    	NBTTagCompound playerdata = NecessitiesMain.instance.necessities_data.getCompoundTag(player.username) ;
    	NecessitiesMain.instance.necessities_data.setCompoundTag(player.username, playerdata) ;
    	NBTTagCompound homes = playerdata.getCompoundTag("Homes") ;
    	playerdata.setCompoundTag("Homes", homes) ;
    	
    	// Go to the specified home
    	if (!homes.hasKey(ho)) {
    		sender.sendChatToPlayer("Unknown home.") ;
    		return ;
    	}
    	
    	NBTTagCompound h = homes.getCompoundTag(ho) ;
    	double posX = h.getDouble("PosX") ;
    	double posY = h.getDouble("PosY") ;
    	double posZ = h.getDouble("PosZ") ;
    	float yaw = h.getFloat("Yaw") ;
    	float pitch = h.getFloat("Pitch") ;
    	int dim = h.getInteger("Dim") ;
    	
    	if (player.dimension != dim)
    		server.getConfigurationManager().transferPlayerToDimension(((EntityPlayerMP) player), dim);

       	playerMP.playerNetServerHandler.setPlayerLocation(posX, posY, posZ, yaw, pitch) ;
       	
    } // public voice processCommand(...)
  	
	@Override
    public boolean canCommandSenderUseCommand(ICommandSender sender)  {
		return hasPermission(sender, "necessities.home", false, false) ;
    } // public boolean canCommandSenderUseCommand(...)
	

} // public class CommandHome


