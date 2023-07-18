package resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReporterNG {
	
	public static ExtentReports getReporterObject() {
		String path = System.getProperty("user.dir")+"//reports/AutomationReport.html"; 
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		reporter.config().setTheme(Theme.DARK);
		reporter.config().setDocumentTitle("Automation Report");
		reporter.config().setReportName("Automation Test Results");
		
		ExtentReports extent = new ExtentReports();
		extent.attachReporter(reporter);
		
		return extent;
		
	}

}
