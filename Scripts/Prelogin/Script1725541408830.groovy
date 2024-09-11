import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import com.kms.katalon.core.annotation.Keyword as Keyword
import org.openqa.selenium.Rectangle as Rectangle
import com.kms.katalon.core.annotation.TearDown as TearDown
import com.kms.katalon.core.configuration.RunConfiguration as RunConfiguration
import com.kms.katalon.core.testobject.ObjectRepository as ObjectRepository
import com.kms.katalon.core.webui.driver.DriverFactory as DriverFactory

PreLogin()

def PreLogin() {
    if (Mobile.waitForElementPresent(findTestObject('Prelogin/android.widget.TextView - Lang EN'), 10, FailureHandling.OPTIONAL)
		||Mobile.waitForElementPresent(findTestObject('Prelogin/android.widget.TextView - Next'), 10, FailureHandling.OPTIONAL)) {
        KeywordUtil.markPassed('Prelogin screens display Successfully')

		for (int i=0;i>=2;i++) {
        Mobile.tap(findTestObject('Prelogin/android.widget.TextView - Next'), 5, FailureHandling.OPTIONAL)
		}
    }else{
		KeywordUtil.markFailed('Failed to display Prelogin screens')
	}
}