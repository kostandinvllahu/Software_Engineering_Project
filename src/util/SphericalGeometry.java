package util;

import org.openstreetmap.gui.jmapviewer.interfaces.ICoordinate;

/**
 * Spherical Geometry Utilities
 */
public class SphericalGeometry {
    private static final int RADIUS = 6371000;   // radius of earth in metres

    /**
     * Find distance in metres between two lat/lon points
     *
     * @param point1  first point
     * @param point2  second point
     * @return distance between p1 and p2 in metres
     */
    public static double distanceBetween(ICoordinate point1, ICoordinate point2) {
        double latitude1 = point1.getLat() / 180.0 * Math.PI;
        double latitude2 = point2.getLat() / 180.0 * Math.PI;
        double deltaLongitude = (point2.getLon() - point1.getLon()) / 180.0 * Math.PI;
        double deltaLatitude = (point2.getLat() - point1.getLat()) / 180.0 * Math.PI;

        double coordinateCalculation = Math.sin(deltaLatitude / 2.0) * Math.sin(deltaLatitude / 2.0)
                + Math.cos(latitude1) * Math.cos(latitude2)
                * Math.sin(deltaLongitude / 2.0) * Math.sin(deltaLongitude / 2.0);
        double totalCalculation = 2.0 * Math.atan2(Math.sqrt(coordinateCalculation), Math.sqrt(1 - coordinateCalculation));

        return totalCalculation * RADIUS;
    }
}
