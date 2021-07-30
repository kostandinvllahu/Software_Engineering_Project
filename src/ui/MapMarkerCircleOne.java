package ui;

import org.openstreetmap.gui.jmapviewer.Coordinate;
import org.openstreetmap.gui.jmapviewer.Layer;
import org.openstreetmap.gui.jmapviewer.MapMarkerCircle;

import java.awt.*;
import java.awt.image.BufferedImage;

public class MapMarkerCircleOne extends MapMarkerCircle {
    private BufferedImage miniImage;
    private String userProfileImageURL;
    private String tweet;

    public MapMarkerCircleOne(Layer layer, Coordinate coord, Color color, BufferedImage miniImage,
                               String userProfileImageURL, String tweet) {
        super(layer, null, coord, 40.0, STYLE.FIXED, getDefaultStyle());
        this.miniImage = miniImage;
        this.userProfileImageURL = userProfileImageURL;
        this.tweet = tweet;

        setColor(Color.BLACK);
        setBackColor(color);
    }

    // getters
    public String getUserProfileImageURL() {
        return userProfileImageURL;
    }
    public String getTweet() {
        return tweet;
    }

    @Override
    public void paint(Graphics graphics, Point position, int radius) {
        int size = radius;
        int imgSize = radius - 20;
        if (graphics instanceof Graphics2D && this.getBackColor() != null) {
            Graphics2D graphics2D = (Graphics2D)graphics;
            Composite oldComposite = graphics2D.getComposite();
            graphics2D.setComposite(AlphaComposite.getInstance(3));
            graphics2D.setPaint(this.getBackColor());
            graphics.fillOval(position.x - radius, position.y - radius, size, size);
            graphics.drawImage(miniImage, position.x - 30, position.y - 30, imgSize, imgSize,
                    Color.black , null);
            graphics2D.setComposite(oldComposite);
        }
        graphics.setColor(this.getColor());
        graphics.drawOval(position.x - radius, position.y - radius, size, size);

        if (this.getLayer() == null || this.getLayer().isVisibleTexts()) {
            this.paintText(graphics, position);
        }
    }
}
