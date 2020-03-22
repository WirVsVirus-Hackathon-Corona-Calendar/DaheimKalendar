package de.garritfra.daheimkalender.model;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Challenge extends RealmObject {

    @PrimaryKey
    private String id;

    @SerializedName("titel")
    private String title;
    private String body;
    @SerializedName("icon_url")
    private String iconUrl;
    private RealmList<String> tutorialSteps;
    private RealmList<String> resources;

    // TODO: Date/Calendar/long?
    @SerializedName("completion_date")
    private long completionDate;
    @SerializedName("order")
    private double order;
    private boolean isCompleted = false;
    private String imagePath;

    public Challenge() {

    }

    public Challenge(String id) {
        this.id = id;
    }

    public Challenge(String id, String title, String body) {
        this.id = id;
        this.title = title;
        this.body = body;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public double getOrder() {
        return order;
    }

    public void setOrder(double order) { this.order = order; }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public long getCompletionDate() {
        return completionDate;
    }

    public void setCompletionDate(long completionDate) {
        this.completionDate = completionDate;
    }

    public boolean getCompleted() {
        return isCompleted;
    }

    public void setCompleted(Boolean completed) {
        this.isCompleted = completed;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public RealmList<String> getTutorialSteps() {
        return tutorialSteps;
    }

    public void setTutorialSteps(RealmList<String> tutorialSteps) {
        this.tutorialSteps = tutorialSteps;
    }

    public RealmList<String> getResources() {
        return resources;
    }

    public void setResources(RealmList<String> resources) {
        this.resources = resources;
    }
}
