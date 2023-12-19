package model;

public class Restaurant extends Property{
    private String RestName;
    private String price;
    private String location;


    private String cuisine;
    private String timings;

    public String getName() {
        return RestName;
    }

    public void setName(String RestName) {
        this.RestName = RestName;
    }

    public String getImgSrc() {
        return imageLink;
    }

    public void setImgSrc(String imageLink) {
        this.imageLink = imageLink;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getHref() {
        return dataHref1;
    }

    public void setHref(String dataHref1) {
        this.dataHref1 = dataHref1;
    }

    public String getCuisine() {
        return cuisine;
    }

    public void setCuisine(String cuisine) {
        this.cuisine = cuisine;
    }


    public String getTimings() {
        return timings;
    }

    public void setTimings(String timings) {
        this.timings = timings;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

}
