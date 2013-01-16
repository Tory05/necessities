package kdx7214.necessities;

import com.jcraft.jorbis.Block;

import cpw.mods.fml.common.Loader;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class CommandWand extends CommandBaseNecessities {

	public CommandWand() {
	}

	@Override
    public String getCommandName()
    {
        return "wand";
    }

	@Override
    public String getCommandUsage(ICommandSender par1ICommandSender)
    {
    	return "/" + getCommandName() ;
    }

	@Override
    public void processCommand(ICommandSender sender, String[] par2ArrayOfStr)
    {
		if (!(sender instanceof EntityPlayer || sender instanceof EntityPlayerMP))
			return ;

		assert(sender instanceof EntityPlayer) ;
		
		EntityPlayer player = getCommandSenderAsPlayer(sender) ; 
		
		if (player.inventory.hasItem(NecessitiesMain.instance.WorldEditWandItem))
			return ;	// Player already has one

		ItemStack it = new ItemStack(NecessitiesMain.instance.WorldEditWandItem, 1, 0) ;
		player.inventory.addItemStackToInventory(it) ;
		
    }
	
	@Override
    public boolean canCommandSenderUseCommand(ICommandSender sender)
    {
		if (Loader.instance().isModLoaded("MCPermissions")) {
			if (isPlayer(sender) && NecessitiesPermissions.Instance.hasPermission(sender.getCommandSenderName(), "necessities.wand"))	
				return true ;
		} else if (isOP(sender)) {
			return true ;
		}
		
		return false ;
		
    } // public boolean canCommandSenderUseCommand(...)

} // public class CommandWhoAmI
