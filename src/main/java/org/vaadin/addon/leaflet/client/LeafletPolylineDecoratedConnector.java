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
@Connect(org.vaadin.addon.leaflet.PolylineDecoration.class)
public class LeafletPolylineDecoratedConnector
		extends AbstractLeafletVectorConnector<LeafletPolylineDecoratedState, PolylineDecoratedOptions> {

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

		PolylineDecoratedOptions o = createOptions();
		AbstractPath line = createVector(o);
		pattern = createPattern(line , o);
		addToParent(pattern);
	}

	@Override
	public Layer getLayer() {
		return pattern;
	}

	@Override
	protected PolylineDecoratedOptions createOptions() {
		PolylineDecoratedOptions options = super.createOptions();
		options.setColor(getState().color);
		return options;
	}

	protected AbstractPath createPattern(AbstractPath polyline, PolylineDecoratedOptions o) {
		return PolylineDecorated.createPattern(polyline, o);
	}

	@Override
	public void setParent(ServerConnector parent) {
		super.setParent(parent);
		markDirty();
	}

	protected AbstractPath createVector(PolylineDecoratedOptions options) {
		return Polyline.createWithArray(getCoordinatesArray(), options);
	}

	protected JsArray<JsArray> getCoordinatesArray() {
		return JSONParser.parseStrict(getState().geometryjson).isArray().getJavaScriptObject().cast();
	}


}
