package kdx7214.necessities;

import java.util.ArrayList;
import java.util.List;

import cpw.mods.fml.common.Loader;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.src.ModLoader;

public class CommandSummon extends CommandBaseNecessities {

	public CommandSummon() {
	}

	@Override
	public String getCommandName() {
		return "tpahere";
	}

	@Override
	public String getCommandUsage(ICommandSender par1ICommandSender) {
		return "/" + getCommandName() + " <player>";
	}

	@Override
	public List getCommandAliases() {
		ArrayList<String> temp = new ArrayList();
		temp.add("summon");
		return temp;
	}

	@Override
	public void processCommand(ICommandSender sender, String[] par2ArrayOfStr) {
		if (par2ArrayOfStr.length != 1) {
			sender.sendChatToPlayer(getCommandUsage(sender));
			return;
		}

		EntityPlayer player = getCommandSenderAsPlayer(sender);
		EntityPlayerMP other = func_82359_c(sender, par2ArrayOfStr[0]);

		NBTTagCompound playerdata = NecessitiesMain.instance.necessities_data
				.getCompoundTag(other.username);
		NecessitiesMain.instance.necessities_data.setCompoundTag(
				other.username, playerdata);
		NBTTagCompound tpa = playerdata.getCompoundTag("[Tpa]");
		playerdata.setCompoundTag("[Tpa]", tpa);
		if (tpa.hasKey("DenyAll")) {
			sender.sendChatToPlayer("Player " + other.username + " has blocked all teleport requests.") ;
			return ;
		}
		tpa.setString("Who", player.username);
		tpa.setDouble("PosX", player.posX);
		tpa.setDouble("PosY", player.posY);
		tpa.setDouble("PosZ", player.posZ);
		tpa.setFloat("Yaw", player.rotationYaw);
		tpa.setFloat("Pitch", player.rotationPitch);
		tpa.setInteger("Dim", player.dimension) ;
		
		other.sendChatToPlayer(player.username
				+ " has requested that you teleport to them.  Use /tpaccept to accept or /tpreject to refuse.");

	} // public void processCommand(...)

	@Override
	public boolean canCommandSenderUseCommand(ICommandSender sender) {
		if (Loader.instance().isModLoaded("MCPermissions")) {
			if (isPlayer(sender)
					&& NecessitiesPermissions.Instance.hasPermission(
							sender.getCommandSenderName(),
							"necessities.tpahere"))
				return true;
		} else if (isOP(sender)) {
			return true;
		} else {
			return false;
		}

		return false;

	} // public boolean canCommandSenderUseCommand(...)

	/**
	 * Return whether the specified command parameter index is a username
	 * parameter.
	 */
	public boolean isUsernameIndex(int par1) {
		return par1 == 0;
	}

} // public class CommandSummon
