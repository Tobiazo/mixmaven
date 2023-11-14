import { useAutoAnimate } from '@formkit/auto-animate/react'
import { Ingredient } from '../types'
import { DeleteOutline } from '@mui/icons-material'

const IngredientList = ({
  ingredients,
  setIngredient,
  setIngredients,
  setEditIndex,
}: {
  ingredients: Ingredient[]
  setIngredient: React.Dispatch<React.SetStateAction<Ingredient>>
  setIngredients: React.Dispatch<React.SetStateAction<Ingredient[]>>
  setEditIndex: React.Dispatch<React.SetStateAction<number | null>>
}) => {
  const [animateRef] = useAutoAnimate<HTMLUListElement>()

  const handleDelete = (index: number) => {
    const copy = [...ingredients]
    copy.splice(index, 1)
    setIngredients(copy)
  }

  return (
    <div className="ingredients-list">
      <h4>Added ingredients</h4>
      <ul ref={animateRef}>
        {ingredients.length === 0 ? (
          <li>
            <p>So empty...</p>
          </li>
        ) : (
          ingredients.map((ing, index) => (
            <li
              key={index}
              onClick={() => {
                setEditIndex(index)
                setIngredient({
                  name: ing.name,
                  amount: ing.amount,
                  alcoholPercentage: ing.alcoholPercentage,
                  unit: ing.unit,
                  type: ing.type,
                })
              }}
            >
              <p>{ing.amount + ing.unit}</p>
              <p className="capitalize">{ing.name}</p>
              <p>{ing.alcoholPercentage || 0}%</p>
              <button
                id="delete-ingredient"
                onClick={(e) => {
                  e.stopPropagation()
                  handleDelete(index)
                }}
              >
                <DeleteOutline />
              </button>
            </li>
          ))
        )}
      </ul>
    </div>
  )
}

export default IngredientList
