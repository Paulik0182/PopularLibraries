package com.paulik.popularlibraries.automator

import android.content.Context
import android.content.Intent
import android.view.KeyEvent
import android.view.View
import android.widget.AutoCompleteTextView
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.NoMatchingViewException
import androidx.test.espresso.ViewAssertion
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.pressKey
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SdkSuppress
import androidx.test.platform.app.InstrumentationRegistry.getInstrumentation
import androidx.test.uiautomator.By
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.Until
import com.paulik.popularlibraries.R
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.core.AllOf.allOf
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

private const val TIMEOUT = 5_000L

@RunWith(AndroidJUnit4::class)
@SdkSuppress(minSdkVersion = 18)
class BehaviorTest {

    //Класс UiDevice предоставляет доступ к устройству.
    //Именно через UiDevice можно управлять устройством, открывать приложения
    //и находить нужные элементы на экране
    private val uiDevice: UiDevice = UiDevice.getInstance(getInstrumentation())

    //Контекст понадобится для запуска нужных экранов и получения packageName
    private val context = ApplicationProvider.getApplicationContext<Context>()

    //Путь к классам приложения, которые тестируются
    private val packageName = context.packageName

    @Before
    fun setup() {
        //Сворачиваем приложения, если что-то запущено
        uiDevice.pressHome()

        //Запускаем приложение
        val intent = context.packageManager.getLaunchIntentForPackage(packageName)
        //проверили Интент на null
        Assert.assertNotNull(uiDevice)
        intent!!.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)//Чистим бэкстек от запущенных ранее Активити
        context.startActivity(intent)

        //Ждем, когда приложение откроется на смартфоне чтобы начать тестировать его элементы
        uiDevice.wait(Until.hasObject(By.pkg(packageName).depth(0)), TIMEOUT)

        // Нажимаем на кнопку "пользователя gitHub"
        onView(withId(R.id.users_git_hub_button)).perform(ViewActions.click())
    }

    //Убеждаемся, что приложение открыто. Для этого достаточно найти на экране любой элемент
    //и проверить его на null
    @Test
    fun test_StartingFragmentIsStarted() {
        //Через uiDevice находим editText
        val editText = uiDevice.findObject(By.res(packageName, "action_search"))
        //Проверяем на null
        Assert.assertNotNull(editText)
    }

    internal class RecyclerViewItemCountAssertion(private val expectedCount: Int) : ViewAssertion {
        override fun check(view: View, noViewFoundException: NoMatchingViewException?) {
            if (noViewFoundException != null) {
                throw noViewFoundException
            }
            val recyclerView = view as RecyclerView
            val adapter = recyclerView.adapter
            assertThat(adapter!!.itemCount, `is`(expectedCount))
        }
    }

    //Убеждаемся, что поиск работает как ожидается
    @Test
    fun testEspresso_SearchIsPositive() {
        onView(withId(R.id.action_search)).perform(ViewActions.click())

        onView(isAssignableFrom(AutoCompleteTextView::class.java))
            .perform(typeText("mojombo"))

        onView(
            allOf(
                withId(R.id.action_search),
                isDisplayed()
            )
        ).perform(pressKey(KeyEvent.KEYCODE_ENTER))  // starting the object  search

        Thread.sleep(3_000) // ожидание в течение 3 секунд
        onView(withId(R.id.recycler_view)).check(RecyclerViewItemCountAssertion(2))
    }

    @Test
    fun testAutomator_SearchIsPositive() {
        val autoCompleteTextView = uiDevice.findObject(By.res(packageName, "action_search"))
        autoCompleteTextView.click()

        val inputField = uiDevice.findObject(By.clazz("android.widget.EditText"))
        inputField.text = "mojombo"

        uiDevice.pressEnter()

        uiDevice.wait(Until.hasObject(By.res(packageName, "recycler_view")), TIMEOUT)
        val recyclerView = uiDevice.findObject(By.res(packageName, "recycler_view"))
        assertThat(recyclerView.childCount, `is`(2))
    }
}
