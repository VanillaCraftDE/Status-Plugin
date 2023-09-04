package org.vanillacraft.vanillacraftplugin.commands;

import jdk.vm.ci.meta.Local;
import  org.vanillacraft.vanillacraftplugin.VanillaCraftPlugin;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Team;

public class Status implements TabExecutor {
    public Status() {
    }

    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        List<String> completions = new ArrayList();
        if (args.length == 1) {
            completions.add("afk");
            completions.add("recording");
            completions.add("live");
            completions.add("mining");
            completions.add("farming");
            completions.add("rp");
            completions.add("reset");
            completions.add("geilo");
            completions.add("vc5");
            completions.add("ghg");
            completions.add("poggers");
            completions.add("king");
        } else if (args.length == 2) {
            completions.clear();
            completions.add("true");
            completions.add("false");
        }

        return  completions;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player)sender;
            label = label.toLowerCase(Locale.ROOT);
            byte var7;
            if (label.equals("status") && args.length == 1) {
                switch (args[0]) {
                    case "reset":
                        Iterator var8 = VanillaCraftPlugin.teams.iterator();

                        while(var8.hasNext()) {
                            Team t = (Team)var8.next();
                            if (t.hasEntry(player.getName())) {
                                if (t.equals(VanillaCraftPlugin.afk)) {
                                    if (VanillaCraftPlugin.afkLocs.containsKey(player.getName())) {
                                        player.teleport((Location)VanillaCraftPlugin.afkLocs.get(player.getName()));
                                        VanillaCraftPlugin.afkLocs.remove(player.getName());
                                    }

                                    player.setInvisible(false);
                                    player.setCollidable(true);
                                    player.setInvulnerable(false);
                                    VanillaCraftPlugin.afkBar.removePlayer(player);
                                }

                                t.removeEntry(player.getName());
                            }

                            player.setDisplayName(player.getName());
                            player.setPlayerListName(player.getName());
                        }

                        player.sendMessage("§aDein Status wurde zurückgesetzt.");
                        break;
                    case "afk":
                        player.sendMessage("§aBitte gebe mit §ctrue §a/ §cfalse §aan ob du in den sicheren AFK Bereich teleporitert werden möchtest.");
                        break;
                    case "recording":
                        if (VanillaCraftPlugin.afk.hasEntry(player.getName())) {
                            if (VanillaCraftPlugin.afkLocs.containsKey(player.getName())) {
                                player.teleport((Location)VanillaCraftPlugin.afkLocs.get(player.getName()));
                                VanillaCraftPlugin.afkLocs.remove(player.getName());
                            }
                            player.setInvisible(false);
                            player.setCollidable(true);
                            player.setInvulnerable(false);
                            VanillaCraftPlugin.afk.removeEntry(player.getName());
                            player.setDisplayName(player.getName());
                            player.getPlayer().setPlayerListName(player.getName());
                            VanillaCraftPlugin.afkBar.removePlayer(player);
                        }
                        VanillaCraftPlugin.rec.addEntry(player.getName());
                        player.setPlayerListName(VanillaCraftPlugin.sb.getEntryTeam(player.getName()).getPrefix() + player.getName());
                        player.sendMessage("§aDein Status wurde auf §cREC §agesetzt!");
                        break;
                    case "redstone":
                        if (VanillaCraftPlugin.afk.hasEntry(player.getName())) {
                            if (VanillaCraftPlugin.afkLocs.containsKey(player.getName())) {
                                player.teleport((Location)VanillaCraftPlugin.afkLocs.get(player.getName()));
                                VanillaCraftPlugin.afkLocs.remove(player.getName());
                            }
                            player.setInvisible(false);
                            player.setCollidable(true);
                            player.setInvulnerable(false);
                            VanillaCraftPlugin.afk.removeEntry(player.getName());
                            player.setDisplayName(player.getName());
                            player.getPlayer().setPlayerListName(player.getName());
                            VanillaCraftPlugin.afkBar.removePlayer(player);
                        }
                        VanillaCraftPlugin.red.addEntry(player.getName());
                        player.setPlayerListName(VanillaCraftPlugin.sb.getEntryTeam(player.getName()).getPrefix() + player.getName());
                        player.sendMessage("§aDein Status wurde auf §cREDSTONE §agesetzt!");
                        break;
                    case "live":
                        if (VanillaCraftPlugin.afk.hasEntry(player.getName())) {
                            if (VanillaCraftPlugin.afkLocs.containsKey(player.getName())) {
                                player.teleport((Location)VanillaCraftPlugin.afkLocs.get(player.getName()));
                                VanillaCraftPlugin.afkLocs.remove(player.getName());
                            }
                            player.setInvisible(false);
                            player.setCollidable(true);
                            player.setInvulnerable(false);
                            VanillaCraftPlugin.afk.removeEntry(player.getName());
                            player.setDisplayName(player.getName());
                            player.getPlayer().setPlayerListName(player.getName());
                            VanillaCraftPlugin.afkBar.removePlayer(player);
                        }
                        VanillaCraftPlugin.live.addEntry(player.getName());
                        player.setPlayerListName(VanillaCraftPlugin.sb.getEntryTeam(player.getName()).getPrefix() + player.getName());
                        player.sendMessage("§aDein Status wurde auf §cLIVE §agesetzt!");
                        break;
                    case "mining":
                        if (VanillaCraftPlugin.afk.hasEntry(player.getName())) {
                            if (VanillaCraftPlugin.afkLocs.containsKey(player.getName())) {
                                player.teleport((Location)VanillaCraftPlugin.afkLocs.get(player.getName()));
                                VanillaCraftPlugin.afkLocs.remove(player.getName());
                            }
                            player.setInvisible(false);
                            player.setCollidable(true);
                            player.setInvulnerable(false);
                            VanillaCraftPlugin.afk.removeEntry(player.getName());
                            player.setDisplayName(player.getName());
                            player.getPlayer().setPlayerListName(player.getName());
                            VanillaCraftPlugin.afkBar.removePlayer(player);
                        }
                        VanillaCraftPlugin.mine.addEntry(player.getName());
                        player.setPlayerListName(VanillaCraftPlugin.sb.getEntryTeam(player.getName()).getPrefix() + player.getName());
                        player.sendMessage("§aDein Status wurde auf §cMINING §agesetzt!");
                        break;
                    case "farming":
                        if (VanillaCraftPlugin.afk.hasEntry(player.getName())) {
                            if (VanillaCraftPlugin.afkLocs.containsKey(player.getName())) {
                                player.teleport((Location)VanillaCraftPlugin.afkLocs.get(player.getName()));
                                VanillaCraftPlugin.afkLocs.remove(player.getName());
                            }
                            player.setInvisible(false);
                            player.setCollidable(true);
                            player.setInvulnerable(false);
                            VanillaCraftPlugin.afk.removeEntry(player.getName());
                            player.setDisplayName(player.getName());
                            player.getPlayer().setPlayerListName(player.getName());
                            VanillaCraftPlugin.afkBar.removePlayer(player);
                        }
                        VanillaCraftPlugin.farm.addEntry(player.getName());
                        player.setPlayerListName(VanillaCraftPlugin.sb.getEntryTeam(player.getName()).getPrefix() + player.getName());
                        player.sendMessage("§aDein Status wurde auf §cFARMING §agesetzt!");
                        break;
                    case "rp":
                        if (VanillaCraftPlugin.afk.hasEntry(player.getName())) {
                            if (VanillaCraftPlugin.afkLocs.containsKey(player.getName())) {
                                player.teleport((Location)VanillaCraftPlugin.afkLocs.get(player.getName()));
                                VanillaCraftPlugin.afkLocs.remove(player.getName());
                            }
                            player.setInvisible(false);
                            player.setCollidable(true);
                            player.setInvulnerable(false);
                            VanillaCraftPlugin.afk.removeEntry(player.getName());
                            player.setDisplayName(player.getName());
                            player.getPlayer().setPlayerListName(player.getName());
                            VanillaCraftPlugin.afkBar.removePlayer(player);
                        }
                        VanillaCraftPlugin.rp.addEntry(player.getName());
                        player.setPlayerListName(VanillaCraftPlugin.sb.getEntryTeam(player.getName()).getPrefix());
                        player.sendMessage("§aDein Status wurde auf §cRP §agesetzt!");
                        break;
                    case "geilo":
                        if (VanillaCraftPlugin.afk.hasEntry(player.getName())) {
                            if (VanillaCraftPlugin.afkLocs.containsKey(player.getName())) {
                                player.teleport((Location)VanillaCraftPlugin.afkLocs.get(player.getName()));
                                VanillaCraftPlugin.afkLocs.remove(player.getName());
                            }
                            player.setInvisible(false);
                            player.setCollidable(true);
                            player.setInvulnerable(false);
                            VanillaCraftPlugin.afk.removeEntry(player.getName());
                            player.setDisplayName(player.getName());
                            player.getPlayer().setPlayerListName(player.getName());
                            VanillaCraftPlugin.afkBar.removePlayer(player);
                        }
                        VanillaCraftPlugin.geilo.addEntry(player.getName());
                        player.setPlayerListName(VanillaCraftPlugin.sb.getEntryTeam(player.getName()).getPrefix() + player.getName());
                        player.sendMessage("§aDein Status wurde auf §cGEILO §agesetzt!");
                        break;
                    case "vc6":
                        if (VanillaCraftPlugin.afk.hasEntry(player.getName())) {
                            if (VanillaCraftPlugin.afkLocs.containsKey(player.getName())) {
                                player.teleport((Location)VanillaCraftPlugin.afkLocs.get(player.getName()));
                                VanillaCraftPlugin.afkLocs.remove(player.getName());
                            }
                            player.setInvisible(false);
                            player.setCollidable(true);
                            player.setInvulnerable(false);
                            VanillaCraftPlugin.afk.removeEntry(player.getName());
                            player.setDisplayName(player.getName());
                            player.getPlayer().setPlayerListName(player.getName());
                            VanillaCraftPlugin.afkBar.removePlayer(player);
                        }
                        VanillaCraftPlugin.vc6.addEntry(player.getName());
                        player.setPlayerListName(VanillaCraftPlugin.sb.getEntryTeam(player.getName()).getPrefix() + player.getName());
                        player.sendMessage("§aDein Status wurde auf §cVC6 §agesetzt!");
                        break;
                    case "ghg":
                        if (VanillaCraftPlugin.afk.hasEntry(player.getName())) {
                            if (VanillaCraftPlugin.afkLocs.containsKey(player.getName())) {
                                player.teleport((Location)VanillaCraftPlugin.afkLocs.get(player.getName()));
                                VanillaCraftPlugin.afkLocs.remove(player.getName());
                            }
                            player.setInvisible(false);
                            player.setCollidable(true);
                            player.setInvulnerable(false);
                            VanillaCraftPlugin.afk.removeEntry(player.getName());
                            player.setDisplayName(player.getName());
                            player.getPlayer().setPlayerListName(player.getName());
                            VanillaCraftPlugin.afkBar.removePlayer(player);
                        }
                        VanillaCraftPlugin.ghg.addEntry(player.getName());
                        player.setPlayerListName(VanillaCraftPlugin.sb.getEntryTeam(player.getName()).getPrefix() + player.getName());
                        player.sendMessage("§aDein Status wurde auf §cGHG §agesetzt!");
                        break;
                    case "poggers":
                        if (VanillaCraftPlugin.afk.hasEntry(player.getName())) {
                            if (VanillaCraftPlugin.afkLocs.containsKey(player.getName())) {
                                player.teleport((Location)VanillaCraftPlugin.afkLocs.get(player.getName()));
                                VanillaCraftPlugin.afkLocs.remove(player.getName());
                            }
                            player.setInvisible(false);
                            player.setCollidable(true);
                            player.setInvulnerable(false);
                            VanillaCraftPlugin.afk.removeEntry(player.getName());
                            player.setDisplayName(player.getName());
                            player.getPlayer().setPlayerListName(player.getName());
                            VanillaCraftPlugin.afkBar.removePlayer(player);
                        }
                        VanillaCraftPlugin.poggers.addEntry(player.getName());
                        player.setPlayerListName(VanillaCraftPlugin.sb.getEntryTeam(player.getName()).getPrefix() + player.getName());
                        player.sendMessage("§aDein Status wurde auf §cPOGGERS §agesetzt!");
                        break;
                    case "king":
                        if (VanillaCraftPlugin.afk.hasEntry(player.getName())) {
                            if (VanillaCraftPlugin.afkLocs.containsKey(player.getName())) {
                                player.teleport((Location)VanillaCraftPlugin.afkLocs.get(player.getName()));
                                VanillaCraftPlugin.afkLocs.remove(player.getName());
                            }
                            player.setInvisible(false);
                            player.setCollidable(true);
                            player.setInvulnerable(false);
                            VanillaCraftPlugin.afk.removeEntry(player.getName());
                            player.setDisplayName(player.getName());
                            player.getPlayer().setPlayerListName(player.getName());
                            VanillaCraftPlugin.afkBar.removePlayer(player);
                        }
                        VanillaCraftPlugin.king.addEntry(player.getName());
                        player.setPlayerListName(VanillaCraftPlugin.sb.getEntryTeam(player.getName()).getPrefix() + player.getName());
                        player.sendMessage("§aDein Status wurde auf §cKING §agesetzt!");
                        break;
                }

                return  true;
            }

            if (!label.equals("status") || args.length != 0 && args != null) {
                if (label.equals("status") && args.length == 2) {
                    if (args[0].equals("afk")) {
                        switch (args[1]) {
                            case "true":
                                VanillaCraftPlugin.afkLocs.put(player.getName(), player.getLocation());
                                VanillaCraftPlugin.afk.addEntry(player.getName());
                                player.setPlayerListName((VanillaCraftPlugin.sb.getEntryTeam(player.getName()).getPrefix() + player.getName()));
                                player.setDisplayName(VanillaCraftPlugin.sb.getEntryTeam(player.getName()).getPrefix() + player.getName());
                                player.setInvisible(true);
                                player.setCollidable(false);
                                player.setInvulnerable(true);
                                VanillaCraftPlugin.afkBar.addPlayer(player);
                                player.teleport(new Location(player.getWorld(), -3054.5, 207.0, 1303.0));
                                player.sendMessage("§a Dein Status wurde auf §cAFK §agesetzt und du wurdest teleportiert!");
                                break;
                            case "false":
                                VanillaCraftPlugin.afk.addEntry(player.getName());
                                player.setPlayerListName(VanillaCraftPlugin.sb.getEntryTeam(player.getName()).getPrefix() + player.getName());
                                player.setDisplayName(VanillaCraftPlugin.sb.getEntryTeam(player.getName()).getPrefix() + player.getName());
                                player.setInvisible(true);
                                player.setCollidable(false);
                                player.setInvulnerable(true);
                                VanillaCraftPlugin.afkBar.addPlayer(player);
                                player.sendMessage("§aDein Status wurde auf §cAFK §agesetzt!");
                                break;

                        }
                    }
                } else if (label.equals("status") && args.length > 2) {
                    player.sendMessage("You have speicifed to many arugments!");
                }
            } else {
                try {
                    if (VanillaCraftPlugin.sb.getEntryTeam(player.getName()).getPrefix() != null) {
                        player.sendMessage("Dein aktueller Status ist: " + VanillaCraftPlugin.sb.getEntryTeam(player.getName()).getPrefix());

                    } else {
                        player.sendMessage("Du hast aktuell keinen Status");
                    }
                } catch (Exception var10) {
                    player.sendMessage("Du hast aktuell keinen Status");
                }
            }
        } else {
            VanillaCraftPlugin.Log.log(Level.WARNING, "Error 0x001: Registred non-player usage of /status. Check for invalid command block configurations.");
            sender.getServer().broadcastMessage("§cWARNING: VanillaCraft Error 0x001. Please refer to the console for further infromation.");
        }

        return  true;
    }
}
