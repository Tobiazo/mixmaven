import { useState } from 'react'
import { useAutoAnimate } from '@formkit/auto-animate/react'
import {
  LocalBar,
  DeleteOutline,
  Edit,
  KeyboardArrowDown,
  KeyboardArrowUp,
} from '@mui/icons-material'
import { Drink } from '../types'
import '../styles/DrinkCard.css'
import { useMutation, useQueryClient } from '@tanstack/react-query'
import { deleteDrink } from '../api/drinks'
import { Link } from 'react-router-dom'

type Props = {
  content: Drink
  id: string
}

const DrinkCard = ({ content, id }: Props) => {
  const [display, setDisplay] = useState<boolean>(false)

  const [boxRef] = useAutoAnimate<HTMLDivElement>()
  const queryClient = useQueryClient()

  const deleteMutation = useMutation({
    mutationFn: deleteDrink,
    onSuccess: () => {
      queryClient.setQueryData(['drinks'], (prev: Drink[]) => {
        const copy = [...prev]
        const filtered = copy.filter((drink) => {
          // console.log(drink.id, data.id);
          return drink.id !== id
        })
        // console.log(filtered);
        return filtered
      })
      queryClient.invalidateQueries({ queryKey: ['drinks'], exact: true })
    },
  })

  return (
    <div
      className={`drink-card type-${
        content.alcoholContent >= 0.7 && 'alcohol'
      }`}
      ref={boxRef}
    >
      <div className="card-title" onClick={() => setDisplay((val) => !val)}>
        <LocalBar fontSize="large" />
        <h3>{content.name}</h3>
        {display ? (
          <KeyboardArrowUp fontSize="medium" />
        ) : (
          <KeyboardArrowDown fontSize="medium" />
        )}
      </div>
      {display && (
        <div className="card-content">
          <h4>Ingredients:</h4>
          <ul>
            {content.ingredients.map((ingredient, ingredient_index) => (
              <li
                key={'ingredient' + ingredient_index}
                className="ingredient-item"
              >
                {`${ingredient.name}, ${ingredient.amount + ingredient.unit}`}
              </li>
            ))}
          </ul>
          <div className="icon-box">
            <Link to={`/edit/${id}`} className="icon-edit">
              <Edit fontSize="inherit" />
              <p>EDIT</p>
            </Link>
            <button
              className="icon-delete"
              onClick={() => deleteMutation.mutate(id)}
              disabled={deleteMutation.status === 'pending'}
            >
              <DeleteOutline fontSize="inherit" />
              <p>
                {deleteMutation.status === 'pending' ? 'Loading...' : 'DELETE'}
              </p>
            </button>
          </div>
        </div>
      )}
    </div>
  )
}

export default DrinkCard
