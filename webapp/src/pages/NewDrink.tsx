import { useState } from 'react'
import { Ingredient, type, unit } from '../types'
import { createDrink } from '../api/drinks'
import uuid from 'react-uuid'
import '../styles/NewDrink.css'

const NewDrink = () => {
  const [name, setName] = useState('UDEFINERT')
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
  const calculateAlcohol = (): number => {
    return (
      ingredientList
        .map(
          (ingredient) =>
            (ingredient.alcoholPercentage || 0) * ingredient.amount
        )
        .reduce((a, b) => a + b) /
      ingredientList.map((i) => i.amount).reduce((a, b) => a + b)
    )
  }

  const handleAddDrink = () => {
    createDrink({
      id: uuid(),
      name,
      ingredients: ingredientList,
      alcoholContent: calculateAlcohol(),
    })
  }

  return (
    <div className="new-drink-container">
      <h2>Create a new drink</h2>
      <div className="new-drink-form">
        <div className="form-input">
          <label htmlFor="drink-name" className="floating-label">
            Drink name
          </label>
          <input
            id="drink-name"
            type="text"
            required
            onChange={(e) => setName(e.target.value)}
          />
        </div>

        <div className='ingredient-box'>
          <div style={{ width: "50%", display: "flex", flexDirection: "column", alignItems: "center"}}>
            <h4 style={{textAlign: "center"}}>New ingredient</h4>
            <div className="form-input">
              <label htmlFor="ingredient-name">Ingredient name</label>
              <input
                id="ingredient-name"
                type="text"
                value={ingredient.name}
                onChange={(e) =>
                  setIngredient((prev) => ({
                    ...prev,
                    name: e.target.value,
                  }))
                }
              />
            </div>
            <div className="form-input">
              <label htmlFor="alcohol-percentage">Alcohol percentage</label>
              <input
                id="alcohol-percentage"
                type="number"
                onChange={(e) =>
                  setIngredient((prev) => ({
                    ...prev,
                    alcoholPercentage: parseInt(e.target.value),
                  }))
                }
              />
            </div>
            <div className="amount-box">
              <div className="form-input amount-input">
                <label htmlFor="amount">Amount</label>
                <input
                  id="amount"
                  type="number"
                  onChange={(e) =>
                    setIngredient((prev) => ({
                      ...prev,
                      amount: parseInt(e.target.value),
                    }))
                  }
                />
              </div>
              <div className="form-input amount-select">
                <select
                  id="unit"
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
              </div>
            </div>
            <div className="form-input">
              <label htmlFor="type">Type</label>
              <select
                id="type"
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
            </div>
            <button className='btn' onClick={handleAddIngredient}>Add ingredient</button>
          </div>
          <ul className='ingredients-list'>
            <h4 style={{textAlign: "center"}}>Added ingredients</h4>
            {ingredientList.length === 0 ? (
              <p>Her var det tomt!</p>
            ) : (
              ingredientList.map((ing) => <li>{ing.amount + ing.unit + ' ' + ing.name + ' ' + ing.alcoholPercentage + '% '}</li>)
            )}
          </ul>
        </div>

        <button className='btn' onClick={handleAddDrink}>Create new drink</button>
      </div>
    </div>
  )
}

export default NewDrink
