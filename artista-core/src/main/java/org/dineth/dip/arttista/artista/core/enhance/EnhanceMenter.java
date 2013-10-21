/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.dineth.dip.arttista.artista.core.enhance;

import org.openimaj.image.Image;

/**
 *
 * @author dewmal
 */
public abstract class EnhanceMenter<T extends Image> {

    protected T image;
    protected final String name;

    public EnhanceMenter(T image, String name) {
        this.image = image;
        this.name = name;
    }

    /**
     *
     * Enhance Image
     *
     * @return
     */
    public abstract T enhance();

    /**
     *
     * Getter Setter For Image
     *
     * @return
     */
    public T getImage() {
        return image;
    }

    public void setImage(T image) {
        this.image = image;
    }

    /**
     * Get enhance Name
     * 
     * @return 
     */
    public String getName() {
        return name;
    }
    
    
}
