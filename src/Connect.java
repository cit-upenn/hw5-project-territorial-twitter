package twitter1v1;
import twitter4j.*;
import twitter4j.auth.OAuth2Token;
import twitter4j.conf.ConfigurationBuilder;

public class Connect {

	private static final String CONSUMER_KEY = "MQ64zERRWzcvw3gvvygrheh9B";
	private static final String CONSUMER_SECRET = "iYejHdjC7BbqHl32Xs4YMTKwZBqSOfnlyqcaixB1rP6501inQO";

	/**
	 * Creates Twitter4J ConfigurationBuilder object in application authentication mode.
	 * Passes in consumer key and secret.
	 * Calls Twitter to get application token.
	 * @return token, application token
	 */
	private static OAuth2Token getOAuth2Token() {
		OAuth2Token token = null;
		ConfigurationBuilder cb = new ConfigurationBuilder();

		cb.setApplicationOnlyAuthEnabled(true);
		cb.setOAuthConsumerKey(CONSUMER_KEY);
		cb.setOAuthConsumerSecret(CONSUMER_SECRET);

		try {
			token = new TwitterFactory(cb.build()).getInstance().getOAuth2Token();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error getting OAuth2 token!");
			System.exit(0);
		}

		return token;
	}

	/**
	 * Creates Twitter4J ConfigurationBuilder object in application authentication mode.
	 * Passes in consumer key and secret.
	 * Passes in application token.
	 * Creates Twitter4J Twitter object.
	 * @return twitter, Twitter object
	 */
	public static Twitter getTwitter() {

		OAuth2Token token = getOAuth2Token();
		ConfigurationBuilder cb = new ConfigurationBuilder();

		cb.setApplicationOnlyAuthEnabled(true);
		cb.setOAuthConsumerKey(CONSUMER_KEY);
		cb.setOAuthConsumerSecret(CONSUMER_SECRET);
		cb.setOAuth2TokenType(token.getTokenType());
		cb.setOAuth2AccessToken(token.getAccessToken());

		Twitter twitter = new TwitterFactory(cb.build()).getInstance();
		
		return twitter;
    }
}