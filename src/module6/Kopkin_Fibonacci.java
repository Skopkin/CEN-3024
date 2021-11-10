// Author Name: Sam Kopkin
// Date: 10/10/21
// Program Name: Kopkin_Fibonacci
// Purpose: Test runtime of a Fibonacci sequence with iterative vs recursive

package module6;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

public class Kopkin_Fibonacci extends Application{
	static int recursiveCounter = 0;
	static int iterations = 12;
	static long startTime;
	
	@SuppressWarnings("unchecked")
	public void start (Stage stage) {
		stage.setTitle("Fibonacci Efficiency Test");
		final NumberAxis xAxis = new NumberAxis();
		final NumberAxis yAxis = new NumberAxis();
		xAxis.setLabel("Nth Element");
		yAxis.setLabel("Time in Nanoseconds");
		
		LineChart<Number, Number> linechart = new LineChart<Number, Number>(xAxis, yAxis);
		XYChart.Series<Number, Number> recursiveSeries = new XYChart.Series<Number, Number>();
        recursiveSeries.setName("Recursive");
        
        startTime = System.nanoTime();
        for (int i = recursiveCounter; i < iterations; i++) {
        	RecursiveFibonacci(i);
        	recursiveSeries.getData().add(new XYChart.Data<Number, Number>(i, System.nanoTime() - startTime));
        }

        
        XYChart.Series<Number, Number> iterativeSeries= new XYChart.Series<Number, Number>();
        iterativeSeries.setName("Iterative");
        startTime = System.nanoTime();
        IterativeFibonacci(iterativeSeries, 0, 1);     
        
        Scene scene = new Scene(linechart, 800, 600);
        
        linechart.getData().addAll(recursiveSeries, iterativeSeries);
        stage.setScene(scene);
        stage.show();
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);

	}
	
	private static int RecursiveFibonacci(int n) {
		
		if (n <= 1) { 
			return n;
		}
		
		return RecursiveFibonacci(n-1) + RecursiveFibonacci(n-2);
		
	}
	
	private static void IterativeFibonacci(XYChart.Series<Number, Number> series, int first, int second) {
		//startTime = System.nanoTime();
		
		for (int i = 2; i < iterations; i++) {
			int next = first + second;
			series.getData().add(new XYChart.Data<Number, Number>(i, System.nanoTime() - startTime));
			//System.out.println(next);
			
			first = second;
			second = next;
		}
	}

}
