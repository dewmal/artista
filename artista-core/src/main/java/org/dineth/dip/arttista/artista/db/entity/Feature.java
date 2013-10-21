/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.dineth.dip.arttista.artista.db.entity;

import java.util.List;

/**
 *
 * @author dewmalpc
 */
public class Feature {

    private String id;
    private String description;
    private List<Double> values;
    private double mean;
    private double variance;
    private double skewness;

    public Feature() {
    }

    public Feature(String id, String description, List<Double> values, double mean, double variance, double skewness) {
        this.id = id;
        this.description = description;
        this.values = values;
        this.mean = mean;
        this.variance = variance;
        this.skewness = skewness;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Double> getValues() {
        return values;
    }

    public void setValues(List<Double> values) {
        this.values = values;
    }

    public double getMean() {
        return mean;
    }

    public void setMean(double mean) {
        this.mean = mean;
    }

    public double getVariance() {
        return variance;
    }

    public void setVariance(double variance) {
        this.variance = variance;
    }

    public double getSkewness() {
        return skewness;
    }

    public void setSkewness(double skewness) {
        this.skewness = skewness;
    }
    
    
}
