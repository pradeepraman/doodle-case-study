package doodle.example

import doodle.backend.Canvas
import doodle.core.{Line, Color, Stroke}

sealed trait Image {

  def on(that: Image): Image =
    ???

  def beside(that: Image): Image =
    ???

  def above(that: Image): Image =
    ???

  def draw(canvas: Canvas): Unit = {
    this match {
      case Circle(r) =>
        canvas.circle(0.0, 0.0, r)
        canvas.setStroke(Stroke(1.0, Color.darkBlue, Line.Cap.Round, Line.Join.Round))
        canvas.stroke()
      case Rectangle(h, w) => canvas.rectangle(-w/2, h/2, w/2, -h/2)
    }
  }

}

final case class Circle(radius: Double) extends Image
final case class Rectangle(width: Double, height: Double) extends Image
