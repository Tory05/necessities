package kdx7214.necessities;

import cpw.mods.fml.common.Loader;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.src.ModLoader;

public class CommandTPA extends CommandBaseNecessities {

	public CommandTPA() {
	}

	@Override
    public String getCommandName()
    {
        return "tpa";
    }

	@Override
    public String getCommandUsage(ICommandSender par1ICommandSender)
    {
    	return "/" + getCommandName() + " <player>" ;
    }

	@Override
    public void processCommand(ICommandSender sender, String[] par2ArrayOfStr)
    {
		EntityPlayer player = getCommandSenderAsPlayer(sender) ;
		EntityPlayerMP other = func_82359_c(sender, par2ArrayOfStr[0]);

		if (par2ArrayOfStr.length != 1) {
			player.addChatMessage(getCommandUsage(sender));
			return;
		}


		NBTTagCompound playerdata = NecessitiesMain.instance.necessities_data.getCompoundTag(other.username) ;
		NecessitiesMain.instance.necessities_data.setCompoundTag(other.username, playerdata) ;
		NBTTagCompound tpa = playerdata.getCompoundTag("[Tpa]") ;
		playerdata.setCompoundTag("[Tpa]", tpa) ;

		if (tpa.hasKey("DenyAll")) {
			player.addChatMessage("Player " + other.username + " has blocked all teleport requests.") ;
			return ;
		}
		tpa.setString("Command", "TPA") ;
		tpa.setString("Who", player.username) ;
		player.addChatMessage("Sending teleport request to player " + other.username) ;
		other.addChatMessage(player.username	+ " has requested to teleport to you.  Use /tpaccept to accept or /tpreject to refuse.") ;
    }
	
	@Override
    public boolean canCommandSenderUseCommand(ICommandSender sender) {
		return hasPermission(sender, "necessities.tpa", false, false) ;
    } // public boolean canCommandSenderUseCommand(...)

	/**
	 * Return whether the specified command parameter index is a username
	 * parameter.
	 */
	public boolean isUsernameIndex(int par1) {
		return par1 == 0;
	}
	

} // public class CommandTPA
