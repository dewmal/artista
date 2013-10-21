/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.dineth.dip.arttista.artista.core.chart;

import org.openimaj.image.Image;

/**
 *
 * @author dewmal
 */
public abstract class ChartExtractor<T extends Image> {
    
    protected final T image;

    public ChartExtractor(T image) {
        this.image = image;
    }
    
    

    
    /**
     * 
     * Draw Corresponding Chart for given image
     * 
     *
     */
    public abstract void drawChart();
}
