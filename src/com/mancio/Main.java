package com.mancio;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener {
	static public boolean login=false;
	static public int tentativi=0;
	static public Location exLoc;
	static public String name="";
	static public boolean first;
	FileConfiguration config = getConfig();
	
	public void onEnable() {
		getLogger().info("Plugin abilitato!");
		this.getServer().getPluginManager().registerEvents(this,this);
		
		if(config.get("Passowrd") == null) {
			config.addDefault("Password", "");
			config.addDefault("First Start", true);
			config.addDefault("X", "0");
			config.addDefault("Y", "0");
			config.addDefault("Z", "0");
			config.addDefault("Spawn Location", null);
			config.options().copyDefaults(true);
			
			saveConfig();
		}
	}
	
	@EventHandler(priority=EventPriority.HIGHEST, ignoreCancelled=true)
	public void onPlayerJoin(PlayerJoinEvent event) {
		Player player=event.getPlayer();
		
		player.sendMessage("§aBenvenuto "+player.getName()+" nel mio server.\n§3Per favore utilizza /login <password> per loggare nel server");
		exLoc=player.getLocation();
		login=false;
		name=player.getName();
		first=(boolean) config.get("First Start");
		
		if(first) {
			player.sendMessage("§cSe sei OP usa il comando /config <password> <x> <y> <z> per configurare il plugin!");
		}
	}
	
	@EventHandler(priority=EventPriority.HIGH, ignoreCancelled=true)
	public void onPlayerMove(PlayerMoveEvent event) {
		Player player=event.getPlayer();
		int parsed[]=new int[3];
		String str[]=new String[3];
		
		if(player.getName().equalsIgnoreCase(name))
			if(!login) {
				
				str[0]=(String) config.get("X");
				str[1]=(String) config.get("Y");
				str[2]=(String) config.get("Z");
				
				for(int i=0;i<3;i++) {
					parsed[i]=Integer.valueOf(str[i]);
				}
				
				Location loc=new Location(Bukkit.getWorld("world"), parsed[0],parsed[1],parsed[2]);
				player.teleport(loc);
			}
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String args[]) {
		
		if(!first) {
			if(cmd.getName().equalsIgnoreCase("login") && sender instanceof Player) {
				Player player = (Player) sender;
				
				if(!login) {
					if(!args[0].equals(config.get("Password"))) {
						tentativi++;
						
						if(tentativi==3)
							player.kickPlayer("§cHai sbagliato la password per 3 volte consecutive");
						
						player.sendMessage("§eHai sbagliato la passowrd!, Riprova");
						login=false;
						
						} else {
							login=true;
							player.sendMessage("§7Password corretta, ora puoi giocare nel server");
							try {
								player.teleport(exLoc);
							} catch(NullPointerException e) {
								player.sendMessage("§7Login corretto ma errore inetrno(NullPointerException) colpa del /reload, §epuoi giocare lo stesso!");
							}
						}
					
					} else {
						player.sendMessage("§cLogin già eseguito!");
					}
				return true;
			}
			
			if(cmd.getName().equalsIgnoreCase("chat") && sender instanceof Player) {
				Player player = (Player) sender;
				
				if(player.isOp()) {
				
					if(args[0].equalsIgnoreCase("giallo") || args[0].equalsIgnoreCase("g")) {
						StringBuffer result=new StringBuffer();
						
						for(int i=1;i<args.length;i++)
							result.append(args[i]+" ");
						
						this.getServer().broadcastMessage("<"+player.getName()+"> "+"§e"+result.toString());
					}
					
					if(args[0].equalsIgnoreCase("blu") || args[0].equalsIgnoreCase("b")) {
						StringBuilder result=new StringBuilder();
						
						for(int i=1;i<args.length;i++)
							result.append(args[i]+" ");
							
						this.getServer().broadcastMessage("<"+player.getName()+"> "+"§1"+result.toString());
					}
					
					if(args[0].equalsIgnoreCase("verde") || args[0].equalsIgnoreCase("a")) {
						StringBuilder result=new StringBuilder();
						
						for(int i=1;i<args.length;i++)
							result.append(args[i]+" ");
							
						this.getServer().broadcastMessage("<"+player.getName()+"> "+"§a"+result.toString());
					}
					
					if(args[0].equalsIgnoreCase("rosso") || args[0].equalsIgnoreCase("r")) {
						StringBuilder result=new StringBuilder();
						
						for(int i=1;i<args.length;i++)
							result.append(args[i]+" ");
							
						this.getServer().broadcastMessage("<"+player.getName()+"> "+"§c"+result.toString());
					}
					
					if(args[0].equalsIgnoreCase("viola") || args[0].equalsIgnoreCase("v")) {
						StringBuilder result=new StringBuilder();
						
						for(int i=1;i<args.length;i++)
							result.append(args[i]+" ");
						
						this.getServer().broadcastMessage("<"+player.getName()+"> "+"§d"+result.toString());
					}
					
					if(args[0].equalsIgnoreCase("grassetto") || args[0].equalsIgnoreCase("grass")) {
						StringBuilder result=new StringBuilder();
						
						for(int i=1;i<args.length;i++)
							result.append(args[i]+" ");
						
						this.getServer().broadcastMessage("<"+player.getName()+"> "+"§l"+result.toString());
					}
					
					if(args[0].equalsIgnoreCase("ciano") || args[0].equalsIgnoreCase("c")) {
						StringBuilder result=new StringBuilder();
						
						for(int i=1;i<args.length;i++)
							result.append(args[i]+" ");
						
						this.getServer().broadcastMessage("<"+player.getName()+"> "+"§b"+result.toString());
					}
					
					if(args[0].equalsIgnoreCase("nero") || args[0].equalsIgnoreCase("n")) {
						StringBuilder result=new StringBuilder();
						
						for(int i=1;i<args.length;i++)
							result.append(args[i]+" ");
						
						this.getServer().broadcastMessage("<"+player.getName()+"> "+"§0"+result.toString());
					}
					
					if(args[0].equalsIgnoreCase("offuscato") || args[0].equalsIgnoreCase("ggwp")) {
						StringBuilder result=new StringBuilder();
						
						for(int i=1;i<args.length;i++)
							result.append(args[i]+" ");
							
						this.getServer().broadcastMessage("<"+player.getName()+"> "+"§k"+result.toString());
					}
					return true;
				} else {
					player.kickPlayer("§aVoleeeeeeviiiiii scrivere colorato ma zebb te lo impedisce!!!\nciaoneee e alla prossima...rientra e sbrigatiiiii\n§kaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
				}
			}
			
			if(cmd.getName().equalsIgnoreCase("cambiapass") && sender instanceof Player) {
				Player player=(Player) sender;
				
				if(player.isOp()) {
					config.set("Password", args[0]);
					saveConfig();
					
					player.sendMessage("§aLa password è stata cambiata con successo!");
				} else {
					player.sendMessage("§cNon puoi utilizzare questo comando.");
				}
				
				return true;
			}
			
			if(cmd.getName().equalsIgnoreCase("mostrapass") && sender instanceof Player) {
				Player player=(Player) sender;
				
				if(player.isOp()) {
					String password=(String) config.get("Password");
					player.sendMessage("§lLa password attuale è "+password);
				}
				return true;
			}
			
			/*if(cmd.getName().equalsIgnoreCase("resetpass") && sender instanceof Player) {
				Player player=(Player) sender;
				
				if(player.isOp()) {
					config.set("Password", config.get("Password");
					saveConfig();
					player.sendMessage("§aLa password è stata ripristinata con successo!");
				}
				return true;
			}*/
			
			if(cmd.getName().equalsIgnoreCase("config")) {
				Player player=(Player) sender;
				
				config.set("Password", args[0]);
				config.set("First Start", false);
				config.set("X", args[1]);
				config.set("Y", args[2]);
				config.set("Z", args[3]);
				config.set("Spawn Location", args[1] + ", " + args[2] + ", " + args[3]);
				
				saveConfig();
				
				player.sendMessage("Plugin configurato correttamente!");
			}
			return true;
			
		} else {
			Player player=(Player) sender;
			
			if(cmd.getName().equalsIgnoreCase("config")) {
				config.set("Password", args[0]);
				config.set("First Start", false);
				config.set("X", args[1]);
				config.set("Y", args[2]);
				config.set("Z", args[3]);
				config.set("Spawn Location", args[1] + ", " + args[2] + ", " + args[3]);
				
				saveConfig();
				
				player.sendMessage("Plugin configurato correttamente!");
				
				return true;
			}
		}
		return false;
	}
	
	public void onDisable() {
		getLogger().info("Plugin disabilitato!");
	}
}
