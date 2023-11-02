import { useAutoAnimate } from '@formkit/auto-animate/react'
import { Ingredient } from '../types'
import { DeleteOutline } from '@mui/icons-material'

const IngredientList = ({
  ingredients,
  setIngredients,
}: {
  ingredients: Ingredient[]
  setIngredients: React.Dispatch<React.SetStateAction<Ingredient[]>>
}) => {
  const [animateRef] = useAutoAnimate<HTMLUListElement>()

  const handleDelete = (index: number) => {
    const copy = [...ingredients]
    copy.splice(index, 1)
    setIngredients(copy)
  }

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
            <p className="capitalize">{ing.name}</p>
            <p>{ing.alcoholPercentage}%</p>
            <button onClick={() => handleDelete(index)}>
              <DeleteOutline />
            </button>
          </li>
        ))
      )}
    </ul>
  )
}

export default IngredientList
