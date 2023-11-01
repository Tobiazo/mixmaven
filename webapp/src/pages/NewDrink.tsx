import { useEffect, useState } from 'react'
import { useMutation, useQueryClient } from '@tanstack/react-query'
import { useAutoAnimate } from '@formkit/auto-animate/react'
import { createDrink } from '../api/drinks'
import uuid from 'react-uuid'
import { DeleteOutline } from '@mui/icons-material'
import { Drink, Ingredient, type, unit } from '../types'
import '../styles/NewDrink.css'
import { useNavigate } from 'react-router-dom'

const NewDrink = () => {
  const INIT_VALUES = {
    name: '',
    alcoholPercentage: 0,
    amount: 0,
    unit: unit.cl,
    type: type.alcohol,
  }
  const [name, setName] = useState('')
  const [ingredientList, setIngredientList] = useState<Ingredient[]>([])
  const [ingredient, setIngredient] = useState<Ingredient>(INIT_VALUES)
  const [disableAdd, setDisableAdd] = useState(true)
  const [disableCreate, setDisableCreate] = useState(true)

  const queryClient = useQueryClient()
  const navigate = useNavigate()

  const handleAddIngredient = () => {
    setIngredientList((prev) => [...prev, ingredient])
    // setIngredient(INIT_VALUES)
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

  const createDrinkMutation = useMutation({
    mutationKey: ['createDrink'],
    mutationFn: createDrink,
    onSuccess: (data: Drink) => {
      queryClient.setQueryData(['drinks'], (prev: Drink[]) => [...prev, data])
      queryClient.invalidateQueries({ queryKey: ['drinks'], exact: true })
      navigate('/')
    },
  })

  // Disable buttons when inputs are invalid
  useEffect(() => {
    setDisableAdd(
      ingredient.name === '' ||
        ingredient.amount === 0 ||
        isNaN(ingredient.amount)
    )
  }, [ingredient])

  useEffect(() => {
    setDisableCreate(name === '' || ingredientList.length === 0)
  }, [name, ingredientList])

  return (
    <div className="new-drink-container">
      <h2>Create a new drink</h2>
      {createDrinkMutation.isError && <p>Error...</p>}
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
                  alcoholPercentage: parseInt(value),
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
                    amount: parseInt(value),
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
              id='add-ingredient-btn'
              onClick={handleAddIngredient}
              disabled={disableAdd}
            >
              Add ingredient
            </button>
          </div>

          <IngredientList ingredients={ingredientList} />
        </div>

        <button
          className="btn"
          id="create-btn"
          onClick={() => {
            createDrinkMutation.mutate({
              id: uuid(),
              name: name,
              ingredients: ingredientList,
              alcoholContent: calculateAlcohol(),
            })
            // uncommet this if we want to reset values after creating a drink (not neseccary since we navigate away from the page)
            // setIngredientList([])
            // setName('')
            // setIngredient(INIT_VALUES)
          }}
          disabled={disableCreate || createDrinkMutation.status === 'pending'}
        >
          {createDrinkMutation.status === 'pending'
            ? 'Loading...'
            : 'Create new drink'}
        </button>
      </div>
    </div>
  )
}

const Input = ({
  type,
  label,
  onChange,
}: {
  type?: 'text' | 'number' | 'alcohol'
  label: string
  onChange: (e: string) => void
}) => {
  const [value, setValue] = useState('')
  const [isError, setError] = useState(false)
  const [errorMsg, setErrorMsg] = useState('Invalid Field')

  const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    if (e.target.value === '') {
      setError(true)
      setErrorMsg('This field is required')
    } else {
      setError(false)
    }

    const regex = /^\d*\.?\d*$/ // Only numbers and max one dot
    if (
      (type === 'number' || type === 'alcohol') &&
      !regex.test(e.target.value)
    ) {
      setError(true)
      setErrorMsg('This field must be a postive number and max one dot')
      setTimeout(() => {
        setError(false)
      }, 1000)
      return
    }

    if (type === 'alcohol' && parseInt(e.target.value) > 100) {
      setError(true)
      setErrorMsg('This field cannot be greater than 100')
      setTimeout(() => {
        setError(false)
      }, 1000)
      return
    }

    setValue(e.target.value)
    onChange(e.target.value)
  }

  return (
    <div className="form-input">
      <input id={label.toLowerCase().replace(" ", "-")} type="text" value={value} required onChange={handleChange} />
      <span>{label}</span>
      <p className={`invalidMsg ${isError && 'visible'}`} id={label.toLowerCase().replace(" ", "-") + "-error"}>{errorMsg}</p>
    </div>
  )
}

const IngredientList = ({ ingredients }: { ingredients: Ingredient[] }) => {
  const [animateRef] = useAutoAnimate<HTMLUListElement>()

  return (
    <ul className="ingredients-list" ref={animateRef}>
      <h4>Added ingredients</h4>
      {ingredients.length === 0 ? (
        <li>
          <p>So empty...</p>
        </li>
      ) : (
        ingredients.map((ing, index) => (
          <li key={index}>
            <p>{ing.amount + ing.unit}</p>
            <p>{ing.name}</p>
            <p>{ing.alcoholPercentage}%</p>
            <DeleteOutline />
          </li>
        ))
      )}
    </ul>
  )
}

export default NewDrink
