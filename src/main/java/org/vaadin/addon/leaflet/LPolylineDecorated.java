package org.vaadin.addon.leaflet;

import org.vaadin.addon.leaflet.jsonmodels.PointArray;
import org.vaadin.addon.leaflet.polylinedecorated.shared.LeafletPolylineDecoratedState;


@SuppressWarnings("serial")
public class LPolylineDecorated extends LPolyline {


    public LPolylineDecorated(LPolyline line) {
    	setPoints(new PointArray(line.getPoints()));
    }

    @Override
    public void beforeClientResponse(boolean initial) {
        super.beforeClientResponse(initial);
        getState().geometryjson = points.asJson();
    }

    @Override
    protected LeafletPolylineDecoratedState getState() {
        return (LeafletPolylineDecoratedState) super.getState();
    }

}
