package lco.scraper.entity;

public class Meta {

    private String id;

    private String title;

    private String description;

    private String url;

    private String image;

    public Meta(String id, String title, String description, String url, String image) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.url = url;
        this.image = image;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
