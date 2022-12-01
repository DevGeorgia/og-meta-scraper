package lco.scraper.services;


import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import lco.scraper.entity.Meta;

import java.io.IOException;

public class Scraper {

    public static HtmlPage initPage(String pageUrl) throws IOException {
        //initialize a headless browser
        WebClient webClient = new WebClient(BrowserVersion.CHROME);

        //configuring options
        webClient.getOptions().setUseInsecureSSL(true);
        webClient.getOptions().setCssEnabled(false);
        webClient.getOptions().setJavaScriptEnabled(false);
        webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
        webClient.getOptions().setThrowExceptionOnScriptError(false);

        //fetching the web page
        return webClient.getPage(pageUrl);
    }

    public static Meta scrapPage(String id, String pageUrl) throws IOException {

        HtmlPage page = initPage(pageUrl);

        String title = page.querySelector("meta[property='og:title']").getAttributes().getNamedItem("content").getTextContent().replace("\u00a0","");
        String description = page.querySelector("meta[property='og:description']").getAttributes().getNamedItem("content").getTextContent().replace("\u00a0","");
        String image = page.querySelector("meta[property='og:image']").getAttributes().getNamedItem("content").getTextContent();
        String url = page.querySelector("meta[property='og:url']").getAttributes().getNamedItem("content").getTextContent();

        return new Meta(id, title, description, url, image);
    }
}
