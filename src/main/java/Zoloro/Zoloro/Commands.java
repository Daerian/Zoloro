package Zoloro.Zoloro;

import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.guild.GuildMessageDeleteEvent;
import net.dv8tion.jda.core.events.message.guild.GuildMessageUpdateEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

// A class for commands
public class Commands extends ListenerAdapter {
	
	// Guild Event
	public void onGuildMessageUpdateEvent(GuildMessageUpdateEvent ev) {
		MessageChannel channel = ev.getChannel();
		User usr = ev.getAuthor();
		String orig = "The Original was:\n\n" + ev.getMessage().getContentDisplay();
		channel.sendMessage(usr.getAsMention() + " has just updated their message\n" + orig).queue();
	}
	
	public void onGuildMessageDeleteEvent(GuildMessageDeleteEvent ev) {
		MessageChannel channel = ev.getChannel();
		// TODO: FILL
	}
	
}
