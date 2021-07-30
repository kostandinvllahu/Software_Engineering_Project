package query;

import org.openstreetmap.gui.jmapviewer.Coordinate;
import twitter4j.Status;
import filters.Filter;
import org.openstreetmap.gui.jmapviewer.JMapViewer;
import org.openstreetmap.gui.jmapviewer.Layer;
import ui.MapMarkerCircleOne;
import util.Util;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.*;

/**
 * A query over the twitter stream.
 * TODO: Task 4: you are to complete this class.
 * SHTIM KODI TE RRJESHTI 78 DHE ME POSHTE DHE IMPLEMENTO OBSERVER
 */
public class Query implements Observer {
    // The map on which to display markers when the query matches
    private final JMapViewer map;
    // Each query has its own "layer" so they can be turned on and off all at once
    private Layer layer;
    // The color of the outside area of the marker
    private final Color color;
    // The string representing the filter for this query
    private final String queryString;
    // The filter parsed from the queryString
    private final Filter filter;
    // The checkBox in the UI corresponding to this query (so we can turn it on and off and delete it)
    private JCheckBox checkBox;

    public Color getColor() {
        return color;
    }
    public String getQueryString() {
        return queryString;
    }
    public Filter getFilter() {
        return filter;
    }
    public Layer getLayer() {
        return layer;
    }
    public JCheckBox getCheckBox() {
        return checkBox;
    }
    public void setCheckBox(JCheckBox checkBox) {
        this.checkBox = checkBox;
    }
    public void setVisible(boolean visible) {
        layer.setVisible(visible);
    }
    public boolean getVisible() { return layer.isVisible(); }

    public Query(String queryString, Color color, JMapViewer map) {
        this.queryString = queryString;
        this.filter = Filter.parse(queryString);
        this.color = color;
        this.layer = new Layer(queryString);
        this.map = map;
    }

    @Override
    public String toString() {
        return "Query: " + queryString;
    }

    /**
     * This query is no longer interesting, so terminate it and remove all traces of its existence.
     *
     * TODO: Implement this method
     * It modiefies the layers and makes the query layer 
     * invisible and sets it with the value of null
     */
    // MODIFIES: this
    // EFFECTS: makes queries layer invisible and set layer to null
    public void terminate() {
    	this.layer.setVisible(false);
    	this.layer = null;
    }
     //SHIKO KETU!
    public void update (Observable observable, Object arg) {
    	Status tweetInfo = (Status) arg;
    	Coordinate coordinate = Util.statusCoordinate(tweetInfo);
    	String tweetText = tweetInfo.getText();
    	String userMiniImageURL = tweetInfo.getUser().getMiniProfileImageURL();
    	String userProfileImageURL = tweetInfo.getUser().getProfileImageURL();
    	BufferedImage userMiniImage = Util.imageFromURL(userMiniImageURL);
    	
    	MapMarkerCircleOne marker = new MapMarkerCircleOne (layer, coordinate, color, userMiniImage, userProfileImageURL, tweetInfo.getText());
    	
    	if(filter.matches(tweetInfo)) {
    		map.addMapMarker(marker);
    		System.out.println(this.toString() + " --- " + tweetText);
    	}
    }
    
}

