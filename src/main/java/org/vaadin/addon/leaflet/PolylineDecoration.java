package org.vaadin.addon.leaflet;

import java.util.List;

import org.vaadin.addon.leaflet.jsonmodels.PointArray;
import org.vaadin.addon.leaflet.shared.LeafletPolylineDecoratedState;
import org.vaadin.addon.leaflet.shared.Point;
import org.vaadin.addon.leaflet.util.JTSUtil;

import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.LineString;


@SuppressWarnings("serial")
public class PolylineDecoration extends AbstractLeafletVector {

    protected PointArray points;
    protected DecorationCfg cfg;

    @Override
    public void beforeClientResponse(boolean initial) {
        super.beforeClientResponse(initial);
        getState().geometryjson = points.asJson();
    }

    public PolylineDecoration(LPolyline line) {
    	this(line,new DecorationCfg());
    }

    public PolylineDecoration(LPolyline line, DecorationCfg cfg) {
    	setPointsWithoutRepaint(line.getPoints());
    	this.cfg = cfg;
   		getState().color = cfg.color;
    	markAsDirty();
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

    public static class DecorationCfg {
    	public String color ;

    	public DecorationCfg() {
    		color = "#3388ff";
		}
    }

}
