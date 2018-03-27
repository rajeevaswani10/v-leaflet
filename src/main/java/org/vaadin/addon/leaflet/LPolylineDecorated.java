package org.vaadin.addon.leaflet;

import org.vaadin.addon.leaflet.jsonmodels.PointArray;
import org.vaadin.addon.leaflet.shared.LeafletPolylineDecoratedState;
import com.vividsolutions.jts.geom.Geometry;

@SuppressWarnings("serial")
public class LPolylineDecorated extends AbstractLeafletLayer {

    private PointArray points;

    public LPolylineDecorated(LPolyline line) {
    	points = new PointArray(line.getPoints());
    	markAsDirty();
    }

    @Override
    protected LeafletPolylineDecoratedState getState() {
        return (LeafletPolylineDecoratedState) super.getState();
    }

    @Override
    public void beforeClientResponse(boolean initial) {
        super.beforeClientResponse(initial);
        getState().geometryjson = points.asJson();
    }

	@Override
	public Geometry getGeometry() {
		// TODO Auto-generated method stub
		return null;
	}

}
