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

public class CommandSeen extends CommandBaseNecessities {

	public CommandSeen() {
	}
	
	@Override
	public String getCommandName() {
		return "seen" ;
	}

	@Override
	public String getCommandUsage(ICommandSender var1) {
		return "/" + getCommandName() + " <username>";
	}

	@Override
	public void processCommand(ICommandSender var1, String[] var2) {
    	MinecraftServer server = ModLoader.getMinecraftServerInstance() ;
    	EntityPlayer player = getCommandSenderAsPlayer(var1) ; 
    	
    	if (var2.length != 1) {
    		var1.sendChatToPlayer("Usage:  /" + getCommandName() + " <playername>") ;
    		return ;
    	}
    	
    	NBTTagCompound playerdata = NecessitiesMain.instance.necessities_data.getCompoundTag(var2[0]) ;
    	long lastseen = playerdata.getLong("[Seen]") ;

    	EntityPlayerMP p ;
    	for (int i = 0; i < MinecraftServer.getServer().getConfigurationManager().playerEntityList.size(); ++i)
        {
            p = (EntityPlayerMP)MinecraftServer.getServer().getConfigurationManager().playerEntityList.get(i);

            if (p.username.equalsIgnoreCase(var2[0]))
            {
                var1.sendChatToPlayer("Player \"" + var2[0] + "\" is currently online.") ;
                return ;
            }
        }    	
    	
    	if (lastseen <= 0) {
    		var1.sendChatToPlayer("Player \"" + var2[0] + "\" has never been seen.") ;
    		return ;
    	} else {
    		long now = System.currentTimeMillis() ;
    		lastseen = now - lastseen ;
    		lastseen /= 1000 ; 		// Convert to seconds
    		int days = (int)(lastseen / 86400) ;
    		int hours = (int)((lastseen - (days * 86400)) / 3600  ) ;
    		int mins = (int)((lastseen - (days * 86400) - (hours * 3600)) / 60) ;
    		var1.sendChatToPlayer("Player \"" + var2[0] + "\" was last on " + days + " days, " + hours + " hours, " + mins + " minutes ago.") ;
    	}

	} // public void processCommand(...)

	@Override
    public boolean canCommandSenderUseCommand(ICommandSender sender)
    {
		if (isPlayer(sender) && NecessitiesPermissions.Instance.hasPermission(sender.getCommandSenderName(), "necessities.seen"))
			return true ;
		else
			return false ;
    } // public boolean canCommandSenderUseCommand(...)
	

}
