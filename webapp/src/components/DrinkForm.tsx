import { useEffect, useState } from 'react'
import { Drink, Ingredient, type, unit } from '../types'
import IngredientList from './IngredientList'
import uuid from 'react-uuid'
import { UseMutationResult } from '@tanstack/react-query'
import Input from './Input'

const DrinkForm = ({
  submit,
  INIT_VALUES
}: {
  submit: UseMutationResult<Drink, Error, Drink, unknown>,
  INIT_VALUES: Ingredient
}) => {
  const [name, setName] = useState('')
  const [ingredientList, setIngredientList] = useState<Ingredient[]>([])
  const [ingredient, setIngredient] = useState<Ingredient>(INIT_VALUES)

  const disableAdd = useDisableButton(ingredient.name === '' || ingredient.amount === 0 || isNaN(ingredient.amount))
  const disableCreate = useDisableButton(name === '' || ingredientList.length === 0)

    const handleAddIngredient = () => {
    setIngredientList((prev) => [...prev, ingredient])
  }
  
  return (
    <>
      {submit.isError && <p>Error...</p>}
      <div className="new-drink-form">
        <Input
          label="Drink name"
          onChange={(value) => {
            setName(value)
          }}
        />

        <div className="ingredient-box">
          <div className="ingredient-form">
            <h4 style={{ textAlign: 'center' }}>New ingredient</h4>
            <Input
              label="Ingredient name"
              onChange={(value) => {
                setIngredient((prev) => ({
                  ...prev,
                  name: value,
                }))
              }}
            />
            <Input
              type="alcohol"
              label="Alcohol percentage"
              onChange={(value) => {
                setIngredient((prev) => ({
                  ...prev,
                  alcoholPercentage: parseFloat(value),
                }))
              }}
            />
            <div className="amount-box">
              <Input
                type="number"
                label="Amount"
                onChange={(value) => {
                  setIngredient((prev) => ({
                    ...prev,
                    amount: parseFloat(value),
                  }))
                }}
              />
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
                    <option value={key} key={key}>
                      {key}
                    </option>
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
                  <option value={key} key={key}>
                    {key}
                  </option>
                ))}
              </select>
            </div>

            <button
              className="btn"
              id="add-ingredient-btn"
              onClick={handleAddIngredient}
              disabled={disableAdd}
            >
              Add ingredient
            </button>
          </div>

          <IngredientList
            ingredients={ingredientList}
            setIngredients={setIngredientList}
          />
        </div>

        <button
          className="btn"
          id="create-btn"
          onClick={() => {
            submit.mutate({
              id: uuid(),
              name: name,
              ingredients: ingredientList,
              // TODO: calculate alcohol content
              alcoholContent: 20,
            })
          }}
          disabled={disableCreate || submit.status === 'pending'}
        >
          {submit.status === 'pending' ? 'Loading...' : 'Create new drink'}
        </button>
      </div>
    </>
  )
}

const useDisableButton = (condition: boolean) => {
    const [disabled, setDisabled] = useState(true)
    useEffect(() => {
        setDisabled(condition)
    }, [condition])
    return disabled
}

export default DrinkForm
