package leagueoflegend.helper;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class CrawlHelper {
    public static int getLastIndexOfAbility(Document championPage){
        String finalAbility = championPage.selectFirst("ol > li:last-child").attr("data-testid");
        return Integer.parseInt(finalAbility.charAt(finalAbility.length() - 1)+"");
    }

    public static String getRaceOrRegion(Document championPage, String getWhat){

        String tagOfContents = "div.pi-item";

        String filterTag = "h3";

        Elements divOfContents = championPage.select(tagOfContents);

        for(Element contentDiv : divOfContents) {

            boolean checkForCorrectDivOfContent = contentDiv.selectFirst(filterTag).text().contains(getWhat);

            if(checkForCorrectDivOfContent)
                return contentDiv.selectFirst("a").attr("title");
        }

        return null;
    }

    public static ArrayList<String> getData(Document page, String getWhat, String tagContainingData) {
        ArrayList<String> getWhats = new ArrayList<>();

        String tagOfContents = "div.pi-item";

        String filterTag = "h3";

        // many elements
//        "li:not(:has(s))"
        // 1 element only
        String divNotContainingLiTag = "div.pi-data-value";

        Elements divOfContents = page.select(tagOfContents);

        for(Element contentDiv : divOfContents) {
            Element tagOfValidation = contentDiv.selectFirst(filterTag);
            boolean checkForCorrectDivOfContent = false;
            if(tagOfValidation != null) checkForCorrectDivOfContent = tagOfValidation.text().contains(getWhat);
            if (checkForCorrectDivOfContent) {

                Elements liTags = contentDiv.select(tagContainingData);

                if (!liTags.isEmpty()) {

                    for (Element contentOfLiTag : liTags) {

                        String data = contentOfLiTag.text();
                        if(!data.isEmpty())
                            getWhats.add(data);
                    }

                    }else
                        getWhats.add(contentDiv.selectFirst(divNotContainingLiTag).text());

                break;
            }
        }
        return  getWhats;
    }

    public static  ArrayList<String> getRelatedChamps(Document page) {
        ArrayList<String> relatedChamps = new ArrayList<>();

        String tagContainingChampions = "div#gallery-1";

        String tagOfChampionData = "div[style='text-align:center'] a";

        Element divOfContent = page.selectFirst(tagContainingChampions);
        if(divOfContent == null) return null;
        Elements aTagsOfChampions = divOfContent.select(tagOfChampionData);

        for(Element champ : aTagsOfChampions) {
            relatedChamps.add(champ.text());
        }

        return relatedChamps;
    }
    public static Element getTagHelper(Document speciesPage, String dataTagCssQuery, String dataTagsCssQuery) {
        boolean checkForNavigationDiv = speciesPage.selectFirst("div[role='navigation']") != null;

        Element dataTag = speciesPage.selectFirst("#mw-content-text > div.mw-parser-output > " + dataTagCssQuery);
//        dl:nth-child(4) + p
        Elements dataTags = speciesPage.select("#mw-content-text > div.mw-parser-output > " + dataTagsCssQuery);
//        dl
        for(Element data : dataTags) {
            boolean checkForNextPTag = data.nextElementSibling().tagName().equals("p");

            Elements nextTag = data.nextElementSiblings();

            boolean checkForNextContentTabel = nextTag.contains(speciesPage.selectFirst("div[role='navigation']"));

            if(checkForNextPTag && checkForNextContentTabel && checkForNavigationDiv) {
                return data.nextElementSibling();
            }
        }
        return dataTag;
    }
    public static Element getDlTag(Document speciesPage) {
        return getTagHelper(speciesPage, "dl:nth-child(4) + p", "dl");
    }
    public static Element getTableTag(Document speciesPage) {
        return getTagHelper(speciesPage, "table[cellspacing='0'] + p", "table[cellspacing='0']");
    }
    public static String getDescriptionWithNoDlAndTableTag(Document speciesPage) {
        String input = speciesPage.selectFirst("div.mw-parser-output").text();
        ArrayList<String> universe = CrawlHelper.getData(speciesPage, "Universe","li:not(:has(s))");
        String startMarker = universe.get(universe.size()-1);
        String endMarker ;
        if(input.contains("Contents")) endMarker = "Contents";
        else endMarker = "Sapient Species Main";
        int startIndex = input.indexOf(startMarker);
        int endIndex = input.indexOf(endMarker);

        if (startIndex != -1 && endIndex != -1 && endIndex > startIndex) {
            String extractedContent = input.substring(startIndex + startMarker.length(), endIndex).trim();
            return extractedContent;
        } else {
            return "Unknown";
        }
    }
}


