import org.jetbrains.compose.web.dom.Br
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.P
import org.jetbrains.compose.web.dom.Span
import org.jetbrains.compose.web.dom.Text
import org.jetbrains.compose.web.renderComposable

fun main() {

    renderComposable(rootElementId = "root") {
        Div {
            Span {
                P(
                    attrs = {
                        style {

                        }
                    }
                ) {
                    Text("Hi")
                }
                Br()
            }
        }
    }
}
