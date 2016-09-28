package doodle.example

import doodle.backend.Canvas
import doodle.core.{Line, Color, Stroke}

sealed trait Image {

  def on(that: Image): Image =
    On(this, that)

  def beside(that: Image): Image =
    Beside(this, that)

  def above(that: Image): Image =
    Above(this, that)

  def draw(canvas: Canvas): Unit =
    draw(canvas, 0.0, 0.0)

  def draw(canvas: Canvas, originX: Double, originY: Double): Unit = {
    this match {
      case Circle(r) =>
        canvas.circle(originX, originY, r)
        canvas.setStroke(Stroke(1.0, Color.darkBlue, Line.Cap.Round, Line.Join.Round))
        canvas.stroke()
      case Rectangle(w, h) =>
        canvas.rectangle(-originX-w/2, originY+h/2, w, h)
        canvas.setStroke(Stroke(1.0, Color.darkBlue, Line.Cap.Round, Line.Join.Round))
        canvas.stroke()
      case On(o, u) =>
        o.draw(canvas, originX, originY)
        u.draw(canvas, originX, originY)
      case Above(a, b) =>
        val box = this.boundingBox
        val aBox = a.boundingBox
        val bBox = b.boundingBox

        val aboveOriginY = originY + box.top - (aBox.height/2)
        val belowOriginY = originY + box.bottom + (bBox.height/2)

        a.draw(canvas, originX, aboveOriginY)
        b.draw(canvas, originX, belowOriginY)
      case Beside(l, r) =>
        val box = this.boundingBox
        val lBox = l.boundingBox
        val rBox = r.boundingBox

        val leftOriginX = originX + box.left + (lBox.width /2 )
        val rightOriginX = originX + box.right - (rBox.width /2 )

        l.draw(canvas, leftOriginX, originY)
        r.draw(canvas, rightOriginX, originY)

    }
  }

  val boundingBox: BoundingBox =
    this match {
      case Circle(r) => new BoundingBox(-r, r, r, -r)
      case Rectangle(w, h) => new BoundingBox(-w/2, h/2, w/2, -h/2)
      case On(o,u) => o.boundingBox on u.boundingBox
      case Above(a, b) => a.boundingBox above b.boundingBox
      case Beside(l,r) => l.boundingBox beside r.boundingBox
    }

}

final case class Circle(radius: Double) extends Image
final case class Rectangle(width: Double, height: Double) extends Image
final case class On(on: Image, under: Image) extends Image
final case class Beside(left: Image, right: Image) extends Image
final case class Above(above: Image, below: Image) extends Image
