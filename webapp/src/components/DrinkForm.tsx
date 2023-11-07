import { useEffect, useState } from 'react'
import { UseMutationResult, useQueryClient } from '@tanstack/react-query'
import { useAutoAnimate } from '@formkit/auto-animate/react'
import { useLocation } from 'react-router-dom'
import uuid from 'react-uuid'
import calculateAlcohol from '../utils/calculateAlcohol'
import { Drink, Ingredient, type, unit } from '../types'
import IngredientList from './IngredientList'
import Input from './Input'

const DrinkForm = ({
  submit,
  INIT_VALUES,
  id,
}: {
  submit: UseMutationResult<Drink, Error, Drink, unknown>
  INIT_VALUES: Omit<Drink, 'id' | 'alcoholContent'>
  id?: string
}) => {
  const [name, setName] = useState(INIT_VALUES.name)
  const [ingredientList, setIngredientList] = useState<Ingredient[]>(
    INIT_VALUES.ingredients
  )
  const [ingredient, setIngredient] = useState<Ingredient>({
    name: '',
    alcoholPercentage: 0,
    amount: 0,
    unit: unit.cl,
    type: type.alcohol,
  })
  const [editIndex, setEditIndex] = useState<number | null>(null)
  const disableAdd = useDisableButton(
    ingredient.name === '' ||
      ingredient.amount === 0 ||
      ingredient.amount === null ||
      isNaN(ingredient.amount)
  )
  const disableCreate = useDisableButton(
    name === '' || ingredientList.length === 0
  )
  const [animationRef] = useAutoAnimate<HTMLDivElement>()
  const queryClient = useQueryClient()
  const location = useLocation()

  const handleAddIngredient = () => {
    setIngredientList((prev) => [...prev, ingredient])
    setIngredient({
      ...ingredient,
      name: '',
      alcoholPercentage: 0,
      amount: 0,
    })
  }

  const handleEditIngredient = () => {
    const copy = [...ingredientList]
    if (editIndex === null) return
    copy[editIndex] = ingredient
    setIngredientList(copy)
    setEditIndex(null)
    setIngredient({
      ...ingredient,
      name: '',
      alcoholPercentage: 0,
      amount: 0,
    })
  }

  const handleSubmit = () => {
    const newDrink = {
      id: id || uuid(), // creates unique ID if not provided
      name: name,
      ingredients: ingredientList,
      alcoholContent: calculateAlcohol(ingredientList),
    }
    submit.mutate(newDrink, {
      onSuccess: () => {
        if (location.pathname.includes('/edit/'))
          queryClient.setQueryData(['drinks'], (prev: Drink[]) =>
            prev.map((drink) => (drink.id === id ? newDrink : drink))
          )
      },
    })
  }

  return (
    <>
      {submit.isError && <p>Error...</p>}
      <div className="new-drink-form">
        <Input
          label="Drink name"
          value={name}
          onChange={(value) => {
            setName(value)
          }}
        />

        <div className="ingredient-box">
          <div className="ingredient-form" ref={animationRef}>
            <h4 style={{ textAlign: 'center' }}>
              {editIndex === null ? 'New' : 'Edit'} ingredient
            </h4>
            <Input
              label="Ingredient name"
              value={ingredient.name}
              onChange={(value) => {
                setIngredient((prev) => ({
                  ...prev,
                  name: value,
                }))
              }}
            />

            <div className="form-input">
              <label htmlFor="type">Type</label>
              <select
                id="type"
                value={ingredient.type}
                onChange={(e) =>
                  setIngredient((prev) => ({
                    ...prev,
                    type: e.target.value,
                  }))
                }
              >
                <option value={type.alcohol} key="alcohol">
                  Alcohol
                </option>
                <option value={type.mixer} key="mixer">
                  Mixer
                </option>
                <option value={type.extras} key="extras">
                  Extras
                </option>
              </select>
            </div>
            {ingredient.type === type.alcohol ? (
              <Input
                type="alcohol"
                label="Alcohol percentage"
                value={ingredient.alcoholPercentage || ''}
                onChange={(value) => {
                  setIngredient((prev) => ({
                    ...prev,
                    alcoholPercentage: parseFloat(value),
                  }))
                }}
              />
            ) : (
              ''
            )}

            <div className="amount-box">
              <Input
                type="number"
                label="Amount"
                value={ingredient.amount || ''}
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
                  value={ingredient.unit}
                  onChange={(e) =>
                    setIngredient((prev) => ({
                      ...prev,
                      unit: e.target.value,
                    }))
                  }
                >
                  <option value={unit.dl}>dl</option>
                  <option value={unit.cl}>cl</option>
                  <option value={unit.ml}>ml</option>
                  {ingredient.type === type.extras && (
                    <option value={unit.g}>g</option>
                  )}
                </select>
              </div>
            </div>

            <button
              className="btn"
              id="add-ingredient-btn"
              disabled={disableAdd}
              onClick={
                editIndex === null ? handleAddIngredient : handleEditIngredient
              }
            >
              {editIndex === null ? 'Add' : 'Update'}
            </button>
          </div>

          <IngredientList
            ingredients={ingredientList}
            setIngredients={setIngredientList}
            setEditIndex={setEditIndex}
            setIngredient={setIngredient}
          />
        </div>

        <button
          className="btn"
          id="create-btn"
          disabled={disableCreate || submit.status === 'pending'}
          onClick={handleSubmit}
        >
          {submit.status === 'pending'
            ? 'Loading...'
            : location.pathname.includes('/edit/')
            ? 'Update drink'
            : 'Create drink'}
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
