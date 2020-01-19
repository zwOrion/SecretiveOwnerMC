package com.github.zworion.secretiveowner.command;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.WorldType;

import javax.annotation.Nullable;
import java.util.List;

/**
 * @author ZWOrion
 * @version 1.0.0
 * @date 2020/01/18 22:14
 */
public class CommandPosition extends CommandBase {


    /**
     * Return the required permission level for this command.
     */
    @Override
    public int getRequiredPermissionLevel() {
        return 0;
    }

    /**
     * Gets the name of the command
     */
    @Override
    public String getName() {
        return "position";
    }

    /**
     * Gets the usage string for the command.
     *
     * @param sender
     */
    @Override
    public String getUsage(ICommandSender sender) {
        return "commands.position.usage";
    }

    /**
     * Callback for when the command is executed
     *
     * @param server
     * @param sender
     * @param args
     */
    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        if(args.length>1){
            throw new WrongUsageException("commands.position.usage");
        }else {
            EntityPlayerMP entityPlayerMP = args.length > 0 ? CommandBase.getPlayer(server,sender,args[0]):CommandBase.getCommandSenderAsPlayer(sender);
            Vec3d pos = entityPlayerMP.getPositionVector();
            notifyCommandListener(sender, this, "commands.position.success", new Object[]{entityPlayerMP.getName(), pos, WorldType.WORLD_TYPES[entityPlayerMP.world.provider.getDimension()].getName()});
        }
    }

    /**
     * Get a list of options for when the user presses the TAB key
     *
     * @param server
     * @param sender
     * @param args
     * @param targetPos
     */
    @Override
    public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args, @Nullable BlockPos targetPos) {
        if (args.length == 1)
        {
            String[] names = server.getOnlinePlayerNames();
            return CommandBase.getListOfStringsMatchingLastWord(args, names);
        }
        return null;
    }
}
