/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.dineth.dip.arttista.artista.core.feature.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.math.stat.descriptive.DescriptiveStatistics;
import org.dineth.dip.arttista.artista.core.feature.FeatureExtractor;
import org.dineth.dip.arttista.artista.db.entity.Feature;
import org.dineth.dip.arttista.artista.gui.analys.ChartBuilder;
import org.openimaj.image.FImage;
import org.openimaj.image.MBFImage;
import org.openimaj.image.colour.Transforms;
import org.openimaj.image.pixel.statistics.HistogramModel;

/**
 *
 * @author dewmal
 */
public class RGBColourExtractor extends FeatureExtractor {

    public RGBColourExtractor(MBFImage image) {
        super(image);
    }

    @Override
    public List<Feature> extractFeature() {


        Map<String, double[]> values = new HashMap<String, double[]>();

        int id = 0;


        List<FImage> fImages = image.bands;
        fImages.add(Transforms.calculateIntensity(image));



        for (FImage fimage : fImages) {
            
            DescriptiveStatistics statis = new DescriptiveStatistics();
            
            HistogramModel hm = new HistogramModel(255);
            hm.estimateModel(fimage);
            double[] histValues = null;
            histValues = hm.histogram.values;
            for (double d : histValues) {
                statis.addValue(d);
            }

            values.put(id + "", histValues);

            System.out.println(statis.toString());




            



            id++;
        }


        ChartBuilder cb = new ChartBuilder();
        cb.getXYChart(400, 300, values);

        return null;
    }
}
