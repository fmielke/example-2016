package de.htwberlin.mae.slackapi;

import org.springframework.stereotype.Component;

import net.gpedro.integrations.slack.SlackApi;
import net.gpedro.integrations.slack.SlackMessage;

@Component
public class SlackWebhookImpl implements SlackWebhook{

	private SlackApi api = new SlackApi("https://hooks.slack.com/services/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");
	private SlackMessage message;
	
	/*	
	private SlackApi createSlackApi() {
		SlackApi api = new SlackApi("https://hooks.slack.com/services/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");
		return api;
	}
*/
	@Override
	public void createSlackMessage(String icon, String username, String text) {
		SlackMessage slackMessage = new SlackMessage();
		slackMessage.setIcon(icon);
		slackMessage.setUsername(username);
		slackMessage.setText(text);
		this.message = slackMessage;
	}
	
	@Override
	public void sendRestLimitHook() {
		this.api.call(this.message);
	}

	

}
