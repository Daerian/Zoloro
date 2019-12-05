package Zoloro.Zoloro;

import java.util.List;

import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.Role;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.guild.member.GuildMemberRoleAddEvent;
import net.dv8tion.jda.core.events.guild.member.GuildMemberRoleRemoveEvent;
import net.dv8tion.jda.core.events.message.guild.GuildMessageDeleteEvent;
import net.dv8tion.jda.core.events.message.guild.GuildMessageUpdateEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

// A class for commands
public class Commands extends ListenerAdapter {
	
	// Guild Event
	public void onGuildMessageUpdateEvent(GuildMessageUpdateEvent ev) {
		MessageChannel channel = ev.getChannel(); // Channel in which the message was received. Type = text
		User usr = ev.getAuthor(); // the user who wrote the message that triggered this.
		String orig = "The Original was:\n\n" + ev.getMessage().getContentDisplay();
		// send message to the found channel
		channel.sendMessage(usr.getAsMention() + " has just updated their message\n" + orig).queue();
	}
	
	public void onGuildMessageDeleteEvent(GuildMessageDeleteEvent ev) {
		//MessageChannel channel = ev.getChannel();
		// TODO: FILL
	}
	
	// Send an update to default text channel if a member gets a new role.
	public void onGuildMemberRoleAddEvent(GuildMemberRoleAddEvent eve) {
		 Guild guild = eve.getGuild(); // Guild or Server in which the update happens
		 MessageChannel channel = guild.getDefaultChannel(); // Default text channel for server
		 Member mem = eve.getMember(); // Member of the server who has got a new roll
		 List<Role> roles = eve.getRoles();
		 Role role = roles.get(-1);
		 channel.sendMessage(mem.getAsMention() + " has acheived the roles: '" + role.getName()
		 	+ "'. Congratulation!!").queue();
	}

	// Send an update to default text channel if a member loses a role.
	public void onGuildMemberRoleRemoveEvent(GuildMemberRoleRemoveEvent eve) {
		Guild guild = eve.getGuild(); // Guild or Server in which the update happens
		MessageChannel channel = guild.getDefaultChannel(); // Default text channel for server
		Member mem = eve.getMember(); // Member of the server who has got a new roll
		List<Role> roles = eve.getRoles();
		Role role = roles.get(-1);
		channel.sendMessage(mem.getAsMention() + " has lost the roles: '" + role.getName() + "'. Unfortunate!!")
				.queue();
	}
	
}
