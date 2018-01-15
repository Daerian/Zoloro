/*
 * Zoloro Bot for Seppuku Discord Server
 * Author: Daerian (aka Pickzel#4679)
 */
package Zoloro.Zoloro;

import javax.security.auth.login.LoginException;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.exceptions.RateLimitedException;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class App extends ListenerAdapter {

	// MAIN MEATHOD

	public static void main(String[] args)
			throws LoginException, IllegalArgumentException, InterruptedException, RateLimitedException {

		// Initialize Zoloro
		JDA zoloro = new JDABuilder(AccountType.BOT)
				.setToken("NDAxNTcwNDU4NjM5NTMyMDMz.DTxnLg.LtD7sUT-thEtHTlZV69Dg5NmbFM").buildBlocking();
		zoloro.addEventListener(new App()); // just let's it be aware of benjamoon basically
	} // End Main Method.

	
	
	// MESSAGE RECEIVED MEATHOD -- For your own foolery, write any nonsense
	@Override
	public void onMessageReceived(MessageReceivedEvent e) {

		// Obtains properties of the received message
		Message msg = e.getMessage();
		MessageChannel objChannel = e.getChannel();
		User objUser = e.getAuthor();

		// Responds to any user who says "hello"
		if (msg.getContentDisplay().equals("hello")) {
			objChannel.sendMessage("What's up, " + objUser.getAsMention() + "? How's Godfrey's Booty?").queue();
		}
		if (msg.getContentDisplay().equals("iggy")) {
			objChannel.sendMessage("Ahhh, derek's old bitch").queue();
		}
		if (msg.getContentDisplay().equals("derek")) {
			objChannel.sendMessage("Will you be my haram babyyy?").queue();
		}
		if (msg.getContentDisplay().equals("suren")) {
			objChannel.sendMessage("how traingularly vascular!").queue();
		}
		if (msg.getContentDisplay().equals("Zoloro")) {
			objChannel.sendMessage("That's me! Daerian made me, Dae is Bae, right? :yum:").queue();
		}
		if (msg.getContentDisplay().equals("good bot")) {
			objChannel.sendMessage(":yum:").queue();
		}
		

		//////////////////////////////////
		/////// COMMANDS SECTION ////////
		//////////////////////////////////
		
		/*
		 * The commands will be done by searching for "command: !z followed by a command
		 */
		if (msg.getContentDisplay().charAt(0) == 'z') { //Is the string a command?
			String[] cmd_Args = msg.getContentDisplay().substring(1).split(" "); // command arguments
			
			// ROLL A DIE FOR DND AHAHAHAHA :P
			/*
			 * give number of times to roll and the number of sides
			 * example: !z roll 1d8
			 */
			if (cmd_Args[0].equals("roll")) {
				int sides = 6; // number of sides
				int numrolls = 1; // number of 
				
				if (cmd_Args.length > 1) { // Get the information for the roll
					sides = Integer.parseInt(cmd_Args[1].substring(2,3));
					numrolls = Integer.parseInt(cmd_Args[1].substring(0,1));
					
				} // end if
				
				// Loop and roll for each request
				String rolls = (objUser.getAsMention() + " rolls: " + "\n\n");
				int sum = 0;
				for (int i = 0; i < numrolls; i ++) { // roll and add to the string of rolls
					int new_roll = ((int) Math.ceil(Math.random() * sides)); // roll
					sum = sum + new_roll; // add to sum
					rolls = (rolls + new_roll + " "); // string appending
				}
				rolls = (rolls + "\nThe Sum is: " + sum);
				// Final string is done to just output
				objChannel.sendMessage(rolls).queue();
			
			
			} else if (cmd_Args[0].equals("pal")) {
				String strPal = cmd_Args[1];
				boolean blnPal = true;
				for (int i = 0; i < strPal.length(); i++) {
					if (strPal.charAt(i) != strPal.charAt(strPal.length() - 1 - i)) {
						blnPal = false;
					}
				}
				objChannel.sendMessage(strPal + " is a palindrome: " + blnPal).queue();
			}//End if
		}// End if

	} // End Message Received Listener

} // Ends Class