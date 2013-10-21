/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.dineth.dip.arttista.artista.core;

import java.io.File;

/**
 *
 * @author dewmal
 */
public class ImageFile{
    
    private File imageFile;

    public ImageFile() {
    }
    

    public ImageFile(File imageFile) {
        this.imageFile = imageFile;
    }

    public File getImageFile() {
        return imageFile;
    }

    public void setImageFile(File imageFile) {
        this.imageFile = imageFile;
    }
    
    
    

    @Override
    public String toString() {
        return imageFile.getName();
    }
    
    
    
    
}
