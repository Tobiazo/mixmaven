import { Ingredient } from './../types'
import toMl from './toMl'

const calculateAlcohol = (ingredientList: Ingredient[]) => {
  let totalAmountMl = 0
  let totalAlcoholMl = 0
  ingredientList.forEach((ingredient) => {
    totalAmountMl += toMl(ingredient.amount, ingredient.unit)
    totalAlcoholMl +=
      toMl(ingredient.amount, ingredient.unit) *
      (ingredient.alcoholPercentage / 100)
  })

  return (totalAlcoholMl / totalAmountMl) * 100
}

export default calculateAlcohol
