/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.dineth.dip.arttista.artista.core.feature.impl;

import java.util.ArrayList;
import java.util.HashMap;
import org.dineth.dip.arttista.artista.core.enhance.impl.LineEnhanceFactory;
import org.dineth.dip.arttista.artista.core.feature.FeatureExtractor;
import org.openimaj.image.FImage;
import org.openimaj.image.MBFImage;
import org.openimaj.image.analysis.algorithm.HoughLines;
import org.openimaj.math.geometry.line.Line2d;

import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.math.stat.descriptive.DescriptiveStatistics;
import org.dineth.dip.arttista.artista.core.enhance.EnhanceMenter;
import org.dineth.dip.arttista.artista.db.entity.Feature;
import org.dineth.dip.arttista.artista.gui.analys.ChartBuilder;
import org.openimaj.image.DisplayUtilities;

/**
 * @author dewmal
 */
public class LineExtractor extends FeatureExtractor {
    
    private static final Logger LOG = Logger.getLogger(LineExtractor.class.getName());
    
    public LineExtractor(MBFImage image) {
        super(image);
    }
    
    @Override
    public List<Feature> extractFeature() {
        
        List<Feature> features = new ArrayList<>();
        
        
        FImage grayImage = getGrayImage().clone();
        
        
        LineEnhanceFactory lineEnhanceFactory = new LineEnhanceFactory(grayImage);
        
        
        List<EnhanceMenter<FImage>> enhanceImages = lineEnhanceFactory.getLineEnhancers();
        
        
        Map<String, Map<String, Integer>> hisList = new HashMap<>();
        
        
        LOG.log(Level.FINE, "Start Enhance Image Featuring");
        for (EnhanceMenter<FImage> enhancer : enhanceImages) {
            LOG.log(Level.INFO, "Featuring For {0}", enhancer.getName());
            
            final HoughLines hl = new HoughLines(1f);
            final FImage enhanceImage = enhancer.enhance();
            
           // DisplayUtilities.display(enhanceImage);
            
            hl.analyseImage(enhanceImage);
            
            int maxLines = 400;
            
            Map<String, Integer> hangles = new HashMap<>();
           // Map<String, Integer> vangles = new HashMap<>();
            
            DescriptiveStatistics hstats = new DescriptiveStatistics();
           // DescriptiveStatistics vstats = new DescriptiveStatistics();
            
            for (Line2d line : hl.getBestLines(maxLines)) {
                double horizontalAngle = line.calculateHorizontalAngle();
               /// double virticalAngle = line.calculateVerticalAngle();

               // System.out.println(virticalAngle);
               /// System.out.println(horizontalAngle);
                //  Cumalative Data
                Integer counth = hangles.get(horizontalAngle + "");
                
                
                if (counth == null) {
                    counth = 0;
                }
                counth++;
                hangles.put(horizontalAngle + "", counth);


                /*  Culmilate
                Integer countv = vangles.get(virticalAngle + "");
                
                if (countv == null) {
                    countv = 0;
                }
                countv++;
                vangles.put(virticalAngle + "", countv);
                *  vstats.addValue(virticalAngle);
                * */


                //Add Values for Statistical Analysis
                hstats.addValue(horizontalAngle);
               
            }


            /**
             * double values[] = new double[angles.keySet().size()];
             *
             *
             *
             * for (int i = 0; i < values.length; i++) { Integer get =
             * angles.get(angles.keySet().toArray()[i]);
             *
             * values[i] = (double) get / (double) maxLines; }
             *
             */
            hisList.put(enhancer.getName() + "-H", hangles);
           // hisList.put(enhancer.getName() + "-V", vangles);
            
            
            
            /**
             * Create Features and Add them to List for return
             */
          //  Feature featureh = stAnalyser.buildFeatureObject(vstats, enhancer.getName() + "_V");
            Feature featurev = stAnalyser.buildFeatureObject(hstats, enhancer.getName() + "_H");
            
            
            
            features.add(featurev);
           // features.add(featureh);
            
            
            
            hl.clearIterator();
        }
        
        ChartBuilder cb = new ChartBuilder();
        cb.getXYCumChart(800, 600, hisList, 100);
        
        
        
        return features;
    }
}
