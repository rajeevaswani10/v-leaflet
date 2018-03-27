package org.vaadin.addon.leaflet.client;

import org.peimari.gleaflet.client.AbstractPath;
import org.peimari.gleaflet.client.Polyline;

import com.google.gwt.core.client.JsArray;


public class PolylineDecorated extends AbstractPath {

	protected PolylineDecorated() {}

	public static native AbstractPath createPattern(Polyline polyline)
	/*-{
		return $wnd.L.polylineDecorator(polyline, {
        	patterns: [
            	{offset: '75%', repeat: 0, symbol: $wnd.L.Symbol.arrowHead({pixelSize: 15, polygon: false, pathOptions: {stroke: true}})}
        	]
    	});
	}-*/;

}
