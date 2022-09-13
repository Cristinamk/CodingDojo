import java.util.Date;

public class AlfredQuotes{
    
    public String basicGreeting() {
        // You do not need to code here, this is an example method
        return "Hello, lovely to see you. How are you?";
    }
    
    public String guestGreeting(String name) {
        String greeting = "Hello," + String.format(name) + ".Lovely to see you!";
        return greeting;
    }
    
    public String dateAnnouncement() {
        Date date = new Date();
        return "It is currently : " + date;
    }
    
    public String respondBeforeAlexis(String conversation) {
        if (conversation.contains("Alexis")){
            String response = "She's really of no help. What can I get for you?";
            return response;
        }
        if (conversation.contains("Alfred")){
            String response ="At your service. As you wish, naturally.";
            return response;
        }else{
            return "Right. And with that I shall retire.";
        }

        }
    }
