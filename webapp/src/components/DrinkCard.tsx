import { useState } from 'react'
import {
  LocalBar,
  DeleteOutline,
  Edit,
  KeyboardArrowDown,
  KeyboardArrowUp,
} from '@mui/icons-material'
import { Drink } from '../types'
import '../styles/DrinkCard.css'

const DrinkCard = ({ content }: { content: Drink }) => {
  const [display, setDisplay] = useState<boolean>(false)

  return (
    <div
      className={`drink-item type-${content.ingredients[0].name.toLowerCase()}`}
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
            <div className="icon-edit" onClick={() => {}}>
              <Edit fontSize="inherit" />
              <p>EDIT</p>
            </div>
            <div className="icon-delete">
              <DeleteOutline fontSize="inherit" />
              <p>DELETE</p>
            </div>
          </div>
        </div>
      )}
    </div>
  )
}

export default DrinkCard
