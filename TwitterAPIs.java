package FirstTwitterApp;
	import twitter4j.*;
	import twitter4j.conf.ConfigurationBuilder;
	import java.io.File;
	import java.util.List;
	

	public class TwitterApp {
		
		public static void main (String[] args) throws TwitterException{
			ConfigurationBuilder cb1 = new ConfigurationBuilder();
				
			cb1.setDebugEnabled(true)
					.setOAuthConsumerKey("")
					.setOAuthConsumerSecret("")
					.setOAuthAccessToken("")
					.setOAuthAccessTokenSecret("");
					
			TwitterFactory tf = new TwitterFactory(cb1.build());
			twitter4j.Twitter twitter = tf.getInstance();
			
	//Getting posts:
	
			List<Status> statuses = twitter.getHomeTimeline();
			for(Status status: statuses){
				System.out.println(status.getUser().getName()+" tweeted "+status.getText());
			}
			
	//sending a direct message:
	
		    	DirectMessage dm = twitter.sendDirectMessage(twitter.getScreenName(), "Testing123");
		    	System.out.println("\""+dm.getText() +"\" was sent to @" + dm.getRecipientScreenName());

	//get followers
        
        IDs id = twitter.getFollowersIDs(twitter.getScreenName(), -1);
        long[] idslist = id.getIDs();
        System.out.println("You are followed by:");
        System.out.println();
        for (long i : idslist) {
            twitter4j.User user = twitter.showUser(i);
            System.out.println(user.getScreenName());
        }
        System.out.println();

	//update profile pic		
	File updatedPic = new File("C:\Users\Avani Bhatnagar\6564985-random-picture.jpg");
        twitter.updateProfileImage(updatedPic);
        System.out.println("Profile picture was updated!");


	//get followers of followers: 
      for (long y : idslist) {
          twitter4j.User user = twitter.showUser(y);
          IDs idofFoF = twitter.getFollowersIDs(user.getScreenName(), -1);
          System.out.println(user.getScreenName()+" is followed by:");
          long[] idofFoFList = idofFoF.getIDs();
          for (long a : idofFoFList) {
              System.out.println(twitter.showUser(a).getScreenName());
          }

      }


      //post a tweer:
      Status tweet = twitter.updateStatus("Testing posting a tweet");
	    	System.out.println("Tweeted: " + tweet.getText());

	   //trending locations:
	    ResponseList<Location> location;
        location = twitter.getAvailableTrends();
        System.out.println("Trending information for the following cities:");
        int count =1;
      
		for (Location l : location) {
            // used Yahoo! earth identifier
            System.out.println(count +". "+ l.getName() + " in "+ l.getCountryName());
            count++;      
        }



	   //display posts:
	    	 ResponseList<Status> tt = twitter.getUserTimeline(twitter.getScreenName());
        for(Status b: tt) {
                //Displaying the tweets 
            System.out.println(twitter.getScreenName() +" posted "+b.getText()+ ". It was retweeted "+b.getRetweetCount()+" times."); 
            //couldnt display time at which it was posted

        }
    }
}


