package ke.co.forth.leaderboard.adapters;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Nicole on 2/16/2016.
 */
public class SkillIQLeadersModel {
    private String name;
    private Integer score;
    private String country;
    private String badgeUrl;

    public SkillIQLeadersModel() {
    }

    public String getName() {
        return name;
    }

    public Integer getScore() {
        return score;
    }

    public String getCountry() {
        return country;
    }

    public String getBadgeUrl() {
        return badgeUrl;
    }
}
