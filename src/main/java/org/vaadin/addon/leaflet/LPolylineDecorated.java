package org.vaadin.addon.leaflet;

import org.vaadin.addon.leaflet.PolylineDecoration.DecorationCfg;
import org.vaadin.addon.leaflet.shared.Point;


@SuppressWarnings("serial")
public class LPolylineDecorated extends LLayerGroup {

    public LPolylineDecorated(DecorationCfg cfg, Point... points) {
    	super();

    	LPolyline line = new LPolyline(points);
    	line.setColor(cfg.color);
    	PolylineDecoration decoration = new PolylineDecoration(line,cfg);
    	addComponent(line);
    	addComponent(decoration);
    }

}
