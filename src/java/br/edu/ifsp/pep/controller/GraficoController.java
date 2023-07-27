package br.edu.ifsp.pep.controller;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import java.util.ArrayList;
import java.util.List;
import org.primefaces.model.charts.ChartData;
import org.primefaces.model.charts.bar.BarChartDataSet;
import org.primefaces.model.charts.bar.BarChartModel;
import org.primefaces.model.charts.bar.BarChartOptions;
import org.primefaces.model.charts.optionconfig.legend.Legend;
import org.primefaces.model.charts.optionconfig.legend.LegendLabel;
import org.primefaces.model.charts.optionconfig.title.Title;


@Named
@RequestScoped
public class GraficoController {
    
    private BarChartModel barModel;
    
    @PostConstruct
    public void init() {
        createBarModel(); 
    }

    private void createBarModel() {
        barModel = new BarChartModel();
        ChartData data = new ChartData();
        
        BarChartDataSet barDataSet = new BarChartDataSet();
        
        BarChartDataSet barDataSetSegundo = new BarChartDataSet();
        
        barDataSet.setLabel("Dias");
        barDataSetSegundo.setLabel("Salario");
        
        List<Number> valor = new ArrayList<>();
        
        valor.add(50);
        valor.add(60);
        valor.add(84);
        valor.add(100);
        valor.add(30);
        
        barDataSet.setData(valor);
        
        barDataSetSegundo.setData(valor);
        
        //cores da barra dos valores
        List<String> bgColor = new ArrayList<>();
        bgColor.add("rgba(255, 99, 132, 0.2)");
        bgColor.add("rgba(255, 159, 64, 0.2)");
        bgColor.add("rgba(255, 205, 86, 0.2)");
        bgColor.add("rgba(75, 192, 192, 0.2)");
        bgColor.add("rgba(54, 162, 235, 0.2)");
        bgColor.add("rgba(153, 102, 255, 0.2)");
        bgColor.add("rgba(201, 203, 207, 0.2)");
        barDataSet.setBackgroundColor(bgColor);

        //cores da borda das barras dos valores
        List<String> borderColor = new ArrayList<>();
        borderColor.add("rgb(255, 99, 132)");
        borderColor.add("rgb(255, 159, 64)");
        borderColor.add("rgb(255, 205, 86)");
        borderColor.add("rgb(75, 192, 192)");
        borderColor.add("rgb(54, 162, 235)");
        borderColor.add("rgb(153, 102, 255)");
        borderColor.add("rgb(201, 203, 207)");

        barDataSet.setBorderColor(borderColor);
        barDataSetSegundo.setBorderColor(borderColor);

        barDataSetSegundo.setBorderColor(borderColor);
        barDataSet.setBorderWidth(1);

        data.addChartDataSet(barDataSet);
        
        data.addChartDataSet(barDataSetSegundo);


        //são o nome de cada campo do grafico (coluna)
        List<String> labels = new ArrayList<>();
        labels.add("January");
        labels.add("February");
        labels.add("March");
        labels.add("April");
        labels.add("May");
        labels.add("June");
        labels.add("July");
        data.setLabels(labels);
        barModel.setData(data);

        BarChartOptions options = new BarChartOptions();

        //adiciona um titulo em cima do grafico
        Title title = new Title();
        title.setDisplay(true);
        title.setText("Bar Chart");
        options.setTitle(title);

        //legend seria o my fist dataset
        Legend legend = new Legend();
        legend.setDisplay(true);
        legend.setPosition("botom");
        LegendLabel legendLabels = new LegendLabel();
        legendLabels.setFontStyle("italic");
        legendLabels.setFontColor("#2980B9");
        legendLabels.setFontSize(16);
        legend.setLabels(legendLabels);
        options.setLegend(legend);

        barModel.setOptions(options);
        
    }

    public BarChartModel getBarModel() {
        return barModel;
    }

    public void setBarModel(BarChartModel barModel) {
        this.barModel = barModel;
    }
    
    
}
