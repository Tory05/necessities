package kdx7214.necessities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.src.ModLoader;

public class CommandListHome extends CommandBaseNecessities {

	public CommandListHome() {
	}
		
    public String getCommandName()
    {
        return "listhome";
    }

    public String getCommandUsage(ICommandSender par1ICommandSender)
    {
    	return "/" + getCommandName() ;
    }

    public List getCommandAliases()
    {
    	ArrayList<String> temp = new ArrayList() ;
    	temp.add("listhomes") ;
        return temp ;
    }

    public void processCommand(ICommandSender sender, String[] par2ArrayOfStr)
    {
    	assert(sender instanceof EntityPlayer || sender instanceof EntityPlayerMP) ;
    	
    	MinecraftServer server = ModLoader.getMinecraftServerInstance() ;
    	EntityPlayer player = getCommandSenderAsPlayer(sender) ; 

    	NBTTagCompound playerdata = NecessitiesMain.instance.necessities_data.getCompoundTag(player.username) ;
    	NecessitiesMain.instance.necessities_data.setCompoundTag(player.username, playerdata) ;
    	NBTTagCompound homes = playerdata.getCompoundTag("Homes") ;
    	playerdata.setCompoundTag("Homes", homes) ;
    	
    	Collection c = homes.getTags() ;
    	if (c.isEmpty()) {
    		sender.sendChatToPlayer("No homes have been defined.") ;
    		return ;
    	} else {
    		String str = "Homes:  " ;
    		
    		for (Object o: c) {
    			NBTTagCompound t = (NBTTagCompound) o ;
    			str = str + t.getName() + " " ;
    		}
    		sender.sendChatToPlayer(str) ;
    	} // if (c.isEmpty())
    	
    } // public voice processCommand(...)
  	
	@Override
    public boolean canCommandSenderUseCommand(ICommandSender sender) {
		return hasPermission(sender, "necessities.listhome", false, false) ;
    } // public boolean canCommandSenderUseCommand(...)

    
    
} // public class CommandListHome


