package kdx7214.necessities;

import java.util.Iterator;
import java.util.Set;

import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.src.ModLoader;

public class CommandListOps extends CommandBaseNecessities {

	public CommandListOps() {
	}

	@Override
    public String getCommandName()
    {
        return "listops";
    }

	@Override
    public String getCommandUsage(ICommandSender par1ICommandSender)
    {
    	return "/" + getCommandName() ;
    }

	@Override
    public void processCommand(ICommandSender sender, String[] par2ArrayOfStr)
    {
    	Set ops = ModLoader.getMinecraftServerInstance().getConfigurationManager().getOps();
    	EntityPlayer player = getCommandSenderAsPlayer(sender) ;

    	if (ops.isEmpty()) {
    		player.addChatMessage("There are no ops in the set.") ;
    		return ;
    	}
    	
    	String msg = "" ;
    	player.addChatMessage("Current OPs:") ;
    	Iterator i = ops.iterator() ;
    	while (i.hasNext()) {
    		msg += i.next() + " " ;
    		if (msg.length() > 100) {
    			player.addChatMessage(msg) ;
    			msg = "" ;
    		}
    	} // while(...)
    	player.addChatMessage(msg) ;
    	
    } // public void processCommand(...)
	
	
	@Override
    public boolean canCommandSenderUseCommand(ICommandSender sender) {
		return hasPermission(sender, "necessities.listops", false, true) ;
    } // public boolean canCommandSenderUseCommand(...)
	

} // public class CommandWhoAmI
