package kdx7214.necessities;

import java.io.File;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.src.ModLoader;

public class CommandMotd extends CommandBaseNecessities {

	public CommandMotd() {
	}
		
    public String getCommandName()
    {
        return "motd";
    }

    public String getCommandUsage(ICommandSender par1ICommandSender)
    {
    	return "/" + getCommandName() ;
    }

    public void processCommand(ICommandSender sender, String[] par2ArrayOfStr)
    {
    	String motd = NecessitiesMain.instance.necessities_data.getString("[MOTD]") ;
    	if (motd.length() > 0)
    		sender.sendChatToPlayer(motd) ;
    	else
    		sender.sendChatToPlayer("No Message of the day has been set.") ;
    } // public void processCommand(...)

	@Override
    public boolean canCommandSenderUseCommand(ICommandSender sender) {
		return hasPermission(sender, "necessities.motd", false, false) ;
    } // public boolean canCommandSenderUseCommand(...)
    
} // public class CommandMotd

