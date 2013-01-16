package kdx7214.necessities;

import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;

public class CommandTPReject extends CommandBaseNecessities {

	public CommandTPReject() {
	}

	@Override
    public String getCommandName()
    {
        return "tpreject";
    }

	@Override
    public String getCommandUsage(ICommandSender par1ICommandSender)
    {
    	return "/" + getCommandName() ;
    }

	@Override
    public void processCommand(ICommandSender sender, String[] par2ArrayOfStr)
    {
    	EntityPlayer player = getCommandSenderAsPlayer(sender) ; 

    	NBTTagCompound playerdata = NecessitiesMain.instance.necessities_data.getCompoundTag(player.username) ;
    	NecessitiesMain.instance.necessities_data.setCompoundTag(player.username, playerdata) ;
    	NBTTagCompound tpa = playerdata.getCompoundTag("[Tpa]") ;
    	playerdata.setCompoundTag("[Tpa]", tpa) ;
    	String who = tpa.getString("Who") ;
    	EntityPlayerMP other = func_82359_c(sender, who) ;
    	other.sendChatToPlayer(player.username + " has rejected your teleport request.") ;
    	player.sendChatToPlayer("Teleport request from " + who + " rejected.") ;
    	tpa.removeTag("Who") ;
    	tpa.removeTag("PosX") ;
    	tpa.removeTag("PosY") ;
    	tpa.removeTag("PosZ") ;
    	tpa.removeTag("Yaw") ;
    	tpa.removeTag("Pitch") ;
    	tpa.removeTag("Dim") ;
    }
	
	@Override
    public boolean canCommandSenderUseCommand(ICommandSender sender)
    {
		if (isPlayer(sender) && NecessitiesPermissions.Instance.hasPermission(sender.getCommandSenderName(), "necessities.tpreject"))
			return true ;
		else
			return false ;
    } // public boolean canCommandSenderUseCommand(...)

} // public class CommandReject
