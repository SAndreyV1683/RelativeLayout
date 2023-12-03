package a.sboev.relativelayouttestapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.RelativeLayout

class MainActivity : AppCompatActivity() {

    private var relativeLayout: RelativeLayout? = null
    private var lastButtonIndex = 0
    private var lastButtonId: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        relativeLayout = findViewById(R.id.relativeLayout)
        findViewById<View>(R.id.addButton)?.setOnClickListener {
            addButton()
        }
    }

    private fun addButton() {
        val newButton = createNewButton(lastButtonIndex, lastButtonId)
        relativeLayout?.addView(newButton)

        ++lastButtonIndex
        lastButtonId = newButton.id
    }

    private fun createNewButton(
        currentButtonIndex: Int,
        previousButtonId: Int
    ): Button {
        val isFirstButton = currentButtonIndex == 0

        return Button(this).also { button ->
            button.text = "Button $lastButtonIndex"
            // идентификатор нам нужен, чтобы привязываться к кнопкам.
            button.id = View.generateViewId()

            button.layoutParams = RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
            ).apply {
                this.marginEnd = 10
                if (isFirstButton) {
                    // Если у нас первая кнопка — привязываем её к родительскому контейнеру
                    this.addRule(RelativeLayout.ALIGN_PARENT_END)
                } else {
                    // Если кнопка не первая — привязываем её к предыдущей кнопке
                    this.addRule(RelativeLayout.START_OF, previousButtonId)
                }
            }
        }
    }
}