/*
 * Zoloro Bot for Seppuku Discord Server
 * Author: Daerian (aka Pickzel#4679)
 */
package Zoloro.Zoloro;

import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.entities.*;
import net.dv8tion.jda.core.events.Event;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.exceptions.RateLimitedException;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.SettableFuture;
import com.wrapper.spotify.Api;
import com.wrapper.spotify.methods.authentication.ClientCredentialsGrantRequest;
import com.wrapper.spotify.models.ClientCredentials;

import javax.security.auth.login.LoginException;

public class App extends ListenerAdapter {
	
	public Commands cmd = new Commands();

	// MAIN MEATHOD

	public static void main(String[] args)
			throws LoginException, IllegalArgumentException, InterruptedException, RateLimitedException {

		// Initialize Zoloro
		JDA zoloro = new JDABuilder(AccountType.BOT)
				.setToken(Constants.TOKEN).buildBlocking();
		zoloro.addEventListener(new App()); // just let's it be aware of benjamoon basically
		
		//Spotify API
		final Api api = Api.builder()
				  .clientId(Constants.SPOTIFYCID)
				  .clientSecret(Constants.STOKEN)
				  .redirectURI(Constants.RedUri)
				  .build();
		
		/* Create a request object. */
		ClientCredentialsGrantRequest request = api.clientCredentialsGrant().build();

		/* Use the request object to make the request, either asynchronously (getAsync) or synchronously (get) */
		SettableFuture<ClientCredentials> responseFuture = request.getAsync();

		/* Add callbacks to handle success and failure */
		Futures.addCallback(responseFuture, new FutureCallback<ClientCredentials>() {
		  public void onSuccess(ClientCredentials clientCredentials) {
		    /* The tokens were retrieved successfully! */
		    System.out.println("Successfully retrieved an access token! " + clientCredentials.getAccessToken());
		    System.out.println("The access token expires in " + clientCredentials.getExpiresIn() + " seconds");
		    
		    /* Set access token on the Api object so that it's used going forward */
		    api.setAccessToken(clientCredentials.getAccessToken());
		    
		    /* Please note that this flow does not return a refresh token.
		   * That's only for the Authorization code flow */
		  }

		  public void onFailure(Throwable throwable) {
		    /* An error occurred while getting the access token. This is probably caused by the client id or
		     * client secret is invalid. */
		  }
		});
		
	} // End Main Method.
	
	
	
	// MESSAGE RECEIVED MEATHOD -- For your own foolery, write any nonsense
	@Override
	public void onMessageReceived(MessageReceivedEvent e) {

		// Obtains properties of the received message
		Message msg = e.getMessage();
		MessageChannel objChannel = e.getChannel();
		User objUser = e.getAuthor();
		String disp = msg.getContentDisplay();

		// Responds to any user who says "hello"
		if (disp.equals("hello")||disp.equals("Hello")) {
			objChannel.sendMessage("What's up, " + objUser.getAsMention() + "? How's Godfrey's Booty?").queue();
		}
		if (disp.equals("Iggy")) {
			objChannel.sendMessage("Ahhh, derek's old bitch").queue();
		}
		if (disp.equals("Derek")) {
			objChannel.sendMessage("Will you be my haram babyyy?").queue();
		}
		if (disp.equals("Suren")) {
			objChannel.sendMessage("how traingularly vascular!").queue();
		}
		if (disp.equals("Zoloro")) {
			objChannel.sendMessage("That's me! Daerian made me, Dae is Bae, right? :yum:").queue();
		}
		if (disp.equals("Godfrey")) {
			objChannel.sendMessage("Fuck you pleb").queue();
		}
		if (disp.equals("Mark")) {
			objChannel.sendMessage("But why?").queue();
		}
		if (disp.equals("Will")) {
			objChannel.sendMessage("Sooooy Booooooy!!").queue();
		}
		if (disp.equals("Kevin!")) {
			objChannel.sendMessage("HUEHUEHUE :joy:").queue();
		}
		if (disp.equals("good bot")) {
			objChannel.sendMessage(":yum:").queue();
		}
		if (disp.equals("Daerian")) {
			objChannel.sendMessage("Playa Playa").queue();
		}

		//////////////////////////////////
		/////// COMMANDS SECTION ////////
		//////////////////////////////////
		
		/*
		 * The commands will be done by searching for "command: !z followed by a command
		 */
		if (msg.getContentDisplay().charAt(0) == '.') { //Is the string a command?
			String[] cmd_Args = msg.getContentDisplay().substring(1).split(" "); // command arguments
			
			// ROLL A DIE FOR DND AHAHAHAHA :P
			/*
			 * give number of times to roll and the number of sides
			 * example: !z roll 1d8
			 */
			if (cmd_Args[0].equals("roll")) {
				Rolls(msg,objChannel,objUser);
			
			}
			if (cmd_Args[0].equals("pal")) {
				Pal(msg,objChannel,objUser);
			}
			
		}// End if

	} // End Message Received Listener
	
	
	// Dice rolling 
	public void Rolls(Message msg, MessageChannel objChannel, User objUser) {
		String[] cmd_Args = msg.getContentDisplay().substring(1).split(" ");
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
	}// End Roll
	
	
	// Palindromes
	public void Pal(Message msg, MessageChannel objChannel, User objUser) {
		String strPal = (msg.getContentDisplay().substring(1).split(" "))[1];
		boolean blnPal = true;
		for (int i = 0; i < strPal.length(); i++) {
			if (strPal.charAt(i) != strPal.charAt(strPal.length() - 1 - i)) {
				blnPal = false;
			}
		} // End for
		objChannel.sendMessage("is " + strPal + " a palindrome?\n" + blnPal).queue();
	} // End Pal
	
	
} // Ends Class