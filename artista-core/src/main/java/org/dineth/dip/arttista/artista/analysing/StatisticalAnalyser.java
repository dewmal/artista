/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.dineth.dip.arttista.artista.analysing;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.math.stat.descriptive.DescriptiveStatistics;
import org.dineth.dip.arttista.artista.db.entity.Feature;

/**
 *
 * @author dewmalpc
 */
public class StatisticalAnalyser {
    private static final Logger LOG = Logger.getLogger(StatisticalAnalyser.class.getName());
    
    
    
    
    public Feature buildFeatureObject(DescriptiveStatistics summary,String name){
        double geometricMean = summary.getGeometricMean();
        double kurtosis = summary.getKurtosis();
        double max = summary.getMax();
        double mean = summary.getMean();
        double min = summary.getMin();
        double skewness = summary.getSkewness();
        double standardDeviation = summary.getStandardDeviation();
        double sum = summary.getSum();
        double sumsq = summary.getSumsq();
        double variance = summary.getVariance();
        double[] values = summary.getValues();
        
        
        Feature feature=new Feature(name, name, null, mean, variance, skewness);
        
        
        
       LOG.log(Level.INFO, summary.toString());
       
        
        return feature;        
    }
    
}
