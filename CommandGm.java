package kdx7214.necessities;

import cpw.mods.fml.common.Loader;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.StatCollector;
import net.minecraft.world.EnumGameType;
import net.minecraft.world.WorldSettings;

public class CommandGm extends CommandBaseNecessities {

	public CommandGm() {
	}

	@Override
    public String getCommandName()
    {
        return "gm";
    }

	@Override
    public String getCommandUsage(ICommandSender par1ICommandSender)
    {
    	return "/" + getCommandName() + "<s/c>" ;
    }

	@Override
    public void processCommand(ICommandSender sender, String[] par2ArrayOfStr)
    {
        if (par2ArrayOfStr.length > 0)
        {
            EnumGameType var3 = this.getGameModeFromCommand(sender, par2ArrayOfStr[0]);
            EntityPlayerMP var4 = getCommandSenderAsPlayer(sender) ; 
            var4.sendGameTypeToPlayer(var3);
            var4.fallDistance = 0.0F;
            String var5 = StatCollector.translateToLocal("gameMode." + var3.getName());

            if (var4 != sender)
            {
                notifyAdmins(sender, 1, "commands.gamemode.success.other", new Object[] {var4.getEntityName(), var5});
            }
            else
            {
                notifyAdmins(sender, 1, "commands.gamemode.success.self", new Object[] {var5});
            }
        }
    
    } // public void processCommand(...)

	
    protected EnumGameType getGameModeFromCommand(ICommandSender par1ICommandSender, String par2Str)
    {
        return !par2Str.equalsIgnoreCase(EnumGameType.SURVIVAL.getName()) && !par2Str.equalsIgnoreCase("s") ? (!par2Str.equalsIgnoreCase(EnumGameType.CREATIVE.getName()) && !par2Str.equalsIgnoreCase("c") ? (!par2Str.equalsIgnoreCase(EnumGameType.ADVENTURE.getName()) && !par2Str.equalsIgnoreCase("a") ? WorldSettings.getGameTypeById(parseIntBounded(par1ICommandSender, par2Str, 0, EnumGameType.values().length - 2)) : EnumGameType.ADVENTURE) : EnumGameType.CREATIVE) : EnumGameType.SURVIVAL;
    }

	
	@Override
    public boolean canCommandSenderUseCommand(ICommandSender sender) {
		return hasPermission(sender, "necessities.gm", true, false) ;
    } // public boolean canCommandSenderUseCommand(...)

} // public class CommandGm
