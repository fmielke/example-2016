package de.htwberlin.mae.eventhandler;

import de.htwberlin.mae.model.Nutzer;
import net.gpedro.integrations.slack.SlackApi;
import net.gpedro.integrations.slack.SlackMessage;
import org.springframework.data.rest.core.annotation.HandleAfterCreate;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;

import java.util.UUID;

/**
 * Created by fmielke on 17.07.16.
 */
@RepositoryEventHandler(Nutzer.class)
public class NutzerEventHandler {

    @HandleAfterCreate
    public void handleNutzerAfterCreate(Nutzer nutzer) {
        String name = nutzer.getName();
        UUID id = nutzer.getNutzerId();
        SlackApi api = new SlackApi("https://hooks.slack.com/services/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");
        SlackMessage slackMessage = new SlackMessage();
        slackMessage.setIcon(":ghost:");
        slackMessage.setUsername("user-listener");
        slackMessage.setText("new nutzer '" +name +"' with id '" +id +"' was created.");
        api.call(slackMessage);
    }
}
