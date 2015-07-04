/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parts;

import java.awt.Color;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author Willian
 */
public class GraphicContent {
    ResultSet result;
    
    public void setContent( JPanel panel )
    {
        String sprint[] = null;
        int taskCount[] = null;
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        try{
            Statement st = scrum.SCRUM.conexao.createStatement();
            String sql= "select COUNT(*) from sprint";
            result = st.executeQuery( sql );
            result.next();
            sprint = new String[ result.getInt( 1 ) ];
        } catch( Exception e ) {
            e.printStackTrace();
        }
        
        try{
            Statement st = scrum.SCRUM.conexao.createStatement();
            String sql= "select * from sprint";
            result = st.executeQuery( sql );
            int i = 0;
            while(result.next())
            {
                sprint[i] = result.getString("alias");
                i++;
            }
        } catch( Exception e ) {
            e.printStackTrace();
        }
        
        
        int sprintIDs[] = new int[sprint.length ];
        
        try{
            Statement st = scrum.SCRUM.conexao.createStatement();
            String sql= "select * from sprint";
            result = st.executeQuery( sql );
            int i = 0;
            while(result.next())
            {
                sprintIDs[i] = result.getInt("idsprint");
                i++;
            }
        } catch( Exception e ) {
            e.printStackTrace();
        }
        
        for (int i = 0; i < sprint.length ; i++) {
            try{
                Statement st = scrum.SCRUM.conexao.createStatement();
                String sql= "select COUNT(*) from sprint_tasks  where idsprint  = " + sprintIDs[i];
                result = st.executeQuery( sql );
                while(result.next())
                {
                    dataset.addValue(result.getInt(1), "Tarefas", sprint[i]);
                }
            } catch( Exception e ) {
                e.printStackTrace();
            }
        }
        
        JFreeChart chart = null;
            chart = ChartFactory.createBarChart("Quantidade de tarefas por Sprint",
                    "Sprint", "Tarefas", dataset, PlotOrientation.VERTICAL, false, true, false);
            CategoryPlot p = chart.getCategoryPlot();
            p.setRangeGridlinePaint(Color.black);

        ChartPanel cp = new ChartPanel(chart);
        cp.setBounds(0, 0, panel.getWidth(), panel.getHeight());
        panel.removeAll();
        cp.setVisible(true);
        panel.add(cp);
        panel.revalidate();
        panel.repaint();
    }
    
}
