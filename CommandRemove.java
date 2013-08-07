package kdx7214.necessities;

import java.util.Iterator;
import java.util.List;

import cpw.mods.fml.common.Loader;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;

public class CommandRemove extends CommandBaseNecessities {

	public CommandRemove() {
	}

	@Override
    public String getCommandName()
    {
        return "remove";
    }

	@Override
    public String getCommandUsage(ICommandSender par1ICommandSender)
    {
    	return "/" + getCommandName() + " <radius>" ;
    }

	@Override
    public void processCommand(ICommandSender sender, String[] var2)
    {
    	assert(sender instanceof EntityPlayer || sender instanceof EntityPlayerMP) ;
    	EntityPlayer player = getCommandSenderAsPlayer(sender) ;
    	
    	if (var2.length != 1) {
    		player.addChatMessage(getCommandUsage(sender)) ;
    		return ;
    	}
    	
    	double r = Double.parseDouble(var2[0]) ;
    	r *= r ;
    	List mobs = player.worldObj.loadedEntityList ;
    	Iterator it = mobs.iterator() ;
    	int count = 0 ;

    	while (it.hasNext()) {
    		Object t = it.next() ;
    		Entity u = (Entity)t ;
    		double x = player.posX - u.posX ;
    		double y = player.posY - u.posY ;
    		double z = player.posZ - u.posZ ;
    		double dist = x*x + y*y + z*z ;
    		
    		if (dist < r) {
    			if (t instanceof EntityItem) {
    				u.setDead() ;
    				count++ ;
    			}    			
    		} // if (dist < r)
    		
    	} // while (it.hasNext())
    	player.addChatMessage("" + count + " items removed.") ;

    }
	
	@Override
    public boolean canCommandSenderUseCommand(ICommandSender sender) {
		return hasPermission(sender, "necessities.remove", true, false) ;
    } // public boolean canCommandSenderUseCommand(...)

} // public class CommandRemove
