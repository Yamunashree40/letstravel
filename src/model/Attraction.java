package model;

public class Attraction extends Property{
    private String AttName;
    private String rating;
    private String location;
    private String cardText;

    public String getName() {
        return AttName;
    }

    public void setName(String AttName) {
        this.AttName = AttName;
    }

    public String getImgSrc() {
        return imageLink;
    }

    public void setImgSrc(String imageLink) {
        this.imageLink = imageLink;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
    public String getCardText() {
        return cardText;
    }

    public void setCardText(String cardText) {
        this.cardText = cardText;
    }

    public String getHref() {
        return dataHref1;
    }

    public void setHref(String dataHref1) {
        this.dataHref1 = dataHref1;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

}
