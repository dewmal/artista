/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.dineth.dip.arttista.artista.core.feature.impl;

import java.util.List;
import org.dineth.dip.arttista.artista.core.enhance.EnhanceMenter;
import org.dineth.dip.arttista.artista.core.enhance.impl.LineEnhanceFactory;
import org.dineth.dip.arttista.artista.core.feature.FeatureExtractor;
import org.dineth.dip.arttista.artista.db.entity.Feature;
import org.openimaj.image.DisplayUtilities;
import org.openimaj.image.FImage;
import org.openimaj.image.MBFImage;
import org.openimaj.image.analysis.algorithm.HoughCircles;
import org.openimaj.image.colour.RGBColour;
import org.openimaj.math.geometry.shape.Polygon;

/**
 *
 * @author dewmal
 */
public class CircleExtractor extends FeatureExtractor {

    public CircleExtractor(MBFImage image) {
        super(image);
    }

    /**
     * Extract Circles In Image
     *
     * @return
     */
    @Override
    public List<Feature> extractFeature() {

       /// final JFrame frame = DisplayUtilities.display(image);


        //CannyEdgeDetector ced = new CannyEdgeDetector();
        FImage grayImage = getGrayImage();


        LineEnhanceFactory lineEnhanceFactory = new LineEnhanceFactory(grayImage);


        List<EnhanceMenter<FImage>> enhanceImages = lineEnhanceFactory.getLineEnhancers();


        for (EnhanceMenter<FImage> enhanceMenter : enhanceImages) {
            HoughCircles circles = new HoughCircles(20, 2000, 10, 10);
            FImage enhanceImage = enhanceMenter.enhance();
            

            circles.analyseImage(enhanceImage);

            
            for (HoughCircles.WeightedCircle wc : circles.getBest(400)) {
                Polygon circle = wc.asPolygon();
               // System.out.println(circle.getCOG().getX());
                
                image.drawPolygon(circle, 2, RGBColour.BLUE);

            }
            
            DisplayUtilities.display(image);
            
            
        }











        return null;
    }
}
