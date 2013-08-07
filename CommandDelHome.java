package kdx7214.necessities;

import java.util.List;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.src.ModLoader;

public class CommandDelHome extends CommandBaseNecessities {

	public CommandDelHome() {
	}
		
    public String getCommandName()
    {
        return "delhome";
    }

    public List getCommandAliases()
    {
        return null;
    }
	
	@Override
	public String getCommandUsage(ICommandSender var1) {
    	return "/" + getCommandName() + " <homename>" ;
	}

	@Override
	public void processCommand(ICommandSender sender, String[] par2ArrayOfStr) {

		assert(sender instanceof EntityPlayer || sender instanceof EntityPlayerMP) ;
		
		MinecraftServer server = ModLoader.getMinecraftServerInstance() ;
		EntityPlayer player = getCommandSenderAsPlayer(sender) ;
		
		if (par2ArrayOfStr.length != 1) {
			player.addChatMessage(getCommandUsage(sender)) ;
			return ;
		}

    	NBTTagCompound playerdata = NecessitiesMain.instance.necessities_data.getCompoundTag(player.username) ;
    	NecessitiesMain.instance.necessities_data.setCompoundTag(player.username, playerdata) ;
    	NBTTagCompound homes = playerdata.getCompoundTag("Homes") ;
    	playerdata.setCompoundTag("Homes", homes) ;

    	if (!homes.hasKey(par2ArrayOfStr[0])) {
    		player.addChatMessage("Unknown home.") ;
    		return ;
    	}
    	
    	homes.removeTag(par2ArrayOfStr[0]) ;  
    	player.addChatMessage("Home \"" + par2ArrayOfStr[0] + "\" deleted.") ;
	}

	@Override
    public boolean canCommandSenderUseCommand(ICommandSender sender) {
		return hasPermission(sender, "necessities.delhome", false, false) ;
    }

	
	
} // public class CommandDelHome

