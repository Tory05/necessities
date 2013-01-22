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

    	if (ops.isEmpty()) {
    		sender.sendChatToPlayer("There are no ops in the set.") ;
    		return ;
    	}
    	
    	String msg = "" ;
    	sender.sendChatToPlayer("Current OPs:") ;
    	Iterator i = ops.iterator() ;
    	while (i.hasNext()) {
    		msg += i.next() + " " ;
    		if (msg.length() > 100) {
    			sender.sendChatToPlayer(msg) ;
    			msg = "" ;
    		}
    	} // while(...)
    	sender.sendChatToPlayer(msg) ;
    	
    } // public void processCommand(...)
	
	
	@Override
    public boolean canCommandSenderUseCommand(ICommandSender sender) {
		return hasPermission(sender, "necessities.listops", false, true) ;
    } // public boolean canCommandSenderUseCommand(...)
	

} // public class CommandWhoAmI
