package org.vaadin.addon.leaflet;

import java.util.List;

import org.vaadin.addon.leaflet.jsonmodels.PointArray;
import org.vaadin.addon.leaflet.shared.LeafletPolylineDecoratedState;
import org.vaadin.addon.leaflet.shared.Point;
import org.vaadin.addon.leaflet.util.JTSUtil;

import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.LineString;


@SuppressWarnings("serial")
public class LPolylineDecorated extends AbstractLeafletVector {

    protected PointArray points;
    protected LPolyline polyline;

    @Override
    public void beforeClientResponse(boolean initial) {
        super.beforeClientResponse(initial);
        getState().geometryjson = points.asJson();
    }

    public LPolylineDecorated(LPolyline line) {
    	this.polyline = line;
    	setPoints(new PointArray(line.getPoints()));
    }

    public LPolylineDecorated(Point... points) {
        setPoints(points);
    }

    public void setPoints(Point... array) {
        setPointsWithoutRepaint(array);
        markAsDirty();
    }

    public void setPoints(List<Point>  points) {
        this.points = new PointArray(points);
        markAsDirty();
    }

    public void setPointsWithoutRepaint(Point...  points) {
        this.points = new PointArray(points);
    }

    public Point[] getPoints() {
        return points.toArray(new Point[points.size()]);
    }

    @Override
    protected LeafletPolylineDecoratedState getState() {
        return (LeafletPolylineDecoratedState) super.getState();
    }

    @Override
    public Geometry getGeometry() {
        final LineString line = JTSUtil.toLineString( points.toArray(new Point[points.size()]) );
       /* if(line.isSimple() && line.isClosed())
        {
          return JTSUtil.toLinearRing(this);
        }*/
        return line;
    }

    public void setGeometry(LineString lineString) {
        setPoints(JTSUtil.toLeafletPointArray(lineString));
    }

    public void setGeometryWithoutRepaint(LineString lineString) {
        setPointsWithoutRepaint(JTSUtil.toLeafletPointArray(lineString));
    }

    /**
     * Removes all null values from the geometry.
     */
    public void sanitizeGeometry() {
        points.sanitize();
    }

}
