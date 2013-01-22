package kdx7214.necessities;

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

public class CommandDelWarp extends CommandBaseNecessities {

	public CommandDelWarp() {
	}

	@Override
	public String getCommandName() {
		return "delwarp" ;
	}

	@Override
	public String getCommandUsage(ICommandSender var1) {
		return "/" + getCommandName() + " <warpname>" ;
	}

	@Override
	public void processCommand(ICommandSender var1, String[] var2) {
    	MinecraftServer server = ModLoader.getMinecraftServerInstance() ;
    	EntityPlayer player = getCommandSenderAsPlayer(var1) ; 

    	NBTTagCompound warps = NecessitiesMain.instance.necessities_data.getCompoundTag("[Warps]") ;
    	NecessitiesMain.instance.necessities_data.setCompoundTag("[Warps]", warps) ;

    	if (!warps.hasKey(var2[0])) {
    		var1.sendChatToPlayer("Unknown warp.") ;
    		return ;
    	}
    	
    	warps.removeTag(var2[0]) ;  
    	var1.sendChatToPlayer("Warp \"" + var2[0] + "\" deleted.") ;

	} // public void processCommand(...)

	@Override
    public boolean canCommandSenderUseCommand(ICommandSender sender) {
		return hasPermission(sender, "necessities.delwarp", true, false) ;
    } // public boolean canCommandSenderUseCommand(...)

}
