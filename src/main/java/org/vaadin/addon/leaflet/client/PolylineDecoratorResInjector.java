package org.vaadin.addon.leaflet.client;

import com.google.gwt.core.client.GWT;

public class PolylineDecoratorResInjector {

    protected static PolylineDecoratorClientBundle bundle;

    public static void ensureInjected() {
        if (bundle == null) {
            bundle = GWT.create(PolylineDecoratorClientBundle.class);
            PolylineDecoratorResInjector injector = GWT
                    .create(PolylineDecoratorResInjector.class);
            injector.injectResources();
        }
    }

    /**
     * Override this with deferred binding to customize injected stuff
     */
    protected void injectResources() {
        injectScript(bundle.baseScript().getText());
    }

    private static native void injectScript(String script)
    /*-{
        $wnd.eval(script);
    }-*/;

}
