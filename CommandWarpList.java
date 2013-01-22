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

public class CommandWarpList extends CommandBaseNecessities {

	public CommandWarpList() {
	}

	@Override
	public String getCommandName() {
		return "warplist" ;
	}

	@Override
	public String getCommandUsage(ICommandSender var1) {
		return "/" + getCommandName() ;
	}

	@Override
	public void processCommand(ICommandSender var1, String[] var2) {
	   	MinecraftServer server = ModLoader.getMinecraftServerInstance() ;
    	EntityPlayer player = getCommandSenderAsPlayer(var1) ; 
    	NBTTagCompound warps = NecessitiesMain.instance.necessities_data.getCompoundTag("[Warps]") ;
    	NecessitiesMain.instance.necessities_data.setCompoundTag("[Warps]", warps) ;
    	
    	Collection c = warps.getTags() ;
    	if (c.isEmpty()) {
    		var1.sendChatToPlayer("No warps have been defined.") ;
    		return ;
    	} else {
    		String str = "Warps:  " ;
    		
    		for (Object o: c) {
    			NBTTagCompound t = (NBTTagCompound) o ;
    			str = str + t.getName() + " " ;
    		}
    	
    		var1.sendChatToPlayer(str) ;
    		
    	} // if (c.isEmpty())

	} // public void processCommand(...)

	@Override
    public boolean canCommandSenderUseCommand(ICommandSender sender) {
		return hasPermission(sender, "necessities.warplist", false, false) ;
    } // public boolean canCommandSenderUseCommand(...)

	
	
}
