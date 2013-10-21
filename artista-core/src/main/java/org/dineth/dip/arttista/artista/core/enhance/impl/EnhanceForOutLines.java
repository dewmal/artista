/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.dineth.dip.arttista.artista.core.enhance.impl;

import org.dineth.dip.arttista.artista.core.enhance.EnhanceMenter;
import org.openimaj.image.FImage;
import org.openimaj.image.processing.edges.CannyEdgeDetector;

/**
 * Extract GrayImage For OutLines
 *
 * @author dewmal
 */
public class EnhanceForOutLines extends EnhanceMenter<FImage> {

    public EnhanceForOutLines(FImage image) {
        super(image,"Enhance For Out Lines");
    }

    @Override
    public FImage enhance() {

        FImage originalImage = image.clone();

       /*I
        image.processInplace(new FGaussianConvolve(1f));
        image.processInplace(new Laplacian3x3());
       */
        image.threshold(150f / 255f);
        image.processInplace(new CannyEdgeDetector(1f));


        return getImage();
    }
}
