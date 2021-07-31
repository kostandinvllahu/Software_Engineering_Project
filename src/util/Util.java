package util;

import org.openstreetmap.gui.jmapviewer.Coordinate;
import twitter4j.GeoLocation;
import twitter4j.Status;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;

/**
 * Helpful methods that don't clearly fit anywhere else.
 */
public class Util {
    public static GeoLocation statusLocation(Status status) {
        GeoLocation bottomRight = status.getPlace().getBoundingBoxCoordinates()[0][0];
        GeoLocation topLeft = status.getPlace().getBoundingBoxCoordinates()[0][2];
        double newLatitude = (bottomRight.getLatitude() + topLeft.getLatitude())/2;
        double newLongitude = (bottomRight.getLongitude() + topLeft.getLongitude())/2;
        return new GeoLocation(newLatitude, newLongitude);
    }

    public static Coordinate GeoLocationToCoordinate(GeoLocation location) {
        return new Coordinate(location.getLatitude(), location.getLongitude());
    }

    public static Coordinate statusCoordinate(Status status) {
        GeoLocation bottomRight = status.getPlace().getBoundingBoxCoordinates()[0][0];
        GeoLocation topLeft = status.getPlace().getBoundingBoxCoordinates()[0][2];
        double newLatitude = (bottomRight.getLatitude() + topLeft.getLatitude())/2;
        double newLongitude = (bottomRight.getLongitude() + topLeft.getLongitude())/2;
        return new Coordinate(newLatitude, newLongitude);
    }

    public static BufferedImage defaultImage = imageFromURL("http://png-2.findicons.com/files/icons/1995/web_application/48/smiley.png");
    public static BufferedImage imageFromURL(String url) {
    	
    	  try {
              BufferedImage img = ImageIO.read(new URL(url));
              if (img == null) 
            	  {
            	  return defaultImage;
            	  }else 
            	  {
            	 return img;  
            	  }
              
          } catch (IOException ioException) {
              return defaultImage;
          }
      }
        /*try {
            BufferedImage img = ImageIO.read(new URL(url));
            if (img == null) 
            {
            	return imageFromURL("http://png-2.findicons.com/files/icons/1995/web_application/48/smiley.png");//defaultImage;
            }
            else
            {
            	return img;
            }
            	
            
        } 
        catch (IOException e) {
            return defaultImage;
        }
    }*/
}
