/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.dineth.dip.arttista.artista.processors;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

/**
 *
 * @author dewmalpc
 */
public class JobRunner extends Configured implements Tool {
    
    

    @Override
    public int run(String[] strings) throws Exception {
        long currentTimeMillis = System.currentTimeMillis();
        List l=new ArrayList();
        for (int i = 0; i < 10000; i++) {
            l.add(new Object());
        }
        System.out.println("HADOOP ");
        System.out.println(System.currentTimeMillis() - currentTimeMillis);

        return 0;
    }

    public static void main(String[] args) {
        try {      
            ToolRunner.run(new JobRunner(), args);
        } catch (Exception ex) {
            Logger.getLogger(JobRunner.class.getName()).log(Level.SEVERE, null, ex);
        }

        long currentTimeMillis = System.currentTimeMillis();
        List l=new ArrayList();
        for (int i = 0; i < 10000; i++) {
            l.add(new Object());
        }
        System.out.println("Noramla ");
        System.out.println(System.currentTimeMillis() - currentTimeMillis);

    }
}
