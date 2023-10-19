import { useState } from 'react'
import { Ingredient, type, unit } from '../types'

const NewDrink = () => {
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

  return (
    <>
      <h2>Create a new drink</h2>
      <div>
        <input type="text" placeholder="Ex. Cosmopolitan" />
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
        <button>Create new drink</button>
      </div>
    </>
  )
}

export default NewDrink
