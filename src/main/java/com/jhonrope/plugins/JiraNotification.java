package com.jhonrope.plugins;

import net.rcarz.jiraclient.*;
import org.sonar.api.ce.posttask.PostProjectAnalysisTask;
import org.sonar.api.ce.posttask.QualityGate;
import org.sonar.api.config.Settings;
import org.sonar.api.utils.log.Logger;


public class JiraNotification implements PostProjectAnalysisTask {

    private final Settings settings;

    public JiraNotification(Settings settings) {
        this.settings = settings;
    }

    @Override
    public void finished(ProjectAnalysis analysis) {
        try {

            QualityGate gate = analysis.getQualityGate();
            if (gate != null && gate.getStatus() == QualityGate.Status.ERROR) {
                createIssue(getClient());
            }

        } catch (JiraException ex) {
            System.err.println(ex);
        }


    }

    public JiraClient getClient() {
        String endpoint = settings.getString(JiraNotificationPlugin.JIRA_ENDPOINT);
        String username = settings.getString(JiraNotificationPlugin.JIRA_USERNAME);
        String password = settings.getString(JiraNotificationPlugin.JIRA_PASSCODE);

        BasicCredentials cred = new BasicCredentials(username, password);

        return new JiraClient(endpoint, cred);
    }

    public Issue createIssue(JiraClient jira) throws JiraException {
        String project = settings.getString(JiraNotificationPlugin.JIRA_NAME_PROJECT);
        String summary = settings.getString(JiraNotificationPlugin.JIRA_ISSUE_SUMMARY);
        String description = settings.getString(JiraNotificationPlugin.JIRA_ISSUE_DESCRIPTION);

        return jira.createIssue(project, "BUG")
                .field(Field.SUMMARY, summary)
                .field(Field.DESCRIPTION, description)
                .execute();
    }
}
