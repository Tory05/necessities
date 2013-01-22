package kdx7214.necessities;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import cpw.mods.fml.common.Loader;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.src.ModLoader;
import net.minecraft.util.ChunkCoordinates;

public class CommandSetWarp extends CommandBaseNecessities {

	public CommandSetWarp() {
	}

	@Override
	public String getCommandName() {
		return "setwarp" ;
	}

	@Override
	public String getCommandUsage(ICommandSender var1) {
		return "/" + getCommandName() + " <warpname>" ;
	}

	@Override
	public void processCommand(ICommandSender var1, String[] var2) {
    	MinecraftServer server = ModLoader.getMinecraftServerInstance() ;
    	EntityPlayer player = getCommandSenderAsPlayer(var1) ; 

    	// First sanity check the arguments
    	if (var2.length != 1) {
    		var1.sendChatToPlayer("Usage:  /" + getCommandName() + " <warpname>") ;
    		return ;
    	}
        	
    	// Now deal with the NBT stuff
    	NBTTagCompound warps = NecessitiesMain.instance.necessities_data.getCompoundTag("[Warps]") ;
    	NecessitiesMain.instance.necessities_data.setCompoundTag("[Warps]", warps) ;

    	NBTTagCompound w = warps.getCompoundTag(var2[0]) ;
    	w.setDouble("PosX", player.posX) ;
    	w.setDouble("PosY", player.posY) ;
    	w.setDouble("PosZ", player.posZ) ;
    	w.setFloat("Yaw", player.rotationYaw) ;
    	w.setFloat("Pitch", player.rotationPitch) ;
		w.setInteger("Dim", player.dimension) ;
		warps.setCompoundTag(var2[0], w) ;
		var1.sendChatToPlayer("Warp \"" + var2[0] + "\" created.") ;
   	}

	@Override
    public boolean canCommandSenderUseCommand(ICommandSender sender) {
		return hasPermission(sender, "necessities.setwarp", true, false) ;
    } // public boolean canCommandSenderUseCommand(...)
	
}
