package leagueoflegend.crawl;

import java.util.ArrayList;

public interface IScraper {
    public String getName();
    public String getDescription();
    public String getImageURL();
    public ArrayList<String> getRelatedChamps();
}
