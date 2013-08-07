package kdx7214.necessities;

import java.io.File;
import java.util.List;
import java.util.Set;

import cpw.mods.fml.common.Loader;

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


	//
	// See if the player has permissions to perform the command
	// requireOP:   true = this command requires an operator (or permission node)
	// allowConsole:  true = command works from console
	//
	public boolean hasPermission(ICommandSender sender, String node, boolean requireOP, boolean allowConsole) {

		if (!isPlayer(sender)) {
			if (!allowConsole) {
				EntityPlayer player = (EntityPlayer)sender ;
				player.addChatMessage("Command not allowed from server console.") ;
				return false ;
			}
		} // if (!isPlayer(sender))

		// Check for MCPermissions
		if (Loader.instance().isModLoaded("MCPermissions")) {
			if (!NecessitiesPermissions.Instance.hasPermission(sender.getCommandSenderName(), node))
				return false ;
		} // if (MCPermissions)

		// Check for PermissionsEx
		if (Loader.instance().isModLoaded("PermissionsEx")) {
			
		} // if (PermissionsEx)

		if (requireOP) {
			MinecraftServer server = ModLoader.getMinecraftServerInstance() ;
	    	EntityPlayer player = getCommandSenderAsPlayer(sender) ; 
	    	Set ops = server.getConfigurationManager().getOps();
	    	if (!ops.contains(player.username.toLowerCase())) {
	    		return false ;
	    	}
		} // if (requireOP)
			
		return true ;
	} // public boolean hasPermission(...)
	
	
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
   
	
} // public class CommandBaseNecessities


