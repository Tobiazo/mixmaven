import { useMutation, useQueryClient } from '@tanstack/react-query'
import DrinkForm from '../components/DrinkForm'
import { useNavigate, useParams } from 'react-router-dom'
import { Drink } from '../types'
import { editDrink } from '../api/drinks'

const EditDrink = () => {
  const { id } = useParams()
  const queryClient = useQueryClient()
  const navigate = useNavigate()

  const drink = (
    (queryClient.getQueryData(['drinks']) as Array<Drink>) || []
  ).find((drink) => drink.id === id)

  const editMutation = useMutation({
    mutationFn: editDrink,
    onSuccess: () => {
      queryClient.invalidateQueries({ queryKey: ['drinks'], exact: true })
      navigate('/')
    },
  })

  return (
    <div className="new-drink-container">
      <h2>Edit drink</h2>
      {drink === undefined ? (
        <p>Drink not found</p>
      ) : (
        <DrinkForm submit={editMutation} INIT_VALUES={drink} id={id} />
      )}
    </div>
  )
}

export default EditDrink
