package org.shadaj

import org.bukkit.plugin.java.JavaPlugin
import org.bukkit.command.CommandSender
import org.bukkit.command.Command
import org.bukkit.entity.Player

class AliaserPlugin extends JavaPlugin {
  var aliasMap = Map[String, String]()
  
  override def onCommand(sender: CommandSender, command: Command, label: String, args: Array[String]): Boolean = {
    val player = sender.asInstanceOf[Player]
    
    player.sendMessage("Recieved a command: " + label)
    if (label == "addalias") {
      player.sendMessage("Adding an alias")
      val key = args(0)
      val value = args(1)
      aliasMap = aliasMap + (key -> value)
    } else if (label == "listaliases") {
      aliasMap.foreach { p =>
        player.sendMessage(p._1 + " -> " + p._2)
      }
    } else if (label == "") {
      val command = aliasMap(args(0))
      player.performCommand(command + " " + args.tail.mkString(" "))
    }
    
    true
  }
}