package lco.scraper.services;


import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomNode;
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
        System.out.println("Processing page : " + pageUrl);
        String title = "";
        String description = "";
        String image = "";
        String url = pageUrl;

        DomNode titleNode = page.querySelector("meta[property='og:title']");
        DomNode descNode = page.querySelector("meta[property='og:description']");
        DomNode imgNode = page.querySelector("meta[property='og:image']");
        DomNode urlNode = page.querySelector("meta[property='og:url']");

        if(titleNode != null) {
            title = titleNode.getAttributes().getNamedItem("content").getTextContent().replace("\u00a0","");
            System.out.println("og:title : " + title);
        }
        if(descNode != null) {
            description = descNode.getAttributes().getNamedItem("content").getTextContent().replace("\u00a0","");
            System.out.println("og:description : " + description);
        }

        if(imgNode != null) {
            image = imgNode.getAttributes().getNamedItem("content").getTextContent();
            System.out.println("og:image : " + image);
        }
        if(urlNode != null) {
            url = urlNode.getAttributes().getNamedItem("content").getTextContent();
            System.out.println("og:url : " + url);
        }

        return new Meta(id, title, description, url, image);
    }
}
