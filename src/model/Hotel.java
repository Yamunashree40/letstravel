package model;

public class Hotel extends Property{
    private String hotelName;
    private String price;
    private String neighborhood;
    private String rating;

    public String getName() {
        return hotelName;
    }

    public void setName(String hotelName) {
        this.hotelName = hotelName;
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

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public String getHref() {
        return dataHref1;
    }

    public void setHref(String dataHref1) {
        this.dataHref1 = dataHref1;
    }

    public String getRat() {
        return rating;
    }

    public void setRat(String rating) {
        this.rating = rating;
    }


    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

}
