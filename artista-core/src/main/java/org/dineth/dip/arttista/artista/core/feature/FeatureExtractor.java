/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.dineth.dip.arttista.artista.core.feature;

import java.util.List;
import org.dineth.dip.arttista.artista.analysing.StatisticalAnalyser;
import org.dineth.dip.arttista.artista.db.entity.Feature;
import org.openimaj.image.FImage;
import org.openimaj.image.MBFImage;
import org.openimaj.image.colour.Transforms;

/**
 *
 * @author dewmal
 */
public abstract class FeatureExtractor {

    protected MBFImage image;
    protected StatisticalAnalyser stAnalyser;

    public FeatureExtractor(MBFImage image) {
        this.image = image;
        stAnalyser=new StatisticalAnalyser();
    }

    
    
    
    
    /**
     * 
     * Extract Feature
     * 
     * @return 
     */
    public abstract List<Feature> extractFeature();

    
    /**
     * Convert MBFImage to Gray Scale Image
     * 
     * @return 
     */
     protected  FImage getGrayImage() {
        FImage grayImage = Transforms.calculateIntensity(image);
        return grayImage;
    }
    
    /**
     * Getter Setter for Image
     * 
     * @return 
     */
    public MBFImage getImage() {
        return image;
    }

    public void setImage(MBFImage image) {
        this.image = image;
    }
    
    
    
}
