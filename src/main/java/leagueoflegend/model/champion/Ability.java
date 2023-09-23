package leagueoflegend.model.champion;

public class Ability {
    private String ability;
    private String abilityName;
    private String abilityDescription;
    private String imageURL;
    private String videoURL;

    public Ability(String ability,
                   String abilityName,
                   String abilityDescription,
                   String imageURL,
                   String videoURL) {
        this.ability = ability;
        this.abilityName = abilityName;
        this.abilityDescription = abilityDescription;
        this.imageURL = imageURL;
        this.videoURL = videoURL;
    }

    public Ability(String ability, String abilityName, String abilityDescription, String imageURL) {
        this.ability = ability;
        this.abilityName = abilityName;
        this.abilityDescription = abilityDescription;
        this.imageURL = imageURL;
    }

    public String getAbility() {
        return ability;
    }

    public void setAbility(String ability) {
        this.ability = ability;
    }

    public String getAbilityName() {
        return abilityName;
    }

    public void setAbilityName(String abilityName) {
        this.abilityName = abilityName;
    }

    public String getAbilityDescription() {
        return abilityDescription;
    }

    public void setAbilityDescription(String abilityDescription) {
        this.abilityDescription = abilityDescription;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }
//
    public String getVideoURL() {
        return videoURL;
    }

    public void setVideoURL(String videoURL) {
        this.videoURL = videoURL;
    }
}
