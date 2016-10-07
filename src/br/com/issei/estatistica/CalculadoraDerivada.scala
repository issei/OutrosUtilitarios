class CalculadoraDerivada {
  def calcular(polinomio: List[(Int, Int)]):List[(Int, Int)] = {
    polinomio match {
      case Nil => Nil
      case h :: t =>
        if (h._2 == 0) calcular(t)
        else (h._1 * h._2, h._2 -1) :: calcular(t)
    }
  }
}