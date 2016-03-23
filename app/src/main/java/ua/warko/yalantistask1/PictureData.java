package ua.warko.yalantistask1;

/**
 * Created by Warko on 20.03.2016.
 */
public class PictureData {
    String image; //[Comment] Wrong visibility modifier. Anyway you don't need this class. Use just list of strings

    public PictureData(String image) {
        this.image = image;
    }

    public String getImage() {
        return image;
    }
}
