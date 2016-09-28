package doodle

object Main extends App {
  import doodle.jvm.Java2DCanvas
  import doodle.example._
  import javax.swing.JFrame

  val canvas = Java2DCanvas.canvas
  canvas.panel.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE)


  (
      Circle(10) on
      Circle(20) on
      Circle(30) above
      Rectangle(6, 20) above
        Rectangle(20, 6) above
      Rectangle(80, 25)
   ).draw(canvas)


}
