package com.jhonrope.plugins;


import org.sonar.api.Plugin;
import org.sonar.api.Properties;
import org.sonar.api.Property;
import org.sonar.api.PropertyType;
import org.sonar.api.batch.postjob.PostJob;


@Properties({
        @Property(
                key = JiraNotificationPlugin.JIRA_ENDPOINT,
                name = "Jira API Endpoint",
                description = "",
                global = true),
        @Property(
                key = JiraNotificationPlugin.JIRA_OAUTH,
                name = "Jira OAuth token",
                description = "Authentication token",
                global = false,
                project = true,
                type = PropertyType.PASSWORD),
        @Property(
                key = JiraNotificationPlugin.JIRA_NAME_PROJECT,
                name = "Jira project",
                description = "Jira name for this project",
                project = true,
                global = false),
        @Property(
                key = JiraNotificationPlugin.JIRA_USERNAME,
                name = "Jira username",
                project = false,
                global = false,
                type = PropertyType.INTEGER),
        @Property(
                key = JiraNotificationPlugin.JIRA_PASSCODE,
                name = "Jira password",
                project = true,
                global = false,
                type = PropertyType.PASSWORD),
        @Property(
                key = JiraNotificationPlugin.JIRA_ISSUE_SUMMARY,
                name = "Jira issue summary",
                description = "Issue summary text for the new issue",
                project = true,
                global = false,
            defaultValue = "Quality Gate failed"),
        @Property(
                key = JiraNotificationPlugin.JIRA_ISSUE_DESCRIPTION,
                name = "Jira issue description",
                description = "Issue description text for the new issue",
                project = true,
                global = false)
})
public class JiraNotificationPlugin implements Plugin{

    public static final String JIRA_ENDPOINT = "sonar.jira.endpoint";
    public static final String JIRA_OAUTH = "sonar.jira.oauth";
    public static final String JIRA_NAME_PROJECT = "sonar.jira.project";
    public static final String JIRA_USERNAME = "sonar.jira.username";
    public static final String JIRA_PASSCODE = "sonar.jira.password";
    public static final String JIRA_ISSUE_SUMMARY = "sonar.jira.issue.summary";
    public static final String JIRA_ISSUE_DESCRIPTION = "sonar.jira.issue.description";


    @Override
    public void define(Context context) {
        context.addExtension(JiraNotification.class);
    }
}
