package assignment.Reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportUtils {
	private static ExtentReports extent;

	public synchronized static ExtentReports getInstance() {
        if (extent == null) {
            ExtentSparkReporter spark = new ExtentSparkReporter("test-output/Spark.html");
            spark.config().setReportName("Automation Test Report");
            spark.config().setDocumentTitle("Test Report");
            extent = new ExtentReports();
            extent.attachReporter(spark);
        }
        return extent;
    }

}
