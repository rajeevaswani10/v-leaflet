package org.vaadin.addon.leaflet.client;

import org.peimari.gleaflet.client.AbstractPath;
import org.peimari.gleaflet.client.Polyline;
import org.peimari.gleaflet.client.PolylineOptions;

import com.google.gwt.core.client.JsArray;


public class PolylineDecorated extends AbstractPath {

	protected PolylineDecorated() {}

	public static native AbstractPath createPattern(AbstractPath polyline, PolylineDecoratedOptions o)
	/*-{
		console.log("creating arrows -------------------------------------");
		return $wnd.L.polylineDecorator(polyline, {
        	patterns: [
            	{
            		offset: '25%',
            		repeat: 0, symbol: $wnd.L.Symbol.arrowHead({
            									pixelSize: 15,
            									polygon: false,
            									pathOptions: {
            										stroke: true ,
            										color: o.color
            									}
            				})
            	},
            	{
            		offset: '75%',
            		repeat: 0, symbol: $wnd.L.Symbol.arrowHead({
            									pixelSize: 15,
            									polygon: false,
            									pathOptions: {
            										stroke: true ,
            										color: o.color
            									}
            				})
            	}
        	]
    	});
	}-*/;

}
