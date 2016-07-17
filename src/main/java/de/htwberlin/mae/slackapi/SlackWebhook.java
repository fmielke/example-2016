package de.htwberlin.mae.slackapi;

import net.gpedro.integrations.slack.SlackMessage;

public interface SlackWebhook {
	
	public void sendRestLimitHook();
	
	public void createSlackMessage(String icon, String username, String text);

}
