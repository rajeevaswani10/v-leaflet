package org.vaadin.addon.leaflet.client;

import com.google.gwt.core.client.JsArray;
import com.google.gwt.json.client.JSONParser;
import com.vaadin.client.ServerConnector;
import com.vaadin.shared.ui.Connect;

import org.peimari.gleaflet.client.AbstractPath;
import org.peimari.gleaflet.client.Layer;
import org.peimari.gleaflet.client.Polyline;
import org.peimari.gleaflet.client.PolylineOptions;
import org.vaadin.addon.leaflet.shared.LeafletPolylineDecoratedState;

@SuppressWarnings("serial")
@Connect(org.vaadin.addon.leaflet.LPolylineDecorated.class)
public class LeafletPolylineDecoratedConnector2
		extends AbstractLeafletVectorConnector<LeafletPolylineDecoratedState, PolylineOptions> {

    static {
        PolylineDecoratorResInjector.ensureInjected();
    }

	protected AbstractPath pattern;

	@Override
	protected void update() {
		if (pattern != null) {
			removeLayerFromParent();
		}
		if (getState().geometryjson == null) {
			return;
		}

		AbstractPath line = createVector(createOptions());
		pattern = createPattern(line);
		addToParent(pattern);
	}

	@Override
	public Layer getLayer() {
		return pattern;
	}

	protected AbstractPath createPattern(AbstractPath polyline) {
		return PolylineDecorated.createPattern(polyline);
	}

	@Override
	public void setParent(ServerConnector parent) {
		super.setParent(parent);
		markDirty();
	}

	protected AbstractPath createVector(PolylineOptions options) {
		return Polyline.createWithArray(getCoordinatesArray(), options);
	}

	protected JsArray<JsArray> getCoordinatesArray() {
		return JSONParser.parseStrict(getState().geometryjson).isArray().getJavaScriptObject().cast();
	}


}
