package doodle

object Main extends App {
  import doodle.jvm.Java2DCanvas
  import doodle.example._
  import javax.swing.JFrame

  val canvas = Java2DCanvas.canvas
  canvas.panel.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE)

  Circle(10).draw(canvas)
  //Rectangle(10,10).draw(canvas)
}
