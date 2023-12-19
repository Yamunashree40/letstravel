package model;

public abstract class Property {
    String imageLink;
    String dataHref1;
    String color;
    public abstract String getColor();

    public abstract void setColor(String color);

    public abstract String getHref();

    public abstract void setHref(String dataHref1) ;

    public abstract String getImgSrc();
    public abstract void setImgSrc(String imageLink);
}
