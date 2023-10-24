import { useState } from 'react'
import { Ingredient, type, unit } from '../types'
import { createDrink } from '../api/drinks'
import uuid from 'react-uuid'

const NewDrink = () => {
  const [name, setName] = useState("UDEFINERT")
  const [ingredientList, setIngredientList] = useState<Ingredient[]>([])
  const INIT_VALUES = {
    name: '',
    alcoholPercentage: 0,
    amount: 0,
    unit: unit.cl,
    type: type.alcohol,
  }
  const [ingredient, setIngredient] = useState<Ingredient>(INIT_VALUES)

  const handleAddIngredient = () => {
    setIngredientList((prev) => [...prev, ingredient])
    setIngredient(INIT_VALUES)
  }

  // DOES NOT WORK
  const calculateAlcohol = ():number => {
    return ingredientList.map(ingredient => (ingredient.alcoholPercentage || 0) * ingredient.amount).reduce((a, b) => a + b) / ingredientList.map(i => i.amount).reduce((a, b) => a+ b)
  }

  const handleAddDrink = () => {
    createDrink({
      id: uuid(),
      name,
      ingredients: ingredientList,
      alcoholContent: calculateAlcohol()
    })
  }

  return (
    <>
      <h2>Create a new drink</h2>
      <div>
        <input type="text" placeholder="Ex. Cosmopolitan" onChange={e => setName(e.target.value)} />
        <div>
          {ingredientList.map((ing) => (
            <p>{ing.name}</p>
          ))}
          <div>
            <input
              type="text"
              placeholder="Ex. Vodka"
              value={ingredient.name}
              onChange={(e) =>
                setIngredient((prev) => ({
                  ...prev,
                  name: e.target.value,
                }))
              }
            />
            <input
              type="number"
              placeholder="Ex. 40"
              onChange={(e) =>
                setIngredient((prev) => ({
                  ...prev,
                  alcoholPercentage: parseInt(e.target.value),
                }))
              }
            />
            <input
              type="number"
              placeholder="Ex. 2"
              onChange={(e) =>
                setIngredient((prev) => ({
                  ...prev,
                  amount: parseInt(e.target.value),
                }))
              }
            />
            <select
              defaultValue={unit.cl}
              onChange={(e) =>
                setIngredient((prev) => ({
                  ...prev,
                  unit: e.target.value,
                }))
              }
            >
              {(Object.keys(unit) as (keyof typeof unit)[]).map((key) => (
                <option value={key}>{key}</option>
              ))}
            </select>
            <select
              defaultValue={type.alcohol}
              onChange={(e) =>
                setIngredient((prev) => ({
                  ...prev,
                  type: e.target.value,
                }))
              }
            >
              {(Object.keys(type) as (keyof typeof type)[]).map((key) => (
                <option value={key}>{key}</option>
              ))}
            </select>
            <button onClick={handleAddIngredient}>Add ingredient</button>
          </div>
        </div>
        <button onClick={handleAddDrink}>Create new drink</button>
      </div>
    </>
  )
}

export default NewDrink
