package Katze.DroneSimulation.logic;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.JPanel;

import org.jxmapviewer.JXMapKit;
import org.jxmapviewer.JXMapViewer;
import org.jxmapviewer.viewer.DefaultWaypoint;
import org.jxmapviewer.viewer.GeoPosition;
import org.jxmapviewer.viewer.Waypoint;
import org.jxmapviewer.viewer.WaypointPainter;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class DroneMapPanel extends JPanel {
    private JXMapKit mapKit;
    private WaypointPainter<Waypoint> waypointPainter;

    public DroneMapPanel() {
        setLayout(new BorderLayout());
        mapKit = new JXMapKit();
        add(mapKit);

        // Initialize waypointPainter
        waypointPainter = new WaypointPainter<>();
        mapKit.getMainMap().setOverlayPainter(waypointPainter);
    }

    public void setDroneLocation(double latitude, double longitude) {
        GeoPosition dronePosition = new GeoPosition(latitude, longitude);

        // Add a waypoint for the drone's location
        Waypoint droneWaypoint = new DefaultWaypoint(dronePosition);

        // Add the waypoint to the painter
        Set<Waypoint> waypoints = new HashSet<>(waypointPainter.getWaypoints());
        waypoints.add(droneWaypoint);
        waypointPainter.setWaypoints(waypoints);

        // Set the drone's location on the map
        mapKit.setAddressLocation(dronePosition);
        mapKit.getMainMap().setZoom(10);

        // Redraw the map
        mapKit.getMainMap().repaint();
    }

    public void clearDroneRoute() {
        // Clear the waypoints on the map
        waypointPainter.setWaypoints(new HashSet<>());

        // Redraw the map
        mapKit.getMainMap().repaint();
    }

    // Getter for the waypointPainter
    public WaypointPainter<Waypoint> getWaypointPainter() {
        return waypointPainter;
    }
}