package ke.co.forth.leaderboard.adapters;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Nicole on 2/16/2016.
 */
public class LearningLeadersModel {
    private String name;
    private Integer hours;
    private String country;
    private String badgeUrl;

    public LearningLeadersModel() {
    }

    public String getName() {
        return name;
    }

    public int getHours() {
        return hours;
    }

    public String getCountry() {
        return country;
    }

    public String getBadgeUrl() {
        return badgeUrl;
    }

}
