package org.vaadin.addon.leaflet.client;

import com.google.gwt.core.client.JsArray;
import com.google.gwt.json.client.JSONParser;
import com.vaadin.shared.ui.Connect;

import org.peimari.gleaflet.client.AbstractPath;
import org.peimari.gleaflet.client.Layer;
import org.peimari.gleaflet.client.Polyline;
import org.peimari.gleaflet.client.PolylineOptions;
import org.vaadin.addon.leaflet.shared.LeafletPolylineDecoratedState;

@SuppressWarnings("serial")
@Connect(org.vaadin.addon.leaflet.LPolylineDecorated.class)
public class LeafletPolylineDecoratedConnector
		extends AbstractLeafletVectorConnector<LeafletPolylineDecoratedState,PolylineOptions> {

	protected AbstractPath pattern;

	@Override
	protected void update() {
		if (pattern != null) {
			removeLayerFromParent();
		}
		if (getState().geometryjson == null) {
			return;
		}

		Polyline line = createVector(createOptions());
		pattern = createPattern(line);
		addToParent(pattern);
	}

	@Override
	public LeafletPolylineDecoratedState getState() {
		return (LeafletPolylineDecoratedState)super.getState();
	}

	@Override
	public Layer getLayer() {
		return pattern;
	}

	protected Polyline createVector(PolylineOptions options) {
		return Polyline.createWithArray(getCoordinatesArray(), options);
	}

	protected JsArray<JsArray> getCoordinatesArray() {
		return JSONParser.parseStrict(getState().geometryjson).isArray().getJavaScriptObject().cast();
	}


	protected AbstractPath createPattern(Polyline polyline) {
		return PolylineDecorated.createPattern(polyline);
	}


}
