/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.dineth.dip.arttista.artista.core.filter;

import Jama.Matrix;
import org.openimaj.image.FImage;
import org.openimaj.image.processor.KernelProcessor;

/**
 * 
 * Basic Filter 
 * 
 *
 * @author dewmal
 */
public class BasicFilter implements KernelProcessor<Float, FImage> {

    private final Matrix mFilter;

    public BasicFilter(Matrix mFilter) {
        this.mFilter = mFilter;
    }

    @Override
    public int getKernelHeight() {
        return mFilter.getRowDimension();
    }

    @Override
    public int getKernelWidth() {
        return mFilter.getColumnDimension();
    }

    @Override
    public Float processKernel(FImage patch) {
        float value = 0;

        for (int y = 0; y < getKernelHeight(); y++) {
            for (int x = 0; x < getKernelHeight(); x++) {

                value += mFilter.get(x, y) * patch.getPixel(x, y);


            }
        }

        value = value / (getKernelHeight() * getKernelWidth());

        return value;
    }
}
