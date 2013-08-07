package kdx7214.necessities;

import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.src.ModLoader;

public class CommandToggleEditWand extends CommandBaseNecessities {

	public CommandToggleEditWand() {
	}

	@Override
    public String getCommandName()
    {
        return "toggleeditwand";
    }

	@Override
    public String getCommandUsage(ICommandSender par1ICommandSender)
    {
    	return "/" + getCommandName() ;
    }

	@Override
    public void processCommand(ICommandSender sender, String[] par2ArrayOfStr)
    {
		boolean t = false ;
    	EntityPlayer player = getCommandSenderAsPlayer(sender) ; 
    	NBTTagCompound playerdata = NecessitiesMain.instance.necessities_data.getCompoundTag(player.username) ;
    	NecessitiesMain.instance.necessities_data.setCompoundTag(player.username, playerdata) ;

    	if (playerdata.hasKey("[ToggleEditWand]")) {
    		t = playerdata.getBoolean("[ToggleEditWand]") ;
    		t = !t ;
    	} 
    	playerdata.setBoolean("[ToggleEditWand]", t) ;
    	player.addChatMessage("Wand Status changed.") ;
    	
    } // public void processCommand(...)
	
	@Override
    public boolean canCommandSenderUseCommand(ICommandSender sender) {
		return hasPermission(sender, "necessities.toggleeditwand", false, false) ;
    } // public boolean canCommandSenderUseCommand(...)

} // public class CommandToggleEditWand
