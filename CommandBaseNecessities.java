package kdx7214.necessities;

import java.io.File;
import java.util.List;
import java.util.Set;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.src.ModLoader;

public class CommandBaseNecessities extends CommandBase {

	public CommandBaseNecessities() {
	}
 
	@Override
	public String getCommandName() {
		return null;
	}

	@Override
    public String getCommandUsage(ICommandSender par1ICommandSender)
    {
    	return "/" + getCommandName() ;
    }

	@Override
    public List getCommandAliases()
    {
        return null;
    }
	

	@Override
	public void processCommand(ICommandSender var1, String[] var2) {
		
	}

	public void setBackLocation(EntityPlayer player) {
    	NBTTagCompound playerdata = NecessitiesMain.instance.necessities_data.getCompoundTag(player.username) ;
    	NecessitiesMain.instance.necessities_data.setCompoundTag(player.username, playerdata) ;

    	NBTTagCompound back = playerdata.getCompoundTag("[Back]") ;
    	playerdata.setCompoundTag("[Back]", back) ;

       	back.setDouble("PosX", player.posX) ;
       	back.setDouble("PosY", player.posY) ;
       	back.setDouble("PosZ", player.posZ) ; 
       	back.setInteger("Dim",  player.dimension) ;
       	back.setFloat("Yaw", player.rotationYaw);
       	back.setFloat("Pitch", player.rotationPitch) ;
       	playerdata.setCompoundTag("[Back]",  back) ;
	} // public void setBackLocation(...)

	
    public String getNecessities() {
    	MinecraftServer server = ModLoader.getMinecraftServerInstance() ;
    	String fname ;
    	
		fname = server.getFolderName() ;
		if (new File(fname).exists() == true) {
		} else {
			fname = "saves/" + server.getFolderName();
		}
		fname = fname + "/Necessities/necessities.dat" ;
		return fname ;
    } // public String getNecessities()

    
    // Check to see if sender is player or console.   true means player
    public boolean isPlayer(ICommandSender var1) {
    	if (var1.getCommandSenderName() == "Rcon" || var1.getCommandSenderName() == "Server") 
    		return false ;
    	else
    		return true ;
    }
   
    
    public boolean isOP(ICommandSender var1) {

    	if (!isPlayer(var1)) {
    		var1.sendChatToPlayer("Command cannot be used from server console.") ;
    		return false ;
    	}
    	
    	MinecraftServer server = ModLoader.getMinecraftServerInstance() ;
    	EntityPlayer player = (EntityPlayer) var1 ;

    	// Only OPs are allowed to set spawn
    	Set ops = server.getConfigurationManager().getOps();
    	if (!ops.contains(player.username.toLowerCase())) {
    		return false ;
    	}

    	
    	return true ;
 	} // public boolean isOP(...)

    
	
} // public class CommandBaseNecessities


