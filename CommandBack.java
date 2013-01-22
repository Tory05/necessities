package kdx7214.necessities;

import java.util.Collection;
import java.util.List;
import net.minecraft.server.MinecraftServer;
import net.minecraft.src.ModLoader;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.command.* ;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.* ;


public class CommandBack extends CommandBaseNecessities {

	public CommandBack() {
	}
		
    public String getCommandName()
    {
        return "back";
    }

    public String getCommandUsage(ICommandSender par1ICommandSender)
    {
    	return "/" + getCommandName() ;
    }

    public List getCommandAliases()
    {
        return null;
    }

    public void processCommand(ICommandSender sender, String[] par2ArrayOfStr)
    {
    	MinecraftServer server = ModLoader.getMinecraftServerInstance() ;
    	EntityPlayer player = getCommandSenderAsPlayer(sender) ; 
    	EntityPlayerMP playerMP = (EntityPlayerMP) sender ;
        	
    	// Now deal with the NBT stuff
    	NBTTagCompound playerdata = NecessitiesMain.instance.necessities_data.getCompoundTag(player.username) ;
    	NecessitiesMain.instance.necessities_data.setCompoundTag(player.username, playerdata) ;

    	if (!playerdata.hasKey("[Back]")) {
    		sender.sendChatToPlayer("No position to go back to!") ;
    		return ;
    	}
    	
    	NBTTagCompound back = playerdata.getCompoundTag("[Back]") ;
    	playerdata.setCompoundTag("[Back]", back) ;
    	double posX = back.getDouble("PosX") ;
    	double posY = back.getDouble("PosY") ;
    	double posZ = back.getDouble("PosZ") ;
    	float pitch = back.getFloat("Pitch") ;
    	float yaw = back.getFloat("Yaw") ;
    	int dim = back.getInteger("Dim") ;

    	setBackLocation(player) ;
    	
    	if (player.dimension != dim)
    		server.getConfigurationManager().transferPlayerToDimension(((EntityPlayerMP) player), dim);

       	playerMP.playerNetServerHandler.setPlayerLocation(posX, posY, posZ, yaw, pitch) ;
       	
    } // public void processCommand(...)
  	
	@Override
    public boolean canCommandSenderUseCommand(ICommandSender sender)
    {
		return hasPermission(sender, "necessities.back", false, false) ;
    }


    
    
} // public class CommandBack

