package org.dineth.dip.arttista.artista.core.enhance.impl;

import org.dineth.dip.arttista.artista.core.enhance.EnhanceMenter;
import org.openimaj.image.Image;
import org.openimaj.image.processing.edges.CannyEdgeDetector;
import java.util.ArrayList;
import java.util.List;
import org.openimaj.image.FImage;
import org.openimaj.image.analysis.algorithm.FloodFill;
import org.openimaj.image.pixel.Pixel;
import org.openimaj.image.processing.convolution.Gaussian2D;
import org.openimaj.image.processing.morphology.Close;
import org.openimaj.image.processing.morphology.Open;
import org.openimaj.image.processing.morphology.StructuringElement;
import org.openimaj.image.processing.threshold.OtsuThreshold;

/**
 * Created with IntelliJ IDEA. User: dewmal Date: 5/12/13 Time: 10:22 PM To
 * change this template use File | Settings | File Templates.
 */
public class LineEnhanceFactory<T extends Image> {

    private T image;
    private List<EnhanceMenter> lineEnhancers;
    private List<T> imgList;

    public LineEnhanceFactory(T image) {
        this.image = image;

        this.lineEnhancers = new ArrayList<EnhanceMenter>();


        addEnhancers();
    }

    private void addEnhancers() {
        final EnhanceMenter normalImageCanny = new EnhanceMenter(image, "Enhance Out Lines (Canney Edge Detector)") {
            @Override
            public Image enhance() {

                int numBins = 255;
                FImage cloneImage = (FImage) image.clone();
                float otsThresh = OtsuThreshold.calculateThreshold(cloneImage, numBins);


                cloneImage.threshold(otsThresh);


                Open open = new Open(StructuringElement.BOX);
                cloneImage.processInplace(open);




                CannyEdgeDetector ced = new CannyEdgeDetector();

                ced.processImage((FImage) cloneImage);


                // Dilate d = new Dilate(StructuringElement.BOX);
                //cloneImage.processInplace(d);



                this.setImage(cloneImage);
                return getImage();
            }
        };
        /**
         *
         * Line Enhance One
         *
         */
        lineEnhancers.add(normalImageCanny);


        final EnhanceMenter inverseImageCanny = new EnhanceMenter(image, "Enhance Out Lines (Susan Edge Detector)") {
            @Override
            public Image enhance() {

                int numBins = 255;
                FImage cloneImage = (FImage) image.clone();



                Gaussian2D gaussian2D = new Gaussian2D(3, 3, 0.5f);

                cloneImage.processInplace(gaussian2D);


                cloneImage = cloneImage.inverse();



                float otsThresh = OtsuThreshold.calculateThreshold(cloneImage, numBins);

                cloneImage.threshold(otsThresh);


                Open open = new Open(StructuringElement.BOX);
                cloneImage.processInplace(open);



                CannyEdgeDetector ced = new CannyEdgeDetector(0.5f);

                ced.processImage(cloneImage);


                this.setImage(cloneImage);
                return getImage();
            }
        };



        lineEnhancers.add(inverseImageCanny);





        final EnhanceMenter emNormalInverseSubImage = new EnhanceMenter(image, null) {
            @Override
            public Image enhance() {

                FImage clonFImage = (FImage) normalImageCanny.enhance().add(inverseImageCanny.enhance());


                Close close = new Close(StructuringElement.BOX);
                clonFImage.processInplace(close);

                Gaussian2D gaussian2D = new Gaussian2D(3, 3, 0.5f);
                clonFImage.processInplace(gaussian2D);
                
                
                
                clonFImage=FloodFill.floodFill(clonFImage, new Pixel(0, 0), 0.5f);
                
                clonFImage=clonFImage.inverse();

                CannyEdgeDetector cannyEdgeDetector = new CannyEdgeDetector();
               cannyEdgeDetector.processImage(clonFImage);





                return clonFImage;


            }
        };

        lineEnhancers.add(emNormalInverseSubImage);

    }

    /**
     * Get Enhanced Images
     *
     * @return
     */
    public List<T> getEnhanceImages() {
        imgList = new ArrayList<T>();

        for (EnhanceMenter<T> enhanceMenter : lineEnhancers) {
            imgList.add(enhanceMenter.enhance());
        }
        return imgList;
    }

    /**
     * Add Customize Enhance Processor
     *
     * @param enhanceMenter
     */
    public void addEnhanceProcess(EnhanceMenter enhanceMenter) {
        this.lineEnhancers.add(enhanceMenter);
    }

    /**
     * Get All Implemented Enhance Processors
     *
     * @return
     */
    public List<EnhanceMenter> getLineEnhancers() {
        return lineEnhancers;
    }
}
